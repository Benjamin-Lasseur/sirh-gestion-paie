package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;

	@OneToMany(mappedBy = "profil")
	private List<Cotisation> cotisationsNonImposables;

	@OneToMany(mappedBy = "profil")
	private List<Cotisation> cotisationsImposables;

	@OneToMany(mappedBy = "profil")
	private List<Avantage> avantages;

	public ProfilRemuneration(String code, List<Cotisation> cotImp, List<Cotisation> cotNonImp,
			List<Avantage> avantages) {
		this.code = code;
		this.cotisationsImposables = cotImp;
		this.cotisationsImposables.forEach(cot -> cot.setProfil(this));
		this.cotisationsNonImposables = cotNonImp;
		this.cotisationsNonImposables.forEach(cot -> cot.setProfil(this));
		this.avantages = avantages;
		this.avantages.forEach(av -> av.setProfil(this));
	}

	public ProfilRemuneration() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
