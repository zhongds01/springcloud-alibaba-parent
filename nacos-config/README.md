# nacos-config

## 1、客户端导入依赖

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

## 2、配置bootstrap.properties文件

```properties
# nacos服务地址
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
# 应用名称
spring.application.name=nacos-config
# 指定nacos服务端配置文件名称，默认prefix为${spring.application.name}，优先加载prefix，其次为name
#spring.cloud.nacos.config.prefix=${spring.application.name}
#spring.cloud.nacos.config.name=${spring.application.name}
# 指定nacos服务端配置文件后缀
spring.cloud.nacos.config.file-extension=yaml
# 是否自动刷新配置，默认true
spring.cloud.nacos.config.refresh-enabled=true
# 是否开启nacos-config功能，默认true
spring.cloud.nacos.config.enabled=true
# 指定配置文件组
spring.cloud.nacos.config.group=NACOS_TEST_GROUP
# 指定命名空间,67f4b6df-ddc6-44e6-b51b-dbc0ec64d4dd是nacos服务端自动生成
#spring.cloud.nacos.config.namespace=67f4b6df-ddc6-44e6-b51b-dbc0ec64d4dd
# 暴露所有端点
management.endpoints.web.exposure.include=*

# 自定义dataId
spring.cloud.nacos.config.extension-configs[0].data-id=ext-config0.yaml
# 一般extension-configs[1]优先级大于extension-configs[0]，
# 但是如果extension-configs[1].data-id=nacos-config.yaml，即data-id与默认的冲突，那么1不生效，0就是最高的优先级
spring.cloud.nacos.config.extension-configs[1].data-id=ext-config1.yaml
# 指定所在的组，默认为DEFAULT_GROUP
spring.cloud.nacos.config.extension-configs[1].group=EXT-CONFIG1_GROUP
# 是否支持动态刷新，默认为false
spring.cloud.nacos.config.extension-configs[1].refresh=true

# 激活不同的环境
spring.profiles.active=dev
```

## 3、在nacos服务端创建配置文件

> 配置文件名称为${spring.application.name}.${spring.cloud.nacos.config.file-extension}，即nacos-config.yaml。

## 4、动态刷新实现

*可以通过配置 `spring.cloud.nacos.config.refresh-enabled=false` 来关闭动态刷新*

### 4.1、@RefreshScope注解+@Value注解使用

- 在类上使用@RefreshScope注解，并在类中使用@Value注解定义配置文件中的属性。可达到动态更新效果

```java
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {
    @Value("${nacos.test.server-url}")
    private String serverUrl;

    @Value("${nacos.test.port}")
    private String port;

    @Value("${nacos.test.is-alive}")
    private boolean isAlive;

    @GetMapping("/get")
    public String get() {
        return serverUrl + ":" + port + " status is : " + isAlive;
    }
}
```

### 4.2、@ConfigurationProperties+@EnableConfigurationProperties使用

- 新建一个类，封装自定义属性，获取配置文件中以nacos.test开头的属性信息，封装到NacosProperties实体类中

```java
@Data
@ConfigurationProperties(prefix = "nacos.test")
public class NacosProperties {
    private String serverUrl;
    private String port;
    private boolean isAlive;
}
```

- 使用时，@EnableConfigurationProperties({NacosProperties.class})使自定义配置类生效，并获取NacosProperties类实例

```java
@RestController
@RequestMapping("/config")
@EnableConfigurationProperties({NacosProperties.class})
public class ConfigController {
    private final NacosProperties properties;

    public ConfigController(NacosProperties properties) {
        this.properties = properties;
    }
    
    @GetMapping("/get")
    public String get() {
        return properties.getServerUrl() + ":" + properties.getPort() + " status is : " + properties.isAlive();
    }
}
```

## 5、多profile配置（支持不同环境）

> nacos-config加载指定的${spring.application.name}.${file-extension:properties}（本文中的nacos-config.yaml）配置文件时，同时还会加载${spring.application.name}-${profile}.${file-extension:properties}。其中${profile}的值通过${spring.profiles.active}指定。

```xml
2021-09-17 22:04:29.012  WARN 3884 --- [           main] c.a.c.n.c.NacosPropertySourceBuilder     : Ignore the empty nacos configuration and get it based on dataId[nacos-config] & group[NACOS_TEST_GROUP]
2021-09-17 22:04:29.021  WARN 3884 --- [           main] c.a.c.n.c.NacosPropertySourceBuilder     : Ignore the empty nacos configuration and get it based on dataId[nacos-config-dev.yaml] & group[NACOS_TEST_GROUP]
2021-09-17 22:04:29.021  INFO 3884 --- [           main] b.c.PropertySourceBootstrapConfiguration : Located property source: [BootstrapPropertySource {name='bootstrapProperties-nacos-config-dev.yaml,NACOS_TEST_GROUP'}, BootstrapPropertySource {name='bootstrapProperties-nacos-config.yaml,NACOS_TEST_GROUP'}, BootstrapPropertySource {name='bootstrapProperties-nacos-config,NACOS_TEST_GROUP'}]
2021-09-17 22:04:29.025  INFO 3884 --- [           main] com.zds.NacosConfigApplication           : The following profiles are active: dev
```

## 6、自定义namespace

> 用于进行租户粒度的配置隔离。不同的命名空间下，可以存在相同的 Group 或 Data ID 的配置。namespace 的常用场景之一是不同环境的配置的区分隔离，例如开发测试环境和生产环境的资源（如配置、服务）隔离等。
>
> 默认的namespace为Public
>
> 可以自己在nacos服务端创建namespace，命名空间的id使用自动生成即可。创建完后，需要记住服务端给我们生成的namespace id，并将改id在客户端的bootstrap.properties文件中配置

```properties
# 指定命名空间,67f4b6df-ddc6-44e6-b51b-dbc0ec64d4dd是nacos服务端自动生成
spring.cloud.nacos.config.namespace=67f4b6df-ddc6-44e6-b51b-dbc0ec64d4dd
```

## 7、自定义group

> 在nacos服务端创建配置文件时，修改默认的DEFAULT_GROUP为自己定义的DEFAULT，修改完成后在客户端的bootstrap.properties配置文件中设置默认的group为自定义的GROUP

```properties
# 指定配置文件组
spring.cloud.nacos.config.group=NACOS_TEST_GROUP
```

## 8、自定义扩展data id

> 使用自定义扩展dataId，可以实现指定dataId的名称，组，以及是否自动刷新。实现多配置文件共存。

```properties
# 自定义dataId
spring.cloud.nacos.config.extension-configs[0].data-id=ext-config0.yaml
spring.cloud.nacos.config.extension-configs[1].data-id=ext-config1.yaml
# 指定所在的组，默认为DEFAULT_GROUP
spring.cloud.nacos.config.extension-configs[1].group=EXT-CONFIG1_GROUP
# 是否支持动态刷新，默认为false
spring.cloud.nacos.config.extension-configs[1].refresh=true
```

**注意：**

> - 多个 data Id 同时配置时，他的优先级关系是 `spring.cloud.nacos.config.extension-configs[n].data-id` 其中 n 的值越大，优先级越高。即以上配置中1会生效
>
> - `spring.cloud.nacos.config.extension-configs[n].data-id` 的值必须带文件扩展名，文件扩展名既可支持 properties，又可以支持 yaml/yml。 此时 `spring.cloud.nacos.config.file-extension` 的配置对自定义扩展配置的 data Id 文件扩展名没有影响。
>
> - 自定义的data-id不要与默认的${spring.application.name}一样，否则自定义的不生效。
>
>   比如我把上面的spring.cloud.nacos.config.extension-configs[1].data-id=ext-config1.yaml中的data-id=ext-config1.yaml修改为nacos-config.yaml，那么整个extension-configs[1]都不生效，反而extension-configs[0]会生效。

**优点**

> 实现一个工程多个配置文件
>
> 实现一个配置文件多工程共享

**Spring Cloud Alibaba Nacos Config 目前提供了三种配置能力从 Nacos 拉取相关的配置。**

> - A: 通过 `spring.cloud.nacos.config.shared-configs[n].data-id` 支持多个共享 Data Id 的配置
> - B: 通过 `spring.cloud.nacos.config.extension-configs[n].data-id` 的方式支持多个扩展 Data Id 的配置
> - C: 通过内部相关规则(应用名、应用名+ Profile )自动生成相关的 Data Id 配置
> - 当三种方式共同使用时，他们的一个优先级关系是:A < B < C

## 9、关闭nacos-config功能

```properties
spring.cloud.nacos.config.enabled = false
```

## 10、nacos-config中的其他配置

