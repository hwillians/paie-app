package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;

public class CreerBulletinReponseDto {

	private LocalDateTime dateCreation;
	private Periode periode;
	private String matricule;
	private BigDecimal salaireBrut;
	private BigDecimal netImposable;
	private BigDecimal netAPayer;

	/**
	 * @param bs
	 * @param grade
	 * @param matricule
	 * @param cotisationsNonImp
	 * @param cotisationsImposab
	 */
	public CreerBulletinReponseDto(BulletinSalaire bs, Grade grade, String matricule,
			List<Cotisation> cotisationsNonImp, List<Cotisation> cotisationsImposab) {

		// ajoute de la date de creation
		dateCreation = LocalDateTime.now();

		// ajoute de la peride
		periode = bs.getPeriode();

		// ajoute de la matricule
		this.matricule = matricule;

		// Calcule du Salaire Brut
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		salaireBrut = salaireBase.add(bs.getPrimeExceptionnelle());

		// Calcule du Net Imposable
		BigDecimal totalRetenueSalaire = BigDecimal.ZERO;
		for (Cotisation c : cotisationsNonImp) {
			totalRetenueSalaire.add(c.getTauxSalarial().multiply(salaireBrut));
		}
		netImposable = salaireBrut.subtract(totalRetenueSalaire);

		// Calcule du Net à peyer
		BigDecimal subtrahend = BigDecimal.ZERO;
		;
		for (Cotisation c : cotisationsImposab) {
			subtrahend.add(c.getTauxSalarial().multiply(salaireBrut));
		}
		netAPayer = netImposable.subtract(subtrahend);
	}

	/**
	 * @return the dateCreation
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the periode
	 */
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

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
	 * @return the salaireBrut
	 */
	public BigDecimal getSalaireBrut() {
		return salaireBrut;
	}

	/**
	 * @param salaireBrut the salaireBrut to set
	 */
	public void setSalaireBrut(BigDecimal salaireBrut) {
		this.salaireBrut = salaireBrut;
	}

	/**
	 * @return the netImposable
	 */
	public BigDecimal getNetImposable() {
		return netImposable;
	}

	/**
	 * @param netImposable the netImposable to set
	 */
	public void setNetImposable(BigDecimal netImposable) {
		this.netImposable = netImposable;
	}

	/**
	 * @return the netAPayer
	 */
	public BigDecimal getNetAPayer() {
		return netAPayer;
	}

	/**
	 * @param netAPayer the netAPayer to set
	 */
	public void setNetAPayer(BigDecimal netAPayer) {
		this.netAPayer = netAPayer;
	}

}
