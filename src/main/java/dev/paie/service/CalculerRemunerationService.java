package dev.paie.service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;


public interface CalculerRemunerationService {
	/**Calcul des valeurs présentes sur un bulletin de salaire à l'aide d'un object BulletinSalaire
	 * @param bulletin
	 * @return
	 */
	ResultatCalculRemuneration calculer(BulletinSalaire bulletin);
}
