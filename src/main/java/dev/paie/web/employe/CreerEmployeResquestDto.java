package dev.paie.web.employe;

import com.sun.istack.NotNull;

public class CreerEmployeResquestDto {

	@NotNull
	private Integer matricule;

	@NotNull
	private Integer entrepriseId;

	@NotNull
	private Integer profilId;

	@NotNull
	private Integer gradeId;

	/**
	 * @return the matricule
	 */
	public Integer getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the entrepriseId
	 */
	public Integer getEntrepriseId() {
		return entrepriseId;
	}

	/**
	 * @param entrepriseId the entrepriseId to set
	 */
	public void setEntrepriseId(Integer entrepriseId) {
		this.entrepriseId = entrepriseId;
	}

	/**
	 * @return the profilId
	 */
	public Integer getProfilId() {
		return profilId;
	}

	/**
	 * @param profilId the profilId to set
	 */
	public void setProfilId(Integer profilId) {
		this.profilId = profilId;
	}

	/**
	 * @return the gradeId
	 */
	public Integer getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

}
