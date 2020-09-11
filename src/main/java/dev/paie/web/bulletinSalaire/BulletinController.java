package dev.paie.web.bulletinSalaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import dev.paie.services.EntrepriseService;

@RestController
@RequestMapping("bulletins")
public class BulletinController {

	private BulletinService bullServ;
	private EntrepriseService entServ;
	private ProfilRemunerationRepository ProfilRep;

	/**
	 * @param bullServ
	 * @param entServ
	 * @param profilRep
	 */
	public BulletinController(BulletinService bullServ, EntrepriseService entServ,
			ProfilRemunerationRepository profilRep) {
		this.bullServ = bullServ;
		this.entServ = entServ;
		ProfilRep = profilRep;
	}

	@GetMapping
	public List<CreerBulletinReponseDtoGet> listerBulletins(@RequestBody CreerBulletinSalaireResquestDtoGet bullRq,
			BindingResult resValid) {

		List<BulletinSalaire> listBulletin = bullServ.listerBulletins(bullRq.getDateCreation(), bullRq.getPerdiodeId(),
				bullRq.getMatricules());

		List<Cotisation> CotisationsNonImp = bullServ.listerCotisationNonImp();

		List<Cotisation> CotisationsImposab = bullServ.listerCotisationImposable();

		List<CreerBulletinReponseDtoGet> listBulletinRep = new ArrayList<>();
		for (BulletinSalaire bs : listBulletin) {

			Grade grade = bullServ.getGrade(bs.getId());
			String matricule = bullServ.getMatricule(bs.getId());
			listBulletinRep
					.add(new CreerBulletinReponseDtoGet(bs, grade, matricule, CotisationsNonImp, CotisationsImposab));
		}
		return listBulletinRep;
	}

	@PostMapping
	public ResponseEntity<?> creerBulletins(@RequestBody @Validated CreerBulletinSalaireResquestDtoPost bullRq,
			BindingResult resValid) {
		if (!resValid.hasErrors()) {
			BulletinSalaire bulletin = bullServ.creerBulletin(bullRq.getEntrepriseId(), bullRq.getPerdiodeId(),
					bullRq.getProfilRemunerationId(), bullRq.getPrimeExetionnelle());

			Optional<ProfilRemuneration> opProfilNomImp = ProfilRep
					.listerCotisationNonImp(bullRq.getProfilRemunerationId());

			List<Cotisation> CotisationsNonImp = opProfilNomImp.get().getCotisations();

			Optional<ProfilRemuneration> opProfilImp = ProfilRep
					.listerCotisationImposable(bullRq.getProfilRemunerationId());

			List<Cotisation> CotisationsImposab = opProfilImp.get().getCotisations();

			Grade grade = bullServ.getGrade(bulletin.getId());
			String matricule = bullServ.getMatricule(bulletin.getId());

			Entreprise entreprise = entServ.getEnterprise(bullRq.getEntrepriseId());

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
