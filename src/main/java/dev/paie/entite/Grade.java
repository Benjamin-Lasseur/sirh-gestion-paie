package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import dev.paie.util.PaieUtils;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column
	private BigDecimal nbHeuresBase;
	@Column(precision = 19, scale = 6)
	private BigDecimal tauxBase;

	public String getSalaireAnnuel() {
		return new PaieUtils().formaterBigDecimal(nbHeuresBase.multiply(tauxBase).multiply(new BigDecimal("12")));
	}

	public Grade() {
		super();
	}

	public Grade(String code, BigDecimal nbHeuresBase, BigDecimal tauxBase) {
		this.code = code;
		this.nbHeuresBase = nbHeuresBase;
		this.tauxBase = tauxBase;
	}

	public String toString() {
		return this.code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}

	public void setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
	}

	public BigDecimal getTauxBase() {
		return tauxBase;
	}

	public void setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Grade rhs = (Grade) obj;
		return new EqualsBuilder().append(code, rhs.code).isEquals() && new CompareToBuilder()
				.append(nbHeuresBase, rhs.nbHeuresBase).append(tauxBase, rhs.tauxBase).toComparison() == 0;
	}

}
