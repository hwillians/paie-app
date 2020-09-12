package dev.paie.entite;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BulletinSalaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private RemunerationEmploye remunerationEmploye;

	@ManyToOne
	private Periode periode;

	private BigDecimal primeExceptionnelle;

	private LocalDate dateCreation;

	private LocalTime heureCreation;

	@Override
	public String toString() {
		return "BulletinSalaire N° " + id + "  remunerationEmploye=" + remunerationEmploye.getMatricule()
				+ ", periode [ du" + periode.getDateDebut() + "au" + periode.getDateFin() + "], dateCreation="
				+ dateCreation + "]";
	}

	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}

	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateCreation
	 */
	public LocalDate getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the heureCreation
	 */
	public LocalTime getHeureCreation() {
		return heureCreation;
	}

	/**
	 * @param heureCreation the heureCreation to set
	 */
	public void setHeureCreation(LocalTime heureCreation) {
		this.heureCreation = heureCreation;
	}

}
