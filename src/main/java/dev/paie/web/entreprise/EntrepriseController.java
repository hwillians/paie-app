package dev.paie.web.entreprise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Entreprise;
import dev.paie.services.EntrepriseService;

@RestController
@RequestMapping("entreprises")
public class EntrepriseController {

	private EntrepriseService entrepriseService;

	/**
	 * @param entrepriseService
	 */
	public EntrepriseController(EntrepriseService entrepriseService) {
		this.entrepriseService = entrepriseService;
	}

	@GetMapping
	public List<CrerEntrepriseDto> listerBulletins() {

		List<Entreprise> listeEntrepise = entrepriseService.listerEntreprises();
		List<CrerEntrepriseDto> listeEntrepiseDto = new ArrayList<>();

		for (Entreprise e : listeEntrepise) {
			listeEntrepiseDto.add(new CrerEntrepriseDto(e.getSiret(), e.getDenomination()));
		}
		return listeEntrepiseDto;

	}

}
