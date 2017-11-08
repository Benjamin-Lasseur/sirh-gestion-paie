package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;

import dev.paie.repository.ProfilRemunerationRepository;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	/** em : EntityManager */
	@PersistenceContext
	private EntityManager em;
	/** entreprises : EntrepriseRepository */
	@Autowired
	private EntrepriseRepository entreprises;
	/** profils : ProfilRemunerationRepository */
	@Autowired
	private ProfilRemunerationRepository profils;
	/** grades : GradeRepository */
	@Autowired
	private GradeRepository grades;
	@Autowired
	private PasswordEncoder passEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dev.paie.service.InitialiserDonneesService#initialiser()
	 */
	@Override
	public void initialiser() {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml",
				"classpath:entreprises.xml", "classpath:grades.xml", "classpath:profils-remuneration.xml");
		context.getBeansOfType(Cotisation.class).values().forEach(cot -> em.persist(cot));
		context.getBeansOfType(Entreprise.class).values().forEach(ent -> em.persist(ent));
		context.getBeansOfType(Grade.class).values().forEach(gra -> em.persist(gra));
		context.getBeansOfType(ProfilRemuneration.class).values().forEach(pro -> em.persist(pro));

		List<Periode> periodes = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			LocalDate init = LocalDate.of(2017, i, 1);
			Periode periode = new Periode(init, init.with(lastDayOfMonth()));
			periodes.add(periode);
			em.persist(periode);
		}
		List<RemunerationEmploye> employes = new ArrayList<>();

		employes.add(new RemunerationEmploye("M0111", entreprises.findAll().get(1), profils.findAll().get(2),
				grades.findAll().get(0), LocalDateTime.of(2017, 6, 1, 10, 16)));
		employes.add(new RemunerationEmploye("M0112", entreprises.findAll().get(0), profils.findAll().get(1),
				grades.findAll().get(1), LocalDateTime.of(2017, 6, 1, 10, 17)));
		employes.add(new RemunerationEmploye("M0114", entreprises.findAll().get(2), profils.findAll().get(1),
				grades.findAll().get(0), LocalDateTime.of(2017, 6, 1, 10, 17)));
		employes.add(new RemunerationEmploye("M0113", entreprises.findAll().get(2), profils.findAll().get(0),
				grades.findAll().get(1), LocalDateTime.of(2017, 6, 1, 10, 18)));

		employes.stream().forEach(employe -> em.persist(employe));

		List<BulletinSalaire> bulletins = new ArrayList<>();

		bulletins.add(new BulletinSalaire(employes.get(0), periodes.get(0), new BigDecimal("100"),
				LocalDateTime.of(2017, 06, 01, 10, 16)));
		bulletins.add(new BulletinSalaire(employes.get(1), periodes.get(0), new BigDecimal("200"),
				LocalDateTime.of(2017, 06, 01, 10, 17)));
		bulletins.add(new BulletinSalaire(employes.get(2), periodes.get(0), new BigDecimal("115"),
				LocalDateTime.of(2017, 06, 01, 10, 17)));
		bulletins.add(new BulletinSalaire(employes.get(3), periodes.get(0), new BigDecimal("152"),
				LocalDateTime.of(2017, 06, 01, 10, 18)));
		bulletins.forEach(bulletin -> em.persist(bulletin));

		em.persist(new Utilisateur("Lasseur", passEncoder.encode("0000"), true, ROLES.ROLE_ADMINISTRATEUR));
		em.persist(new Utilisateur("Chidaine", passEncoder.encode("0000"), true, ROLES.ROLE_UTILISATEUR));

		context.close();
	}

}
