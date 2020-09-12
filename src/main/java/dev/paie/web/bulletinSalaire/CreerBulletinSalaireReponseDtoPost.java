package dev.paie.web.bulletinSalaire;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;

public class CreerBulletinSalaireReponseDtoPost {

	private LocalDate debutPeriode;
	private LocalDate fintPeriode;
	private String matricule;
	private String entrepriseDenomination;
	private String entrepriseSiret;
	private BigDecimal baseSalarial;
	private BigDecimal tauxSalarial;
	private BigDecimal montantSalarial;
	private BigDecimal primeExceptionnelle;
	private BigDecimal salaireBrut;
	private BigDecimal tauxSalarialEP01;
	private BigDecimal montantSalarialEP01;
	private BigDecimal tauxPatronalEP01;
	private BigDecimal montantPatronalEP01;
	private BigDecimal tauxSalarialEP02;
	private BigDecimal montantSalarialEP02;
	private BigDecimal tauxSalarialSP01;
	private BigDecimal montantSalarialSP01;
	private BigDecimal tauxSalarialSP02;
	private BigDecimal montantSalarialSP02;
	private BigDecimal totalRetenueSalarie;
	private BigDecimal totalRetenuePatronal;
	private BigDecimal netImposable;
	private BigDecimal netAPayer;

	/**
	 * @param bs
	 * @param grade
	 * @param matricule
	 * @param entreprise
	 * @param cotisationsNonImp
	 * @param cotisationsImposab
	 */
	public CreerBulletinSalaireReponseDtoPost(BulletinSalaire bs, Grade grade, String matricule, Entreprise entreprise,
			List<Cotisation> cotisationsNonImp, List<Cotisation> cotisationsImposab) {

		System.out.println(cotisationsNonImp.size());
		System.out.println(cotisationsImposab.size());
		// ajoute de la peride
		debutPeriode = bs.getPeriode().getDateDebut();
		fintPeriode = bs.getPeriode().getDateFin();

		// ajoute de la matricule
		this.matricule = matricule;

		// ajoute Entreprise
		entrepriseDenomination = entreprise.getDenomination();
		entrepriseSiret = entreprise.getSiret();

		// Calcul Salaire de base
		baseSalarial = grade.getNbHeuresBase();
		tauxSalarial = grade.getTauxBase();

		montantSalarial = baseSalarial.multiply(tauxSalarial);

		primeExceptionnelle = bs.getPrimeExceptionnelle();

		// Calcul Salaire brut
		salaireBrut = montantSalarial.add(primeExceptionnelle);

		// Calcule cotizations
		Cotisation EP01 = cotisationsNonImp.stream().filter(c -> c.getId().equals(3)).findAny().get();

		tauxSalarialEP01 = EP01.getTauxSalarial() == null ? BigDecimal.ZERO : EP01.getTauxSalarial();

		montantSalarialEP01 = montantSalarial.multiply(tauxSalarialEP01);

		tauxPatronalEP01 = EP01.getTauxPatronal() == null ? BigDecimal.ZERO : EP01.getTauxPatronal();

		montantPatronalEP01 = montantSalarial.multiply(tauxPatronalEP01);

		Cotisation EP02 = cotisationsNonImp.stream().filter(c -> c.getId().equals(4)).findAny().get();

		tauxSalarialEP02 = EP02.getTauxSalarial() == null ? BigDecimal.ZERO : EP02.getTauxSalarial();
		System.out.println(EP02.getTauxSalarial());
		montantSalarialEP02 = montantSalarial.multiply(tauxSalarialEP02);

		// Calcule du Net Imposable
		BigDecimal totalRetenueSalaire = BigDecimal.ZERO;

		for (Cotisation c : cotisationsNonImp) {
			if (c.getTauxSalarial() != null) {
				totalRetenueSalaire = totalRetenueSalaire.add(salaireBrut.multiply(c.getTauxSalarial()));
			}
		}

		netImposable = salaireBrut.subtract(totalRetenueSalaire);

		Cotisation SP01 = cotisationsImposab.stream().filter(c -> c.getId().equals(3)).findAny().get();

		tauxSalarialSP01 = SP01.getTauxSalarial() == null ? BigDecimal.ZERO : SP01.getTauxSalarial();

		montantSalarialSP01 = montantSalarial.multiply(tauxSalarialSP01);
		System.out.println("SP01.getTauxSalarial() " + SP01.getTauxSalarial());
		Cotisation SP02 = cotisationsImposab.stream().filter(c -> c.getId().equals(4)).findAny().get();

		tauxSalarialSP02 = SP02.getTauxSalarial() == null ? BigDecimal.ZERO : SP02.getTauxSalarial();
		System.out.println("SP02.getTauxSalarial() " + SP02.getTauxSalarial());

		montantSalarialSP02 = montantSalarial.multiply(tauxSalarialSP02);

		// Calcule du Net à peyer
		BigDecimal subtrahend = BigDecimal.ZERO;
		netAPayer = BigDecimal.ZERO;
		totalRetenueSalarie = BigDecimal.ZERO;
		for (Cotisation c : cotisationsImposab) {
			if (c.getTauxSalarial() != null) {
				subtrahend = c.getTauxSalarial().multiply(salaireBrut);
				netAPayer = netImposable.subtract(subtrahend);

				totalRetenueSalarie = totalRetenueSalarie.add(c.getTauxSalarial());
			}

		}

		totalRetenuePatronal = BigDecimal.ZERO;

		for (Cotisation c : cotisationsImposab) {
			if (c.getTauxPatronal() != null) {

				totalRetenuePatronal = totalRetenuePatronal.add(c.getTauxPatronal());
			}

		}

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
	 * @return the entrepriseDenomination
	 */
	public String getEntrepriseDenomination() {
		return entrepriseDenomination;
	}

	/**
	 * @param entrepriseDenomination the entrepriseDenomination to set
	 */
	public void setEntrepriseDenomination(String entrepriseDenomination) {
		this.entrepriseDenomination = entrepriseDenomination;
	}

	/**
	 * @return the entrepriseSiret
	 */
	public String getEntrepriseSiret() {
		return entrepriseSiret;
	}

	/**
	 * @param entrepriseSiret the entrepriseSiret to set
	 */
	public void setEntrepriseSiret(String entrepriseSiret) {
		this.entrepriseSiret = entrepriseSiret;
	}

	/**
	 * @return the baseSalarial
	 */
	public BigDecimal getBaseSalarial() {
		return baseSalarial;
	}

	/**
	 * @param baseSalarial the baseSalarial to set
	 */
	public void setBaseSalarial(BigDecimal baseSalarial) {
		this.baseSalarial = baseSalarial;
	}

	/**
	 * @return the tauxSalarial
	 */
	public BigDecimal getTauxSalarial() {
		return tauxSalarial;
	}

	/**
	 * @param tauxSalarial the tauxSalarial to set
	 */
	public void setTauxSalarial(BigDecimal tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	/**
	 * @return the montantSalarial
	 */
	public BigDecimal getMontantSalarial() {
		return montantSalarial;
	}

	/**
	 * @param montantSalarial the montantSalarial to set
	 */
	public void setMontantSalarial(BigDecimal montantSalarial) {
		this.montantSalarial = montantSalarial;
	}

	/**
	 * @return the primeExceptionnelle
	 */
	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	/**
	 * @param primeExceptionnelle the primeExceptionnelle to set
	 */
	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
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
	 * @return the tauxSalarialEP01
	 */
	public BigDecimal getTauxSalarialEP01() {
		return tauxSalarialEP01;
	}

	/**
	 * @param tauxSalarialEP01 the tauxSalarialEP01 to set
	 */
	public void setTauxSalarialEP01(BigDecimal tauxSalarialEP01) {
		this.tauxSalarialEP01 = tauxSalarialEP01;
	}

	/**
	 * @return the montantSalarialEP01
	 */
	public BigDecimal getMontantSalarialEP01() {
		return montantSalarialEP01;
	}

	/**
	 * @param montantSalarialEP01 the montantSalarialEP01 to set
	 */
	public void setMontantSalarialEP01(BigDecimal montantSalarialEP01) {
		this.montantSalarialEP01 = montantSalarialEP01;
	}

	/**
	 * @return the tauxPatronalEP01
	 */
	public BigDecimal getTauxPatronalEP01() {
		return tauxPatronalEP01;
	}

	/**
	 * @param tauxPatronalEP01 the tauxPatronalEP01 to set
	 */
	public void setTauxPatronalEP01(BigDecimal tauxPatronalEP01) {
		this.tauxPatronalEP01 = tauxPatronalEP01;
	}

	/**
	 * @return the montantPatronalEP01
	 */
	public BigDecimal getMontantPatronalEP01() {
		return montantPatronalEP01;
	}

	/**
	 * @param montantPatronalEP01 the montantPatronalEP01 to set
	 */
	public void setMontantPatronalEP01(BigDecimal montantPatronalEP01) {
		this.montantPatronalEP01 = montantPatronalEP01;
	}

	/**
	 * @return the tauxSalarialEP02
	 */
	public BigDecimal getTauxSalarialEP02() {
		return tauxSalarialEP02;
	}

	/**
	 * @param tauxSalarialEP02 the tauxSalarialEP02 to set
	 */
	public void setTauxSalarialEP02(BigDecimal tauxSalarialEP02) {
		this.tauxSalarialEP02 = tauxSalarialEP02;
	}

	/**
	 * @return the montantSalarialEP02
	 */
	public BigDecimal getMontantSalarialEP02() {
		return montantSalarialEP02;
	}

	/**
	 * @param montantSalarialEP02 the montantSalarialEP02 to set
	 */
	public void setMontantSalarialEP02(BigDecimal montantSalarialEP02) {
		this.montantSalarialEP02 = montantSalarialEP02;
	}

	/**
	 * @return the tauxSalarialSP01
	 */
	public BigDecimal getTauxSalarialSP01() {
		return tauxSalarialSP01;
	}

	/**
	 * @param tauxSalarialSP01 the tauxSalarialSP01 to set
	 */
	public void setTauxSalarialSP01(BigDecimal tauxSalarialSP01) {
		this.tauxSalarialSP01 = tauxSalarialSP01;
	}

	/**
	 * @return the montantSalarialSP01
	 */
	public BigDecimal getMontantSalarialSP01() {
		return montantSalarialSP01;
	}

	/**
	 * @param montantSalarialSP01 the montantSalarialSP01 to set
	 */
	public void setMontantSalarialSP01(BigDecimal montantSalarialSP01) {
		this.montantSalarialSP01 = montantSalarialSP01;
	}

	/**
	 * @return the tauxSalarialSP02
	 */
	public BigDecimal getTauxSalarialSP02() {
		return tauxSalarialSP02;
	}

	/**
	 * @param tauxSalarialSP02 the tauxSalarialSP02 to set
	 */
	public void setTauxSalarialSP02(BigDecimal tauxSalarialSP02) {
		this.tauxSalarialSP02 = tauxSalarialSP02;
	}

	/**
	 * @return the montantSalarialSP02
	 */
	public BigDecimal getMontantSalarialSP02() {
		return montantSalarialSP02;
	}

	/**
	 * @param montantSalarialSP02 the montantSalarialSP02 to set
	 */
	public void setMontantSalarialSP02(BigDecimal montantSalarialSP02) {
		this.montantSalarialSP02 = montantSalarialSP02;
	}

	/**
	 * @return the totalRetenueSalarie
	 */
	public BigDecimal getTotalRetenueSalarie() {
		return totalRetenueSalarie;
	}

	/**
	 * @param totalRetenueSalarie the totalRetenueSalarie to set
	 */
	public void setTotalRetenueSalarie(BigDecimal totalRetenueSalarie) {
		this.totalRetenueSalarie = totalRetenueSalarie;
	}

	/**
	 * @return the totalRetenuePatronal
	 */
	public BigDecimal getTotalRetenuePatronal() {
		return totalRetenuePatronal;
	}

	/**
	 * @param totalRetenuePatronal the totalRetenuePatronal to set
	 */
	public void setTotalRetenuePatronal(BigDecimal totalRetenuePatronal) {
		this.totalRetenuePatronal = totalRetenuePatronal;
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
