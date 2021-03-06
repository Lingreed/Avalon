package com.bryan.quartz.conf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Order(1)
public class RedisConfig {

    @Autowired
    private RedisParams redisParams;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisParams.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisParams.getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(redisParams.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(redisParams.getTestOnBorrow());
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(redisParams.getIp());
        redisConnectionFactory.setPort(redisParams.getPort());
        redisConnectionFactory.setDatabase(redisParams.getDatabase());
        redisConnectionFactory.setPassword(redisParams.getPassword());
        redisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        //开启事务支持,设置不支持
        redisTemplate.setEnableTransactionSupport(false);

        return redisTemplate;
    }

}
