package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import dev.paie.spring.JpaConfig;

@Configuration
@Import({ JpaConfig.class, SecurityConfig.class })
@EnableJpaRepositories("dev.paie.repository")
@ComponentScan({ "dev.paie.listener", "dev.paie.service", "dev.paie.util", "dev.paie.web.controller" })
@EnableWebMvc
public class WebAppConfig {
	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}
}
