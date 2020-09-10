package dev.paie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.paie.entite.Employe;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.exception.PaieException;
import dev.paie.repositories.EmployeRepository;
import dev.paie.repositories.EntrepriseRepository;
import dev.paie.repositories.ProfilRemuneratioRepository;

@Service
public class EmployeService {

	private EmployeRepository employeRepository;
	private EntrepriseRepository entRep;
	private ProfilRemuneratioRepository pRep;
	private GradeReposirtory gRep;

	/**
	 * @param employeRepository
	 * @param entRep
	 * @param pRep
	 * @param gRep
	 */
	public EmployeService(EmployeRepository employeRepository, EntrepriseRepository entRep,
			ProfilRemuneratioRepository pRep, GradeReposirtory gRep) {
		this.employeRepository = employeRepository;
		this.entRep = entRep;
		this.pRep = pRep;
		this.gRep = gRep;
	}

	@Transactional
	public Employe creerEmploye(Integer matricule, Integer entrepriseId, Integer profilId, Integer gradeId) {

		List<String> messagesErreurs = new ArrayList<>();

		Optional<Entreprise> opEntreprise = entRep.findById(entrepriseId);
		Optional<ProfilRemuneration> opProfilRem = pRep.findById(profilId);
		Optional<Grade> opGrade = gRep.findById(gradeId);

		if (!employeRepository.findByMatricule(matricule).isEmpty()) {
			messagesErreurs.add("La matricule est déjà asignée à une autre employé");
		}

		if (opEntreprise.isEmpty()) {
			messagesErreurs.add("L'id" + entrepriseId + " ne correspond à aucune Entreprise");
		}

		if (opProfilRem.isEmpty()) {
			messagesErreurs.add("L'id" + profilId + " ne correspond à aucun Profil de remuneration");
		}
		if (opGrade.isEmpty()) {
			messagesErreurs.add("L'id" + gradeId + " ne correspond à aucun Grade");
		}

		if (!messagesErreurs.isEmpty()) {
			throw new PaieException(messagesErreurs);
		}

		Employe emp = new Employe();
		emp.setMatricule(matricule);
		emp.setEntreprise(opEntreprise.get());
		emp.setProfilRemuneration(opProfilRem.get());
		emp.setGrade(opGrade.get());

		return employeRepository.save(emp);
	}

}
