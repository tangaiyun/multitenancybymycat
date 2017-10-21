package com.wym.mycatdemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@SpringBootApplication
public class SpringBootJdbcDemoApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootJdbcDemoApplication.class, args);
    }
}
