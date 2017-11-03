package dev.paie.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;

	@Autowired
	DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Before
	public void truncate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("TRUNCATE TABLE GRADE");
	}

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
		if (grade2.isPresent()) {
			assertThat(grade).isEqualTo(grade3.get());
		}
	}
}