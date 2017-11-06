package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
public class Cotisation {

	/** id : Integer */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** code : String */
	@Column
	private String code;
	/** libelle : String */
	@Column
	private String libelle;
	/** tauxSalarial : BigDecimal */
	@Column
	private BigDecimal tauxSalarial;
	/** tauxPatronal : BigDecimal */
	@Column
	private BigDecimal tauxPatronal;

	public Cotisation(String code, String libelle, BigDecimal tauxS, BigDecimal tauxP) {
		this.code = code;
		this.libelle = libelle;
		this.tauxSalarial = tauxS;
		this.tauxPatronal = tauxP;
	}

	/**
	 * Constructeur Jpa
	 */
	public Cotisation() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public BigDecimal getTauxSalarial() {
		return tauxSalarial;
	}

	public void setTauxSalarial(BigDecimal tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public BigDecimal getTauxPatronal() {
		return tauxPatronal;
	}

	public void setTauxPatronal(BigDecimal tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
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
		Cotisation rhs = (Cotisation) obj;
		return new EqualsBuilder().append(code, rhs.code).append(libelle, rhs.getLibelle()).isEquals()
				&& new CompareToBuilder().append(tauxSalarial, rhs.tauxSalarial).append(tauxPatronal, rhs.tauxPatronal)
						.toComparison() == 0;
	}

}
