package dev.paie.web.entreprise;

public class CrerEntrepriseDto {

	private String siret;
	private String denomination;

	/**
	 * @param siret
	 * @param denomination
	 */
	public CrerEntrepriseDto(String siret, String denomination) {
		this.siret = siret;
		this.denomination = denomination;
	}

	/**
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * @param siret the siret to set
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}

	/**
	 * @return the denomination
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

}
