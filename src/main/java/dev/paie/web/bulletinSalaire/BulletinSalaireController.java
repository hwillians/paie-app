package dev.paie.web.bulletinSalaire;

import java.util.ArrayList;
import java.util.List;

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
import dev.paie.exception.PaieException;
import dev.paie.services.BulletinSalaireService;
import dev.paie.services.RemunerationEmployeService;

@RestController
@RequestMapping("bulletins")
public class BulletinSalaireController {

	private BulletinSalaireService bulletinSalaireService;
	private RemunerationEmployeService remunerationEmployeService;

	/**
	 * @param bulletinSalaireService
	 * @param remunerationEmployeService
	 */
	public BulletinSalaireController(BulletinSalaireService bulletinSalaireService,
			RemunerationEmployeService remunerationEmployeService) {
		this.bulletinSalaireService = bulletinSalaireService;
		this.remunerationEmployeService = remunerationEmployeService;
	}

	/*
	 * notation JSON
	 * 
	 * {
	 * 
	 * "dateCreation": "2020-09-12",
	 * 
	 * "perdiodeId": "1",
	 * 
	 * "matricules":
	 * 
	 * [
	 * 
	 * "M0111",
	 * 
	 * "M0112"
	 * 
	 * ]
	 * 
	 * }
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

			List<Cotisation> listeCotisations = bulletinSalaireService.listerCotisation(bs.getId());
			List<Cotisation> listecotisationsNonImposables = new ArrayList<Cotisation>();
			List<Cotisation> listecotisationsImposables = new ArrayList<Cotisation>();

			for (Cotisation c : listeCotisations) {

				if (c.getImposable() == false) {
					listecotisationsNonImposables.add(c);
				} else {
					listecotisationsImposables.add(c);
				}
			}

			listBulletinRep.add(new CreerBulletinSalaireReponseDtoGet(bs, grade, matricule,
					listecotisationsNonImposables, listecotisationsImposables));

		}

		return listBulletinRep;
	}

	@PostMapping
	public ResponseEntity<?> creerBulletins(@RequestBody @Valid CreerBulletinSalaireResquestDtoPost bullRq,
			BindingResult resValid) {

		if (!resValid.hasErrors()) {

			BulletinSalaire bulletin = bulletinSalaireService.creerBulletin(bullRq.getPerdiodeId(),
					bullRq.getRemunerationEmployeId(), bullRq.getPrimeExetionnelle());

			List<Cotisation> listeCotisations = remunerationEmployeService
					.listerCotisation(bullRq.getRemunerationEmployeId());
			List<Cotisation> listeCotisationsNonImposables = new ArrayList<Cotisation>();
			List<Cotisation> listeCotisationsImpossables = new ArrayList<Cotisation>();
			for (Cotisation c : listeCotisations) {
				if (c.getImposable() == false) {
					listeCotisationsNonImposables.add(c);
				} else {
					listeCotisationsImpossables.add(c);
				}
			}

			Grade grade = bulletinSalaireService.getGrade(bulletin.getId());

			String matricule = bulletinSalaireService.getMatricule(bulletin.getId());

			Entreprise entreprise = bulletinSalaireService.getEnterprise(bulletin.getId());

			CreerBulletinSalaireReponseDtoPost bulletinPost = new CreerBulletinSalaireReponseDtoPost(bulletin, grade,
					matricule, entreprise, listeCotisationsNonImposables, listeCotisationsImpossables);
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
