package dev.paie.web.bulletinSalaire;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.services.BulletinService;
import dev.paie.services.EntrepriseService;

@RestController
@RequestMapping("bulletins")
public class BulletinController {

	private BulletinService bullServ;
	private EntrepriseService entServ;

	/**
	 * @param bullServ
	 * @param gradeRep
	 * @param cotRep
	 */
	public BulletinController(BulletinService bullServ, EntrepriseService entServ) {
		this.bullServ = bullServ;
		this.entServ = entServ;

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
	public CreerBulletinReponseDtoPost creerBulletins(@RequestBody CreerBulletinSalaireResquestDtoPost bullRq,
			BindingResult resValid) {

		BulletinSalaire bulletin = bullServ.creerBulletin(bullRq.getEntrepriseId(), bullRq.getPerdiodeId(),
				bullRq.getProfilRemunerationId(), bullRq.getPrimeExetionnelle());

		List<Cotisation> CotisationsNonImp = bullServ.listerCotisationNonImp();

		List<Cotisation> CotisationsImposab = bullServ.listerCotisationImposable();
		Grade grade = bullServ.getGrade(bulletin.getId());
		String matricule = bullServ.getMatricule(bulletin.getId());

		Entreprise entreprise = entServ.getEnterprise(bullRq.getEntrepriseId());

		CreerBulletinReponseDtoPost bulletinPost = new CreerBulletinReponseDtoPost(bulletin, grade, matricule,
				entreprise, CotisationsNonImp, CotisationsImposab);
		return bulletinPost;

	}

}
