package dev.paie.web.profil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.ProfilRemuneration;
import dev.paie.services.ProfilService;

@RestController
@RequestMapping("profils")
public class ProfilController {

	private ProfilService profilService;

	/**
	 * @param profilService
	 */
	public ProfilController(ProfilService profilService) {
		this.profilService = profilService;
	}

	@GetMapping
	public List<CreerProfilRemunerationreponseDto> listerProfils() {

		List<ProfilRemuneration> listeProfil = profilService.lister();
		List<CreerProfilRemunerationreponseDto> listeProfilDto = new ArrayList<CreerProfilRemunerationreponseDto>();

		for (ProfilRemuneration p : listeProfil) {
			listeProfilDto.add(new CreerProfilRemunerationreponseDto(p.getCode()));
		}
		return listeProfilDto;

	}

}
