package dev.paie.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repositories.BulletinRepositorie;
import dev.paie.repositories.PeriodeRepository;
import dev.paie.repositories.RemunerationEmployeRepository;

@Service
public class BulletinService {

	private BulletinRepositorie bullRep;
	private RemunerationEmployeRepository empRep;
	private PeriodeRepository periodeRep;

	/**
	 * @param bullRep
	 */
	public BulletinService(BulletinRepositorie bullRep, RemunerationEmployeRepository empRep,
			PeriodeRepository periodeRep) {
		this.empRep = empRep;
		this.empRep = empRep;
		this.periodeRep = periodeRep;

	}

	public List<BulletinSalaire> listerBulletins(LocalDate date, Integer periodeId, List<String> matricules) {

		return bullRep.findAllByDateCreationAndPeriodeAndMatricules(date, periodeId, matricules);
	}

	List<String> messagesErreurs = new ArrayList<>();

	public Grade getGrade(Integer id) {
		Grade grade = bullRep.findGradeByBulletinId(id);

		return grade;
	}

	public List<Cotisation> listerCotisationNonImp() {

		return bullRep.findCotisationsNonImp();
	}

	public List<Cotisation> listerCotisationImposable() {

		return bullRep.findCotisationsImp();
	}

	public String getMatricule(Integer id) {

		return bullRep.findMatriculeByBulletinId(id);
	}

	@Transactional
	public BulletinSalaire creerBulletin(Integer entrepriseId, Integer perdiodeId, Integer profilRemunerationId,
			BigDecimal primeExetionnelle) {
		List<String> messagesErreurs = new ArrayList<>();
		Optional<RemunerationEmploye> opEmp = empRep.findById(profilRemunerationId);
		if (opEmp.isEmpty()) {
			messagesErreurs.add("L'id " + profilRemunerationId + " ne correspond à aucun profil de Remuneration");
		}

		Optional<Periode> opPeriode = periodeRep.findById(perdiodeId);
		if (opPeriode.isEmpty()) {
			messagesErreurs.add("L'id " + perdiodeId + " ne correspond à aucune periode enregistrée");
		}

		BulletinSalaire bulletin = new BulletinSalaire();

		bulletin.setRemunerationEmploye(opEmp.get());
		bulletin.setPeriode(opPeriode.get());
		bulletin.setPrimeExceptionnelle(primeExetionnelle);
		bulletin.setDateCreation(LocalDateTime.now());
		return bullRep.save(bulletin);

	}

}
