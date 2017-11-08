package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.service.GradeServiceJdbcTemplate;
import dev.paie.spring.JpaConfig;

@Configuration
@Import({ JpaConfig.class, GradeServiceJdbcTemplate.class })
public class GradeServiceJdbcTemplateTestConfig {

}
