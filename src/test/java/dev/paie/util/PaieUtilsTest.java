package dev.paie.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PaieUtilsTest {
	/** paieUtils : PaieUtils */
	private PaieUtils paieUtils;
	/** context : ClassPathXmlApplicationContext */
	private ClassPathXmlApplicationContext context;
	/**
	 * Création du contexte
	 */
	@Before
	public void onSetup() {
		context = new ClassPathXmlApplicationContext("app-config.xml");
		paieUtils = context.getBean(PaieUtils.class);
	}

	/**
	 * Test
	 */
	@Test
	public void test_formaterBigDecimal_entier_positif() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2"));
		assertThat(resultat, equalTo("2.00"));
	}

	/**
	 * Test
	 */
	@Test
	public void test_formaterBigDecimal_trois_chiffres_apres_la_virgule() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2.199"));
		assertThat(resultat, equalTo("2.20"));
	}
	
	/**
	 * Test
	 */
	@Test
	public void test_formaterBigDecimal_quatre_chiffres_apres_la_virgule() {
		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2.1999"));
		assertThat(resultat, equalTo("2.20"));
	}

	/**
	 * Fermeture du context
	 */
	@After
	public void onExit() {
		context.close();
	}
}
