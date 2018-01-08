package com.bryan.conf.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:db.properties")
@ConfigurationProperties(prefix="jdbc")
public class JdbcParams {

    private String driver;

    private String url;

    private String user;

    private String password;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private String filters;

    private Boolean poolPreparedStatements;
    
    private Integer maxPoolPreparedStatementPerConnectionSize;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
    	if(System.getenv("JDBC_URL") != null){
            this.url = System.getenv("JDBC_URL");
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
    	if( System.getenv("JDBC_USER") != null){
            this.user = System.getenv("JDBC_USER");
        }
    	return this.user;
    }

    public void setUser(String user) {
    	this.user = user;
    }

    public String getPassword() {
    	if( System.getenv("JDBC_PASSWORD") != null){
            this.password = System.getenv("JDBC_PASSWORD");
        }
        return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Boolean getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(
			Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}
    

}