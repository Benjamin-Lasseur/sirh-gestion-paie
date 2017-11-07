package dev.paie.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.paie.entite.Grade;

public class GradeMapper implements RowMapper<Grade> {

	/* 
	 * Mappage de l'objet Grade
	 */
	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		Grade grade = new Grade();
		grade.setCode(rs.getString("CODE"));
		grade.setId(rs.getInt("ID"));
		grade.setNbHeuresBase(BigDecimal.valueOf(rs.getFloat("NBHEURESBASE")));
		grade.setTauxBase(BigDecimal.valueOf(rs.getFloat("TAUXBASE")));
		return grade;
	}

}
