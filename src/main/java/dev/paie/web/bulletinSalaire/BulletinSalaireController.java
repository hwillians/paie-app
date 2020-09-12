package dev.paie.web.bulletinSalaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.exception.PaieException;
import dev.paie.repositories.RemunerationEmployeRepository;
import dev.paie.services.BulletinSalaireService;

@RestController
@RequestMapping("bulletins")
public class BulletinSalaireController {

	private BulletinSalaireService bulletinSalaireService;

	private RemunerationEmployeRepository remunerationEmployeRepository;

	/**
	 * @param bullServ
	 * @param entServ
	 * @param profilRep
	 */
	public BulletinSalaireController(BulletinSalaireService bulletinSalaireService,
			RemunerationEmployeRepository remunerationEmployeRepository) {
		this.bulletinSalaireService = bulletinSalaireService;

		this.remunerationEmployeRepository = remunerationEmployeRepository;
	}

	/*
	 * notation JSON
	 * 
	 * { "dateCreation": "2020-09-12",
	 * 
	 * "perdiodeId": "1",
	 * 
	 * "matricules": [
	 * 
	 * "M0111" ] }
	 */

	@GetMapping
	public List<CreerBulletinSalaireReponseDtoGet> listerBulletins(
			@RequestBody @Valid CreerBulletinSalaireResquestDtoGet bullRq, BindingResult resValid) {

		List<BulletinSalaire> listeBulletins = bulletinSalaireService.listerBulletins(bullRq.getDateCreation(),
				bullRq.getPerdiodeId(), bullRq.getMatricules());

		List<CreerBulletinSalaireReponseDtoGet> listBulletinRep = new ArrayList<>();
		for (BulletinSalaire bs : listeBulletins) {

			Grade grade = bulletinSalaireService.getGrade(bs.getId());
			String matricule = bulletinSalaireService.getMatricule(bs.getId());
			List<Cotisation> CotisationsNonImp = bulletinSalaireService.listerCotisationNonImp(bs.getId());
			List<Cotisation> CotisationsImposab = bulletinSalaireService.listerCotisationImposable(bs.getId());
			listBulletinRep.add(
					new CreerBulletinSalaireReponseDtoGet(bs, grade, matricule, CotisationsNonImp, CotisationsImposab));
		}
		return listBulletinRep;
	}

	@PostMapping
	public ResponseEntity<?> creerBulletins(@RequestBody @Valid CreerBulletinSalaireResquestDtoPost bullRq,
			BindingResult resValid) {

		if (!resValid.hasErrors()) {

			BulletinSalaire bulletin = bulletinSalaireService.creerBulletin(bullRq.getPerdiodeId(),
					bullRq.getRemunerationEmployeId(), bullRq.getPrimeExetionnelle());

			Optional<RemunerationEmploye> optionalRemunerationNonImp = remunerationEmployeRepository
					.listerCotisationNonImp(bullRq.getRemunerationEmployeId());

			List<Cotisation> CotisationsNonImp = optionalRemunerationNonImp
					.orElseThrow(() -> new RuntimeException("erreur : optention Cotisations Imposables"))
					.getProfilRemuneration().getCotisations();

			Optional<RemunerationEmploye> optinalRemunerationImposable = remunerationEmployeRepository
					.listerCotisationImposable(bullRq.getRemunerationEmployeId());

			List<Cotisation> CotisationsImposab = optinalRemunerationImposable
					.orElseThrow(() -> new RuntimeException("erreur : optention Cotisations non Imposables"))
					.getProfilRemuneration().getCotisations();
			System.out.println(CotisationsImposab.size());

			Grade grade = bulletinSalaireService.getGrade(bulletin.getId());

			String matricule = bulletinSalaireService.getMatricule(bulletin.getId());

			Entreprise entreprise = bulletinSalaireService.getEnterprise(bulletin.getId());

			CreerBulletinSalaireReponseDtoPost bulletinPost = new CreerBulletinSalaireReponseDtoPost(bulletin, grade,
					matricule, entreprise, CotisationsNonImp, CotisationsImposab);
			return ResponseEntity.ok(bulletinPost);

		} else {
			return ResponseEntity.badRequest().body("tous les champs sont obligatoires !");
		}
	}

	@ExceptionHandler(PaieException.class)
	public ResponseEntity<List<String>> onPaieException(PaieException ex) {
		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());

	}
}
