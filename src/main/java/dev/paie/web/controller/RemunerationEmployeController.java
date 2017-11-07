package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
@Transactional
public class RemunerationEmployeController {

	/** entrepriseRepository : EntrepriseRepository */
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	/** gradeRepository : GradeRepository */
	@Autowired
	private GradeRepository gradeRepository;
	/** profilRepository : ProfilRemunerationRepository */
	@Autowired
	private ProfilRemunerationRepository profilRepository;
	/** employeRepository : RemunerationEmployeRepository */
	@Autowired
	private RemunerationEmployeRepository employeRepository;

	/**
	 * Méthode get de créer
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepository.findAll());
		mv.addObject("grades", gradeRepository.findAll());
		mv.addObject("profils", profilRepository.findAll());
		return mv;
	}

	/**
	 * Méthode post de créer
	 * 
	 * @param matricule
	 * @param entreprise
	 * @param profil
	 * @param grade
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView ajouterEmploye(@RequestParam("Matricule") String matricule,
			@RequestParam("Entreprise") String entreprise, @RequestParam("Profil") String profil,
			@RequestParam("Grade") String grade) {

		RemunerationEmploye employe = new RemunerationEmploye();
		if (matricule != null) {
			employe.setMatricule(matricule);
		}
		employe.setEntreprise(entrepriseRepository.findByDenomination(entreprise));
		employe.setProfilRemuneration(profilRepository.findByCode(profil));
		employe.setGrade(gradeRepository.findByCode(grade));
		employe.setDateCreation(LocalDateTime.now());
		employeRepository.save(employe);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("employes", employeRepository.findAll());
		return mv;
	}

	/**
	 * Méthode get de lister
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("employes", employeRepository.findAll());
		return mv;
	}
}