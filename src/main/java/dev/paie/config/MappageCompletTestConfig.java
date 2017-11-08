package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dev.paie.spring.JpaConfig;

@Configuration
@EnableJpaRepositories("dev.paie.repository")
@Import(JpaConfig.class)
public class MappageCompletTestConfig {

}
