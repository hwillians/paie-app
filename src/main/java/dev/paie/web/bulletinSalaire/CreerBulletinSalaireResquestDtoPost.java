package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

public class CreerBulletinSalaireResquestDtoPost {

	@NotNull
	private Integer perdiodeId;

	@NotNull
	private Integer profilRemunerationId;

	@NotNull
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