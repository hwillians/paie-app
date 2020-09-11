package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sun.istack.NotNull;

public class CreerBulletinSalaireResquestDtoGet {

	@NotNull
	private Integer id;
	@NotNull
	private Integer entrepriseId;
	@NotNull
	private Integer perdiodeId;
	@NotNull
	private Integer profilRemunerationId;
	@NotNull
	private LocalDate dateCreation;
	@NotNull
	private BigDecimal primeExetionnelle;
	@NotNull
	private List<String> matricules;

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
	 * @return the profilRemunerationId
	 */
	public Integer getProfilRemunerationId() {
		return profilRemunerationId;
	}

	/**
	 * @param profilRemunerationId the profilRemunerationId to set
	 */
	public void setProfilRemunerationId(Integer profilRemunerationId) {
		this.profilRemunerationId = profilRemunerationId;
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

	/**
	 * @return the primeExetionnelle
	 */
	public BigDecimal getPrimeExetionnelle() {
		return primeExetionnelle;
	}

	/**
	 * @param primeExetionnelle the primeExetionnelle to set
	 */
	public void setPrimeExetionnelle(BigDecimal primeExetionnelle) {
		this.primeExetionnelle = primeExetionnelle;
	}

}