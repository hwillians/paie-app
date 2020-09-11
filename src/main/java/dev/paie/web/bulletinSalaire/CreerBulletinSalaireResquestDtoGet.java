package dev.paie.web.bulletinSalaire;

import java.time.LocalDate;
import java.util.List;

import com.sun.istack.NotNull;

public class CreerBulletinSalaireResquestDtoGet {

	@NotNull
	private Integer perdiodeId;
	@NotNull
	private LocalDate dateCreation;
	@NotNull
	private List<String> matricules;

	/**
	 * @return the perdiodeId
	 */
	public Integer getPerdiodeId() {
		return perdiodeId;
	}

	/**
	 * @param perdiodeId the perdiodeId to set
	 */
	public void setPerdiodeId(Integer perdiodeId) {
		this.perdiodeId = perdiodeId;
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