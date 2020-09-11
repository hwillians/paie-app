package dev.paie.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.repositories.BulletinRepositorie;

@Service
public class BulletinService {

	private BulletinRepositorie bullRep;

	/**
	 * @param bullRep
	 */
	public BulletinService(BulletinRepositorie bullRep) {
		this.bullRep = bullRep;
	}

	public List<BulletinSalaire> listerBulletins(LocalDate date, LocalDate debutPeriode, LocalDate finPeriode,
			List<String> matricules) {

		return bullRep.findAllByDateCreationAndPeriodeAndMatricules(date, debutPeriode, finPeriode, matricules);
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

}
