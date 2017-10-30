package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dev.paie.service.CalculerRemunerationService;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Configuration
@ComponentScan({"dev.paie.service", "dev.paie.util"})
public class ServicesConfig {
/*
	@Bean
	public CalculerRemunerationService calculerRemunerationService(){
		return new CalculerRemunerationServiceSimple();	
	}
	*/
}
