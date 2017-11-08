package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
// Import de la configuration XML dans une configuration Java
@ImportResource({ "classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml",
		"classpath:entreprise.xml", "classpath:grade.xml", "classpath:jdd-config.xml" })
public class JeuxDeDonneesConfig {

}
