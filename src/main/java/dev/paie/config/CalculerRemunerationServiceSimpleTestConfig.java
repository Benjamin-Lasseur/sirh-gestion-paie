package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.service.CalculerRemunerationServiceSimple;
import dev.paie.util.PaieUtils;

@Configuration
@Import({ CalculerRemunerationServiceSimple.class, JeuxDeDonneesConfig.class, PaieUtils.class })
public class CalculerRemunerationServiceSimpleTestConfig {

}
