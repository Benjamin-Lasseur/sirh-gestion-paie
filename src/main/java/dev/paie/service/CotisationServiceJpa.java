package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Repository
@Transactional
public class CotisationServiceJpa implements CotisationService {

	/** em : EntityManager */
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see dev.paie.service.CotisationService#sauvegarder(dev.paie.entite.Cotisation)
	 */
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);

	}

	/* (non-Javadoc)
	 * @see dev.paie.service.CotisationService#mettreAJour(dev.paie.entite.Cotisation)
	 */
	@Override
	public void mettreAJour(Cotisation cotisation) {
		em.merge(cotisation);
	}

	/* (non-Javadoc)
	 * @see dev.paie.service.CotisationService#lister()
	 */
	@Override
	public List<Cotisation> lister() {
		return em.createQuery("SELECT c FROM Cotisation c", Cotisation.class).getResultList();
	}

}
