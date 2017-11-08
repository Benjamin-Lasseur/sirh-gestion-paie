package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.GradeServiceJdbcTemplateTestConfig;
import dev.paie.entite.Grade;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { GradeServiceJdbcTemplateTestConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	/** gradeService : GradeService */
	@Autowired
	private GradeService gradeService;

	/**
	 * test de sauvegarde et de modification
	 */
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Grade grade = new Grade();
		grade.setCode("GRA1");
		grade.setNbHeuresBase(new BigDecimal(10));
		grade.setTauxBase(new BigDecimal(25));

		gradeService.sauvegarder(grade);

		Optional<Grade> grade2 = gradeService.lister().stream().filter(g -> g.getCode().equals("GRA1")).findFirst();
		if (grade2.isPresent()) {
			assertThat(grade).isEqualTo(grade2.get());
		}

		grade.setCode("GRA2");
		grade.setId(grade2.get().getId());
		gradeService.mettreAJour(grade);

		Optional<Grade> grade3 = gradeService.lister().stream().filter(g -> g.getCode().equals("GRA2")).findFirst();
		if (grade3.isPresent()) {
			assertThat(grade).isEqualTo(grade3.get());
		}
	}
}
