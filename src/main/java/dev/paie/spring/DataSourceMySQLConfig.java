package dev.paie.spring;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan({ "dev.paie.spring", "dev.paie.service", "dev.paie.util" })
public class DataSourceMySQLConfig {

	/**
	 * Bean de dataSource
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("app");

		dataSource.setDriverClassName(resourceBundle.getString("jdbc.driver"));

		String url = resourceBundle.getString("jdbc.url");
		String username = resourceBundle.getString("jdbc.user");
		String password = resourceBundle.getString("jdbc.pass");

		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
