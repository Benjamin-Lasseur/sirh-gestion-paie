package dev.paie.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;

public class JeuxDeDonneesTest {

	/** context : ClassPathXmlApplicationContext */
	private ClassPathXmlApplicationContext context;
	/** bulletin1 : BulletinSalaire */
	private BulletinSalaire bulletin1;

	/**
	 * Instanciation d'un bulletin de salaire
	 */
	@Before
	public void onSetup() {
		context = new ClassPathXmlApplicationContext("jdd-config.xml");
		bulletin1 = context.getBean("bulletin1", BulletinSalaire.class);
	}

	/**
	 * Test primeExceptionnel = 1000
	 */
	@Test
	public void test_primeExceptionnelle() {
		assertThat(bulletin1.getPrimeExceptionnelle()).isEqualTo(new BigDecimal("1000"));
	}

	/**
	 * Test matricule = M01
	 */
	@Test
	public void test_employe() {
		assertThat(bulletin1.getRemunerationEmploye().getMatricule()).isEqualTo("M01");
	}

	/**
	 * Test sur l'entreprise
	 */
	@Test
	public void test_entreprise() {
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getSiret()).isEqualTo("80966785000022");
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getDenomination()).isEqualTo("Dev Entreprise");
		assertThat(bulletin1.getRemunerationEmploye().getEntreprise().getCodeNaf()).isEqualTo("6202A");
	}

	/**
	 * Test sur les cotisations non-imposables
	 */
	@Test
	public void test_cotisationsNonImposables() {
		List<Cotisation> cotisationsNonImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();
		Stream.of("EP01", "EP02", "EP03", "EP04", "EP05", "EP06", "EP07", "EP12", "EP19", "EP20", "EPR1", "E900",
				"EP28", "EP37")
				.forEach(code -> assertThat(cotisationsNonImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()).isTrue());
	}

	/**
	 * Test sur les cotisations imposables
	 */
	@Test
	public void test_cotisationImposables() {
		List<Cotisation> cotisationsImposables = bulletin1.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		Stream.of("SP01", "SP02").forEach(code -> assertThat(cotisationsImposables.stream().filter(c -> c.getCode().equals(code)).findAny().isPresent()).isTrue());
	}

	/**
	 * Test sur grade
	 */
	@Test
	public void test_grade() {
		assertThat(bulletin1.getRemunerationEmploye().getGrade().getNbHeuresBase()).isEqualTo(new BigDecimal("151.67"));
		assertThat(bulletin1.getRemunerationEmploye().getGrade().getTauxBase()).isEqualTo(new BigDecimal("11.0984"));
	}

	/**
	 * Fermeture du context
	 */
	@After
	public void onExit() {
		context.close();
	}
}
