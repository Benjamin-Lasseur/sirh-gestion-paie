package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.spring.JpaConfig;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util","dev.paie.entite" })
@Import(JpaConfig.class)
public class ServicesConfig {

}
