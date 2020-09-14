package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cotisation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String libelle;
	@Column(precision = 7, scale = 6)
	private BigDecimal tauxSalarial;
	@Column(precision = 7, scale = 6)
	private BigDecimal tauxPatronal;
	private Boolean imposable = false;

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

	public Boolean getImposable() {
		return imposable;
	}

	public void setImposable(Boolean imposable) {
		this.imposable = imposable;
	}
}
