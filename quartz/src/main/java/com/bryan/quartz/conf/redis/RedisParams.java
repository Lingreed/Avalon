package com.bryan.quartz.conf.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix="redis")
public class RedisParams {

    private Integer maxActive;

    private Integer maxIdle;

    private Integer maxWait;

    private Boolean testOnBorrow;

    private String ip;

    private Integer port;

    private String password;

    private Integer database;

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getIp() {
    	if(System.getenv("REDIS_IP") != null){
            this.ip = System.getenv("REDIS_IP");
        }
        return ip;
    }

    public void setIp(String ip) {
    	this.ip = ip;
    }

    public Integer getPort() {
    	if(System.getenv("REDIS_PORT") != null){
            this.port = Integer.parseInt(System.getenv("REDIS_PORT"));
        }
        return port;
    }

    public void setPort(Integer port) {
    	this.port = port;
    }

    public String getPassword() {
    	if(System.getenv("REDIS_PASSWORD") != null){
            this.password = System.getenv("REDIS_PASSWORD");
        }
        return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }
}