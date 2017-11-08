package dev.paie.spring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(DataSourceMySQLConfig.class)
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
public class JpaConfig {
	/**
	 * Bean PlatformTransactionManager
	 * 
	 * @param emf
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

	/**
	 * Bean EntityManagerFactory
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory(@Value("${hibernate.dialect}") String dialect,
			@Value("${schema-generation.database.action}") String schemaGenerationDbAction, DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		// activer les logs SQL
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		// alternative au persistence.xml
		factory.setPackagesToScan("dev.paie.entite");
		factory.setDataSource(dataSource);

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("javax.persistence.schema-generation.database.action", schemaGenerationDbAction);
		jpaProperties.setProperty("hibernate.dialect", dialect);
		jpaProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
}
