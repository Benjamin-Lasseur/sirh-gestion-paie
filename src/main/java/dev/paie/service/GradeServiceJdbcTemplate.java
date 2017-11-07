package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.paie.entite.Grade;

@Repository
public class GradeServiceJdbcTemplate implements GradeService {

	/** jdbcTemplate : JdbcTemplate */
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see dev.paie.service.GradeService#sauvegarder(dev.paie.entite.Grade)
	 */
	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO GRADE(CODE,NBHEURESBASE,TAUXBASE) VALUES(?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(),
				nouveauGrade.getTauxBase());

	}

	/* (non-Javadoc)
	 * @see dev.paie.service.GradeService#mettreAJour(dev.paie.entite.Grade)
	 */
	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE GRADE SET CODE=?, NBHEURESBASE=?, TAUXBASE=? WHERE ID=?";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());

	}

	/* (non-Javadoc)
	 * @see dev.paie.service.GradeService#lister()
	 */
	@Override
	public List<Grade> lister() {
		String sql = "SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}

	/**
	 * @param dataSource
	 */
	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
