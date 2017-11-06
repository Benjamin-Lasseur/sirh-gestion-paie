package dev.paie.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Controller
@RequestMapping("/employes")
@Transactional
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private ProfilRemunerationRepository profilRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepository.findAll());
		mv.addObject("grades", gradeRepository.findAll());
		mv.addObject("profils", profilRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView ajouterEmploye(@RequestParam("Matricule") String matricule,
			@RequestParam("Entreprise") String entreprise, @RequestParam("Profil") String profil,
			@RequestParam("Grade") String grade) {

		RemunerationEmploye employe = new RemunerationEmploye();
		Optional<Entreprise> optEnt = entrepriseRepository.findAll().stream()
				.filter(ent -> ent.getDenomination().equals(entreprise)).findFirst();
		if (optEnt.isPresent()) {
			employe.setEntreprise(optEnt.get());
		}
		// TODO @ModelAttribute
		Optional<ProfilRemuneration> optPro = profilRepository.findAll().stream()
				.filter(pro -> pro.getCode().equals(profil)).findFirst();
		if (optPro.isPresent()) {
			employe.setProfilRemuneration(optPro.get());
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		return mv;
	}
}