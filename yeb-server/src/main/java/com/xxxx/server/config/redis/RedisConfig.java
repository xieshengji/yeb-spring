package com.xxxx.server.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: QiuXuechen
 * @Date: 2021/8/25 23:57
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //String类型key序列器
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //String类型value序列器
    redisTemplate.setValueSerializer( new GenericJackson2JsonRedisSerializer());
    //Hash类型key序列器
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    //Hash类型value序列器
    redisTemplate.setHashValueSerializer( new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return  redisTemplate;

  }
}
