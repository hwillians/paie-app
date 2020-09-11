package dev.paie.web.bulletinSalaire;

import java.time.LocalDate;
import java.util.List;

public class CreerBulletinSalaireResquestDto {

	private LocalDate dateCreation;
	private LocalDate debutPeriode;
	private LocalDate finPeriode;
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
	 * @return the debutPeriode
	 */
	public LocalDate getDebutPeriode() {
		return debutPeriode;
	}

	/**
	 * @param debutPeriode the debutPeriode to set
	 */
	public void setDebutPeriode(LocalDate debutPeriode) {
		this.debutPeriode = debutPeriode;
	}

	/**
	 * @return the finPeriode
	 */
	public LocalDate getFinPeriode() {
		return finPeriode;
	}

	/**
	 * @param finPeriode the finPeriode to set
	 */
	public void setFinPeriode(LocalDate finPeriode) {
		this.finPeriode = finPeriode;
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
