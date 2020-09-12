package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class CreerBulletinSalaireResquestDtoPost {

	@NotBlank
	private Integer perdiodeId;

	@NotBlank
	private Integer remunerationEmployeId;

	@NotBlank
	private BigDecimal primeExetionnelle;

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
	 * @return the remunerationEmployeId
	 */
	public Integer getRemunerationEmployeId() {
		return remunerationEmployeId;
	}

	/**
	 * @param remunerationEmployeId the remunerationEmployeId to set
	 */
	public void setRemunerationEmployeId(Integer remunerationEmployeId) {
		this.remunerationEmployeId = remunerationEmployeId;
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