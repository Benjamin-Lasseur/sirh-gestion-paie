package dev.paie.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.temporal.TemporalAdjusters.*;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialiser() {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml",
				"classpath:entreprises.xml", "classpath:grades.xml", "classpath:profils-remuneration.xml");
		context.getBeansOfType(Cotisation.class).values().forEach(cot -> em.persist(cot));
		context.getBeansOfType(Entreprise.class).values().forEach(ent -> em.persist(ent));
		context.getBeansOfType(Grade.class).values().forEach(gra -> em.persist(gra));
		context.getBeansOfType(ProfilRemuneration.class).values().forEach(pro -> em.persist(pro));
		context.close();
		for (int i = 1; i <= 12; i++) {
			LocalDate init = LocalDate.of(2017, i, 1);
			em.persist(new Periode(init, init.with(lastDayOfMonth())));
		}
	}

}
