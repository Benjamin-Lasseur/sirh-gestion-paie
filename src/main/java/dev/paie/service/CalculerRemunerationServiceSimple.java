package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		BigDecimal salaireDeBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		resultat.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireDeBase));

		BigDecimal salairebrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		resultat.setSalaireBrut(paieUtils.formaterBigDecimal(salairebrut));

		BigDecimal totalRetenueSalariale = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(cot -> cot.getTauxSalarial() != null)
				.map(cNI -> cNI.getTauxSalarial().multiply(salairebrut)).reduce((a, b) -> a.add(b)).get();
		resultat.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totalRetenueSalariale));

		BigDecimal totalRetenuePtronale = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(cot -> cot.getTauxPatronal() != null)
				.map(cNI -> cNI.getTauxPatronal().multiply(salairebrut)).reduce((a, b) -> a.add(b)).get();
		resultat.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalRetenuePtronale));

		BigDecimal netImposable = (new BigDecimal(paieUtils.formaterBigDecimal(salairebrut)))
				.subtract(new BigDecimal(paieUtils.formaterBigDecimal(totalRetenueSalariale)));
		resultat.setNetImposable(paieUtils.formaterBigDecimal(netImposable));

		BigDecimal netAPayer = netImposable.subtract(bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables().stream().filter(cot -> cot.getTauxSalarial() != null)
				.map(cNI -> cNI.getTauxSalarial().multiply(salairebrut)).reduce((a, b) -> a.add(b)).get());

		resultat.setNetAPayer(paieUtils.formaterBigDecimal(netAPayer));

		return resultat;
	}

}
