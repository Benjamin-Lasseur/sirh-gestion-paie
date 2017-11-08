package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.service.CotisationServiceJpa;
import dev.paie.spring.JpaConfig;

@Configuration
@Import({ JpaConfig.class, CotisationServiceJpa.class })
public class CotisationServiceJpaTestConfig {

}
