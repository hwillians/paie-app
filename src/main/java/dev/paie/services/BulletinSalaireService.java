package dev.paie.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.exception.PaieException;
import dev.paie.repositories.BulletinSalaireRepositorie;
import dev.paie.repositories.PeriodeRepository;
import dev.paie.repositories.RemunerationEmployeRepository;

@Service
public class BulletinSalaireService {

	private BulletinSalaireRepositorie bulletinSalaireRepositorie;
	private RemunerationEmployeRepository remunerationEmployeRepository;
	private PeriodeRepository periodeRepository;

	/**
	 * @param bullRep
	 * @param empRep
	 * @param periodeRep
	 * @param messagesErreurs
	 */
	public BulletinSalaireService(BulletinSalaireRepositorie bulletinSalaireRepositorie,
			RemunerationEmployeRepository remunerationEmployeRepository, PeriodeRepository periodeRepository) {
		this.bulletinSalaireRepositorie = bulletinSalaireRepositorie;
		this.remunerationEmployeRepository = remunerationEmployeRepository;
		this.periodeRepository = periodeRepository;

	}

	public List<BulletinSalaire> listerBulletins(LocalDate date, Integer periodeId, List<String> matricules) {

		return bulletinSalaireRepositorie.findAllByDateCreationAndPeriodeAndMatricules(date, periodeId, matricules);

	}

	public Grade getGrade(Integer id) {
		Optional<BulletinSalaire> opt = bulletinSalaireRepositorie.findGradeByBulletinId(id);
		return opt.orElseThrow(() -> new RuntimeException("erreur : optention Grade")).getRemunerationEmploye()
				.getGrade();
	}

	public List<Cotisation> listerCotisation(Integer id) {
		return bulletinSalaireRepositorie.findCotisations(id)
				.orElseThrow(() -> new RuntimeException("erreur : optention Cotosations Imposables"))
				.getRemunerationEmploye().getProfilRemuneration().getCotisations();
	}

	public String getMatricule(Integer id) {
		Optional<BulletinSalaire> opt = bulletinSalaireRepositorie.findRemunerationEmployeByBulletinId(id);
		return opt.orElseThrow(() -> new RuntimeException("erreur : optention Matricule")).getRemunerationEmploye()
				.getMatricule();
	}

	public Entreprise getEnterprise(Integer id) {
		Optional<BulletinSalaire> opt = bulletinSalaireRepositorie.findRemunerationEmployeByBulletinId(id);
		return opt.orElseThrow(() -> new RuntimeException("erreur : optention Enterprise")).getRemunerationEmploye()
				.getEntreprise();
	}

	@Transactional
	public BulletinSalaire creerBulletin(Integer periodeId, Integer profilRemunerationId,
			BigDecimal primeExetionnelle) {
		List<String> messagesErreurs = new ArrayList<>();
		Optional<RemunerationEmploye> opEmp = remunerationEmployeRepository.findById(profilRemunerationId);
		if (opEmp.isEmpty()) {
			messagesErreurs.add("L'id " + profilRemunerationId + " ne correspond à aucun profil de Remuneration");
		}

		Optional<Periode> opPeriode = periodeRepository.findById(periodeId);
		if (opPeriode.isEmpty()) {
			messagesErreurs.add("L'id " + periodeId + " ne correspond à aucune periode enregistrée");
		}

		if (!messagesErreurs.isEmpty()) {
			throw new PaieException(messagesErreurs);
		}

		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin.setRemunerationEmploye(opEmp.get());
		bulletin.setPeriode(opPeriode.get());
		bulletin.setPrimeExceptionnelle(primeExetionnelle);
		bulletin.setDateCreation(LocalDate.now());
		bulletin.setHeureCreation(LocalTime.now());

		return bulletinSalaireRepositorie.save(bulletin);
	}

}
