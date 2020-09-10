package dev.paie.web.employe;

import dev.paie.entite.Employe;

public class CreerEmployeReponseDto {

	private Integer id;
	private Integer Matricule;
	private String Emtreprise;
	private String Grade;
	private String ProfilRe;

	public CreerEmployeReponseDto(Employe emp) {
		id = emp.getId();
		Matricule = emp.getMatricule();
		Emtreprise = emp.getEntreprise().toString();
		Grade = emp.getGrade().toString();
		ProfilRe = emp.getProfilRemuneration().toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the matricule
	 */
	public Integer getMatricule() {
		return Matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(Integer matricule) {
		Matricule = matricule;
	}

	/**
	 * @return the nomEmtreprise
	 */
	public String getNomEmtreprise() {
		return Emtreprise;
	}

	/**
	 * @param nomEmtreprise the nomEmtreprise to set
	 */
	public void setNomEmtreprise(String nomEmtreprise) {
		this.Emtreprise = nomEmtreprise;
	}

	/**
	 * @return the codeGrade
	 */
	public String getCodeGrade() {
		return Grade;
	}

	/**
	 * @param codeGrade the codeGrade to set
	 */
	public void setCodeGrade(String codeGrade) {
		this.Grade = codeGrade;
	}

	/**
	 * @return the codeProfilRe
	 */
	public String getCodeProfilRe() {
		return ProfilRe;
	}

	/**
	 * @param codeProfilRe the codeProfilRe to set
	 */
	public void setCodeProfilRe(String codeProfilRe) {
		this.ProfilRe = codeProfilRe;
	}

}
