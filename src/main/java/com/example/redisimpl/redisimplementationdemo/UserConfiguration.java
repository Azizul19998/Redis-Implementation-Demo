package com.example.redisimpl.redisimplementationdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class UserConfiguration {
    @Bean
    LettuceConnectionFactory getFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration= new RedisStandaloneConfiguration();
        LettuceConnectionFactory lettuceConnectionFactory
                = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    RedisTemplate<String,User> getTemplate() {
        RedisTemplate<String,User> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer =
                new JdkSerializationRedisSerializer();

        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);

        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        redisTemplate.setConnectionFactory(getFactory());

        return redisTemplate;
    }


}
