package dev.paie.web.remunerationEmploye;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreerRemunerationEmployeResquestDto {

	@NotBlank
	@Size(min = 5)
	private String matricule;

	@NotBlank
	private Integer entrepriseId;

	@NotBlank
	private Integer profilId;

	@NotBlank
	private Integer gradeId;

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
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
