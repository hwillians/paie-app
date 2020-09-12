package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;

public class CreerBulletinSalaireReponseDtoGet {

	private LocalDate dateCreation;
	private LocalTime heureCreation;
	private LocalDate debutPeriode;
	private LocalDate fintPeriode;
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
	public CreerBulletinSalaireReponseDtoGet(BulletinSalaire bs, Grade grade, String matricule,
			List<Cotisation> cotisationsNonImp, List<Cotisation> cotisationsImposab) {

		// ajoute de la date de creation
		dateCreation = bs.getDateCreation();

		heureCreation = bs.getHeureCreation();

		// ajoute de la peride
		debutPeriode = bs.getPeriode().getDateDebut();
		fintPeriode = bs.getPeriode().getDateFin();

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
	 * @return the heureCreation
	 */
	public LocalTime getHeureCreation() {
		return heureCreation;
	}

	/**
	 * @param heureCreation the heureCreation to set
	 */
	public void setHeureCreation(LocalTime heureCreation) {
		this.heureCreation = heureCreation;
	}

	/**
	 * @return the debutPeriode
	 */
	public LocalDate getDebutPeriode() {
		return debutPeriode;
	}

	/**
	 * @param debutPeriode the debutPeriode to set
	 */
	public void setDebutPeriode(LocalDate debutPeriode) {
		this.debutPeriode = debutPeriode;
	}

	/**
	 * @return the fintPeriode
	 */
	public LocalDate getFintPeriode() {
		return fintPeriode;
	}

	/**
	 * @param fintPeriode the fintPeriode to set
	 */
	public void setFintPeriode(LocalDate fintPeriode) {
		this.fintPeriode = fintPeriode;
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
