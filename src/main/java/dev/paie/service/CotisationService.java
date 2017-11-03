package dev.paie.service;

import java.util.List;

import dev.paie.entite.Cotisation;

public interface CotisationService {
	/**Enregistrer une nouvelle cotisation
	 * @param nouvelleCotisation
	 */
	void sauvegarder(Cotisation nouvelleCotisation);

	/**Mettre à jour une nouvelle cotisation
	 * @param cotisation
	 */
	void mettreAJour(Cotisation cotisation);

	/**Lister les cotisations
	 * @return
	 */
	List<Cotisation> lister();
}
