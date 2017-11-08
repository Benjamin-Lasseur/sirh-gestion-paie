package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.AvantageRepositoryTestConfig;
import dev.paie.entite.Avantage;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { AvantageRepositoryTestConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Before
	public void init() {
		avantageRepository.deleteAll();
	}

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Avantage avantage = new Avantage();
		avantage.setCode("AVAN1");
		avantage.setNom("Avantage n°1");
		avantage.setMontant(new BigDecimal(1000));

		// TODO sauvegarder un nouvel avantage
		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via
		// la méthode findOne
		avantageRepository.save(avantage);
		Avantage avantage2 = avantageRepository.findByCode("AVAN1");
		Avantage avantage3 = avantageRepository.findOne(avantage2.getId());
		assertThat(avantage).isEqualTo(avantage3);

		// TODO modifier un avantage
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode findOne
		avantage3.setCode("AVAN2");
		avantageRepository.save(avantage3);
		Avantage avantage4 = avantageRepository.findOne(avantage3.getId());
		assertThat(avantage3).isEqualTo(avantage4);
	}

}
