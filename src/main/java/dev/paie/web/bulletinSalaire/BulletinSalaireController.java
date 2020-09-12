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
import dev.paie.entite.ProfilRemuneration;
import dev.paie.exception.PaieException;
import dev.paie.repositories.ProfilRemunerationRepository;
import dev.paie.services.BulletinService;

@RestController
@RequestMapping("bulletins")
public class BulletinSalaireController {

	private BulletinService bullServ;

	private ProfilRemunerationRepository profilRep;

	/**
	 * @param bullServ
	 * @param entServ
	 * @param profilRep
	 */
	public BulletinSalaireController(BulletinService bullServ, ProfilRemunerationRepository profilRep) {
		this.bullServ = bullServ;

		this.profilRep = profilRep;
	}

	/*
	 * notetion JSON
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
	public List<CreerBulletinReponseDtoGet> listerBulletins(
			@Valid @RequestBody CreerBulletinSalaireResquestDtoGet bullRq, BindingResult resValid) {

		List<BulletinSalaire> listBulletin = bullServ.listerBulletins(bullRq.getDateCreation(), bullRq.getPerdiodeId(),
				bullRq.getMatricules());

		List<CreerBulletinReponseDtoGet> listBulletinRep = new ArrayList<>();
		for (BulletinSalaire bs : listBulletin) {

			Grade grade = bullServ.getGrade(bs.getId());
			String matricule = bullServ.getMatricule(bs.getId());
			List<Cotisation> CotisationsNonImp = bullServ.listerCotisationNonImp(bs.getId());
			List<Cotisation> CotisationsImposab = bullServ.listerCotisationImposable(bs.getId());
			listBulletinRep
					.add(new CreerBulletinReponseDtoGet(bs, grade, matricule, CotisationsNonImp, CotisationsImposab));
		}
		return listBulletinRep;
	}

	@PostMapping
	public ResponseEntity<?> creerBulletins(@Valid @RequestBody CreerBulletinSalaireResquestDtoPost bullRq,
			BindingResult resValid) {

		if (!resValid.hasErrors()) {

			BulletinSalaire bulletin = bullServ.creerBulletin(bullRq.getPerdiodeId(), bullRq.getProfilRemunerationId(),
					bullRq.getPrimeExetionnelle());

			Optional<ProfilRemuneration> opProfilNomImp = profilRep
					.listerCotisationNonImp(bullRq.getProfilRemunerationId());
			List<Cotisation> CotisationsNonImp = opProfilNomImp.get().getCotisations();

			Optional<ProfilRemuneration> opProfilImp = profilRep
					.listerCotisationImposable(bullRq.getProfilRemunerationId());
			List<Cotisation> CotisationsImposab = opProfilImp.get().getCotisations();

			Grade grade = bullServ.getGrade(bulletin.getId());

			String matricule = bullServ.getMatricule(bulletin.getId());

			Entreprise entreprise = bullServ.getEnterprise(bulletin.getId());

			CreerBulletinReponseDtoPost bulletinPost = new CreerBulletinReponseDtoPost(bulletin, grade, matricule,
					entreprise, CotisationsNonImp, CotisationsImposab);
			return ResponseEntity.ok(bulletinPost);

		} else {
			return ResponseEntity.badRequest().body("tous les champs sont obligatoires ! ");
		}
	}

	@ExceptionHandler(PaieException.class)
	public ResponseEntity<List<String>> onPaieException(PaieException ex) {
		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());

	}
}
