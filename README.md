# spring-cloud-alibaba微服务搭建

## 1、nacos

### 1.1、下载安装

[nacos下载地址]: https://github.com/alibaba/nacos/releases/tag/

下载完成后解压并启动

### Linux/Unix/Mac

启动命令(standalone代表着单机模式运行，非集群模式):

```
sh startup.sh -m standalone
```

### Windows

启动命令(standalone代表着单机模式运行，非集群模式):

```shell
startup.cmd -m standalone
```

### 1.2、项目导入客户端依赖

新建springboot单体应用，导入nacos服务注册发现的依赖 spring-cloud-starter-alibaba-nacos-discovery

完整pom如下。

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- nacos客户端 -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <!-- spring-cloud-alibaba依赖 -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2021.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2020.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### 1.3、编写配置文件，并注册服务

```yml
server:
  port: 8081
spring:
  application:
    name: service-provider
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

> 在启动类上使用*@EnableDiscoveryClient*注解

## 2、ribbon

## 3、feign

4、Hy
