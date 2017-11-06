package dev.paie.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan({ "dev.paie.spring", "dev.paie.service", "dev.paie.util"})
public class DataSourceMySQLConfig {

	/**
	 * Bean de dataSource
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sirhpaie");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
}
