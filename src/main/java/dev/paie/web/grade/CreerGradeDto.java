package dev.paie.web.grade;

import java.math.BigDecimal;

public class CreerGradeDto {

	private String code;
	private BigDecimal salaireAnnuel;

	/**
	 * @param code
	 * @param salaireAnnuel
	 */
	public CreerGradeDto(String code, BigDecimal nbHeuresBas, BigDecimal tauxBase) {

		this.code = code;
		this.salaireAnnuel = nbHeuresBas.multiply(tauxBase.multiply(new BigDecimal("12")));
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the salaireAnnuel
	 */
	public BigDecimal getSalaireAnnuel() {
		return salaireAnnuel;
	}

	/**
	 * @param salaireAnnuel the salaireAnnuel to set
	 */
	public void setSalaireAnnuel(BigDecimal salaireAnnuel) {
		this.salaireAnnuel = salaireAnnuel;
	}

}
