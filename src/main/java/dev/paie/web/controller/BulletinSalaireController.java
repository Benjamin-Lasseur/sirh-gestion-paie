package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
@Transactional
public class BulletinSalaireController {

	/** calculService : CalculerRemunerationService */
	@Autowired
	private CalculerRemunerationService calculService;
	/** periodeRepository : PeriodeRepository */
	@Autowired
	private PeriodeRepository periodeRepository;
	/** employeRepository : RemunerationEmployeRepository */
	@Autowired
	private RemunerationEmployeRepository employeRepository;
	/** bulletinSalaireRepository : BulletinSalaireRepository */
	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	/**
	 * Méthode get de lister
	 * 
	 * @return
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		List<BulletinSalaire> bulletins = bulletinSalaireRepository.findAll();
		mv.addObject("bulletins", bulletins);
		Map<BulletinSalaire, ResultatCalculRemuneration> resultats = new HashMap<>();
		bulletins.forEach(bul -> resultats.put(bul, calculService.calculer(bul)));
		mv.addObject("resultats", resultats);
		return mv;
	}

	/**
	 * Méthode get de créer
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletinInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodeRepository.findAll());
		mv.addObject("employes", employeRepository.findAll());
		return mv;
	}

	/**
	 * Méthode get de visualiser
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{id}")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView visualiserBulletin(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		BulletinSalaire bulletin = bulletinSalaireRepository.findOne(Integer.valueOf(id));
		if (bulletin != null) {
			mv.addObject("bulletin", bulletin);
			mv.addObject("cotisationsNonImp",
					bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables());
			mv.addObject("cotisationsImp",
					bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables());
			mv.addObject("resultat", calculService.calculer(bulletin));
		}
		return mv;
	}

	/**
	 * Méthode post de créer
	 * 
	 * @param periode
	 * @param matricule
	 * @param prime
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin(@RequestParam("Periode") String periode,
			@RequestParam("Matricule") String matricule, @RequestParam("Prime") String prime) {
		ModelAndView mv = new ModelAndView();
		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin.setRemunerationEmploye(employeRepository.findByMatricule(matricule));
		bulletin.setPrimeExceptionnelle(new BigDecimal(prime));
		bulletin.setPeriode(periodeRepository.findByLibelle(periode));
		bulletin.setDateCreation(LocalDateTime.now());
		bulletinSalaireRepository.save(bulletin);
		mv.setViewName("bulletins/listerBulletins");
		List<BulletinSalaire> bulletins = bulletinSalaireRepository.findAll();
		mv.addObject("bulletins", bulletins);
		Map<BulletinSalaire, ResultatCalculRemuneration> resultats = new HashMap<>();
		bulletins.forEach(bul -> resultats.put(bul, calculService.calculer(bul)));
		mv.addObject("resultats", resultats);
		return mv;
	}

}
