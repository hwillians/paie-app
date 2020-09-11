package dev.paie.web.bulletinSalaire;

import java.time.LocalDate;
import java.util.List;

import dev.paie.entite.Periode;

public class CreerBulletinSalaireResquestDto {

	private LocalDate dateCreation;
	private Periode periode;
	private List<String> matricules;

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
	 * @return the periode
	 */
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	/**
	 * @return the matricules
	 */
	public List<String> getMatricules() {
		return matricules;
	}

	/**
	 * @param matricules the matricules to set
	 */
	public void setMatricules(List<String> matricules) {
		this.matricules = matricules;
	}

}
