package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.CotisationServiceJpaTestConfig;
import dev.paie.entite.Cotisation;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { CotisationServiceJpaTestConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Cotisation cotisation = new Cotisation();
		cotisation.setCode("COT1");
		cotisation.setLibelle("Cotisation n°1");
		cotisation.setTauxPatronal(new BigDecimal(15));
		cotisation.setTauxSalarial(new BigDecimal(25));
		// TODO sauvegarder une nouvelle cotisation
		cotisationService.sauvegarder(cotisation);
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		// via laméthode lister
		Optional<Cotisation> optCot = cotisationService.lister().stream().filter(cot -> cot.getCode().equals("COT1"))
				.findFirst();
		if (optCot.isPresent()) {
			assertThat(cotisation).isEqualTo(optCot.get());
			cotisation.setId(optCot.get().getId());
		}

		// TODO modifier une cotisation
		cotisation.setCode("COT2");
		cotisationService.mettreAJour(cotisation);
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode lister
		Optional<Cotisation> optCot2 = cotisationService.lister().stream().filter(cot -> cot.getCode().equals("COT2"))
				.findFirst();
		if (optCot2.isPresent()) {
			assertThat(cotisation).isEqualTo(optCot2.get());
		}

	}

}
