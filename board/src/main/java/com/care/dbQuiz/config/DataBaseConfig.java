package com.care.dbQuiz.config;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan(basePackages = {"com.care.dbQuiz.repository"})
@Configuration
public class DataBaseConfig {
	@Bean
	public SqlSessionFactoryBean sessionFactory() throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver;
		resolver = new PathMatchingResourcePatternResolver();
		
		Resource[] res = resolver.getResources("classpath:/mappers/*Mapper.xml");
		
		sessionFactory.setMapperLocations(res);
		return sessionFactory;
	}
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("spring0115");
		config.setPassword("oracle");
		
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	

	
}
