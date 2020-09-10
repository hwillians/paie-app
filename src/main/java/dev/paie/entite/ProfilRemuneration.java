package dev.paie.entite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ProfilRemuneration {

	@Id
	private Integer id;
	private String code;

	@ManyToMany
	private List<Cotisation> cotisations;

	@ManyToMany
	private List<Avantage> avantages;

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

	public List<Cotisation> getCotisations() {
		return cotisations;
	}

	public void setCotisations(List<Cotisation> cotisations) {
		this.cotisations = cotisations;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
