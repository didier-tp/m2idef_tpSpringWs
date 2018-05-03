package com.capgemini.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
	
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String dbUsername;
	
	@Value("${db.password}")
	private String dbPassword;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer(); //to interpret ${} in @Value()
	}

	// la source de données MySQL
	// équivalent java de dataSourceSpringConf.xml
	
	@Bean(name="myDataSource")
	public DataSource dataSource() {
		//org.apache.commons.dbcp.BasicDataSource (si .jar de commons-dbcp , commons-pool)
		//org.springframework.jdbc.datasource.DriverManagerDataSource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/minibank_db");
		dataSource.setUsername("root");
		dataSource.setPassword("");//"root" ou "formation" ou "..."
		*/
		dataSource.setDriverClassName(jdbcDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		
		return dataSource;
	}
	/*
	@Bean(name="myDataSource")
	public DataSource dataSource() {
		//org.apache.commons.dbcp.BasicDataSource (si .jar de commons-dbcp , commons-pool)
		//org.springframework.jdbc.datasource.DriverManagerDataSource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/minibank_db");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		// NB: idéalement avec dataSource.properties
		return dataSource;
	}
	*/

}