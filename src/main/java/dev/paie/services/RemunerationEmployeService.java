package dev.paie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.exception.PaieException;
import dev.paie.repositories.EntrepriseRepository;
import dev.paie.repositories.GradeRepository;
import dev.paie.repositories.ProfilRemunerationRepository;
import dev.paie.repositories.RemunerationEmployeRepository;

@Service
public class RemunerationEmployeService {

	private RemunerationEmployeRepository employeRepository;
	private EntrepriseRepository entrepiseRepository;
	private ProfilRemunerationRepository profilRepository;
	private GradeRepository gradeRepository;

	/**
	 * @param employeRepository
	 * @param entRep
	 * @param pRep
	 * @param gRep
	 */
	public RemunerationEmployeService(RemunerationEmployeRepository employeRepository,
			EntrepriseRepository entrepiseRepository, ProfilRemunerationRepository profilRepository,
			GradeRepository gradeRepository) {
		this.employeRepository = employeRepository;
		this.entrepiseRepository = entrepiseRepository;
		this.profilRepository = profilRepository;
		this.gradeRepository = gradeRepository;
	}

	@Transactional
	public RemunerationEmploye creerEmploye(String matricule, Integer entrepriseId, Integer profilId, Integer gradeId) {

		List<String> messagesErreurs = new ArrayList<>();

		Optional<Entreprise> opEntreprise = entrepiseRepository.findById(entrepriseId);
		Optional<ProfilRemuneration> opProfilRem = profilRepository.findById(profilId);
		Optional<Grade> opGrade = gradeRepository.findById(gradeId);

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

		RemunerationEmploye emp = new RemunerationEmploye();
		emp.setMatricule(matricule);
		emp.setEntreprise(opEntreprise.get());
		emp.setProfilRemuneration(opProfilRem.get());
		emp.setGrade(opGrade.get());

		return employeRepository.save(emp);
	}

}
