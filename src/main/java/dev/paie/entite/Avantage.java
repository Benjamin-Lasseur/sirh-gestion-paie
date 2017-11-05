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
public class Avantage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;
	@Column
	private String nom;
	@Column
	private BigDecimal montant;
	@ManyToOne
	@JoinColumn(name = "ID_PROFIL")
	private ProfilRemuneration profil;

	public Avantage(String code, String nom, BigDecimal montant) {
		this.code = code;
		this.nom = nom;
		this.montant = montant;
	}

	public Avantage() {
		super();
	}

	public ProfilRemuneration getProfil() {
		return profil;
	}

	public void setProfil(ProfilRemuneration profil) {
		this.profil = profil;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
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
		Avantage rhs = (Avantage) obj;
		return new EqualsBuilder().append(code, rhs.getCode()).append(nom, rhs.getNom()).isEquals()
				&& new CompareToBuilder().append(montant, rhs.getMontant()).toComparison() == 0;
	}
}
