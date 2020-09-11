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
import dev.paie.entite.Grade;
import dev.paie.services.BulletinService;

@RestController
@RequestMapping("bulletin")
public class BulletinController {

	private BulletinService bullServ;

	/**
	 * @param bullServ
	 * @param gradeRep
	 * @param cotRep
	 */
	public BulletinController(BulletinService bullServ) {
		this.bullServ = bullServ;

	}

	@GetMapping
	public List<CreerBulletinReponseDto> listerBulletins(@RequestBody CreerBulletinSalaireResquestDto bullRq,
			BindingResult resValid) {

		List<BulletinSalaire> listBulletin = bullServ.listerBulletins(bullRq.getDateCreation(),
				bullRq.getDebutPeriode(), bullRq.getFinPeriode(), bullRq.getMatricules());

		List<Cotisation> CotisationsNonImp = bullServ.listerCotisationNonImp();

		List<Cotisation> CotisationsImposab = bullServ.listerCotisationImposable();

		List<CreerBulletinReponseDto> listBulletinRep = new ArrayList<>();
		for (BulletinSalaire bs : listBulletin) {

			Grade grade = bullServ.getGrade(bs.getId());
			String matricule = bullServ.getMatricule(bs.getId());
			listBulletinRep
					.add(new CreerBulletinReponseDto(bs, grade, matricule, CotisationsNonImp, CotisationsImposab));
		}
		return listBulletinRep;
	}

	@PostMapping
	public List<CreerBulletinReponseDto> creerBulletins(@RequestBody CreerBulletinSalaireResquestDto bullRq,
			BindingResult resValid) {
		return null;

	}

}
