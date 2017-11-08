package dev.paie.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.MappageCompletTestConfig;
import dev.paie.entite.Avantage;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { MappageCompletTestConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class MappageCompletTest {

	@Autowired
	private AvantageRepository avantageRepository;
	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired
	private CotisationRepository cotisationRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private PeriodeRepository periodeRepository;
	@Autowired
	private ProfilRemunerationRepository profilRemunerationRepository;
	@Autowired
	private RemunerationEmployeRepository employeRepository;

	@Test
	public void testMappageClasses() {
		List<Cotisation> cotImp = new ArrayList<>();
		cotImp.add(new Cotisation("COT1", "Cotisation Imposable 1", new BigDecimal(25), new BigDecimal(10)));
		cotImp.add(new Cotisation("COT2", "Cotisation Imposable 2", new BigDecimal(26), new BigDecimal(11)));

		List<Cotisation> cotNonImp = new ArrayList<>();
		cotNonImp.add(new Cotisation("COT3", "Cotisation non Imposable 1", new BigDecimal(31), new BigDecimal(16)));
		cotNonImp.add(new Cotisation("COT4", "Cotisation non Imposable 2", new BigDecimal(30), new BigDecimal(15)));

		List<Avantage> avantages = new ArrayList<>();
		avantages.add(new Avantage("AVAN1", "Avantage 1", new BigDecimal(500)));
		avantages.add(new Avantage("AVAN2", "Avantage 2", new BigDecimal(250)));

		ProfilRemuneration profilRemuneration = new ProfilRemuneration("PRREM1", cotImp, cotNonImp, avantages);

		Grade grade = new Grade("GRA1", new BigDecimal(54), new BigDecimal(0.12));

		Entreprise entreprise = new Entreprise("12346841313", "Lasseur corporation", "Rue de la corporation",
				"12348413413", "1324684135468");

		RemunerationEmploye employe = new RemunerationEmploye("MATR1", entreprise, profilRemuneration, grade);

		Periode periode = new Periode(LocalDate.of(2017, 01, 15), LocalDate.of(2018, 01, 15));

		BulletinSalaire bulletinSalaire = new BulletinSalaire(employe, periode, new BigDecimal(50));
		avantageRepository.save(avantages);
		periodeRepository.save(periode);
		cotisationRepository.save(cotImp);
		cotisationRepository.save(cotNonImp);
		gradeRepository.save(grade);
		profilRemunerationRepository.save(profilRemuneration);
		entrepriseRepository.save(entreprise);
		employeRepository.save(employe);
		bulletinSalaireRepository.save(bulletinSalaire);

	}
}
