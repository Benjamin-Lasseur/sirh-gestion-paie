package dev.paie.service;

import java.util.List;

import dev.paie.entite.Grade;

public interface GradeService {
	/**Sauvegarde d'un grade
	 * @param nouveauGrade
	 */
	void sauvegarder(Grade nouveauGrade);

	/**Mise à jour d'un grade
	 * @param grade
	 */
	void mettreAJour(Grade grade);

	/**Obtention de tous les grades
	 * @return
	 */
	List<Grade> lister();

}
