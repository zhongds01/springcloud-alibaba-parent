package com.zds.config;

import com.zds.common.ProtoStuffSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * RedisConfiguration
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(
        LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // 1、jdk序列化方式(默认)
        // redisTemplate.setValueSerializer(RedisSerializer.java());
        // 2、jackson序列化方式
         redisTemplate.setValueSerializer(RedisSerializer.json());
         redisTemplate.setHashValueSerializer(RedisSerializer.json());
        // 3、String序列化方式
        // redisTemplate.setValueSerializer(RedisSerializer.string());
        // 4、ProtoStuff序列化方式
        // 使用protostuff序列化方式解决redis存储的实体类使用protostuff序列化情况下，其他redis序列化方式报错问题(存储GRPC调用返回结果会报错)
        //org.springframework.data.redis.serializer.SerializationException:
        // Could not write JSON: No serializer found for class com.google.protobuf.UnknownFieldSet$Parser and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
         redisTemplate.setValueSerializer(new ProtoStuffSerializer());
         redisTemplate.setHashValueSerializer(new ProtoStuffSerializer());

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
