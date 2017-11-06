package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String code;

	@ManyToMany
	@JoinTable(name = "PRO_COT_N_IMP", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_N_IMP", referencedColumnName = "ID"))
	private List<Cotisation> cotisationsNonImposables;

	@ManyToMany
	@JoinTable(name = "PRO_COT_IMP", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_IMP", referencedColumnName = "ID"))
	private List<Cotisation> cotisationsImposables;

	@ManyToMany
	@JoinTable(name = "PRO_AVA", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_AVA", referencedColumnName = "ID"))
	private List<Avantage> avantages;

	public ProfilRemuneration(String code, List<Cotisation> cotImp, List<Cotisation> cotNonImp,
			List<Avantage> avantages) {
		this.code = code;
		this.cotisationsImposables = cotImp;
		this.cotisationsNonImposables = cotNonImp;
		this.avantages = avantages;
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
