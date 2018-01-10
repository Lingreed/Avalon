package com.bryan.conf.redis;

import com.bryan.common.redis.FSTRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisParams redisParams;
    @Autowired
    FSTRedisSerializer<Object> fstRedisSerializer;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisParams.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisParams.getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(redisParams.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(redisParams.getTestOnBorrow());
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory redisConnectionFactory  = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(redisParams.getIp());
        redisConnectionFactory.setPort(redisParams.getPort());
        redisConnectionFactory.setDatabase(redisParams.getDatabase());
        redisConnectionFactory.setPassword(redisParams.getPassword());
        redisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return redisConnectionFactory ;
    }
    
	@Bean
    public RedisTemplate<String,Object> redisTemplate(){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
    	redisTemplate.setConnectionFactory(connectionFactory());
    	
    	redisTemplate.setValueSerializer(fstRedisSerializer);
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
    	
    	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashValueSerializer(fstRedisSerializer);
    	
    	//默认不开启是事务
    	redisTemplate.setEnableTransactionSupport(false);
    	
    	return redisTemplate;
    }
}
