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
import dev.paie.exception.PaieException;
import dev.paie.repositories.BulletinSalaireRepositorie;
import dev.paie.repositories.PeriodeRepository;
import dev.paie.repositories.RemunerationEmployeRepository;

@Service
public class BulletinService {

	private BulletinSalaireRepositorie bullRep;
	private RemunerationEmployeRepository empRep;
	private PeriodeRepository periodeRep;

	/**
	 * @param bullRep
	 * @param empRep
	 * @param periodeRep
	 * @param messagesErreurs
	 */
	public BulletinService(BulletinSalaireRepositorie bullRep, RemunerationEmployeRepository empRep,
			PeriodeRepository periodeRep) {
		this.bullRep = bullRep;
		this.empRep = empRep;
		this.periodeRep = periodeRep;

	}

	public List<BulletinSalaire> listerBulletins(LocalDate date, Integer periodeId, List<String> matricules) {

		return bullRep.findAllByDateCreationAndPeriodeAndMatricules(date, periodeId, matricules);
	}

	List<String> messagesErreurs = new ArrayList<>();

	public Grade getGrade(Integer id) {
		Optional<BulletinSalaire> opt = bullRep.findGradeByBulletinId(id);
		Grade grade = opt.get().getRemunerationEmploye().getGrade();
		return grade;
	}

	public List<Cotisation> listerCotisationNonImp() {

		return bullRep.findCotisationsNonImp();
	}

	public List<Cotisation> listerCotisationImposable() {

		return bullRep.findCotisationsImp();
	}

	public String getMatricule(Integer id) {

		Optional<BulletinSalaire> opt = bullRep.findMatriculeByBulletinId(id);
		return opt.get().getRemunerationEmploye().getMatricule();
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

		if (!messagesErreurs.isEmpty()) {
			throw new PaieException(messagesErreurs);
		}

		BulletinSalaire bulletin = new BulletinSalaire(opEmp.get(), opPeriode.get(), primeExetionnelle,
				LocalDateTime.now());

		return bullRep.save(bulletin);

	}

}
