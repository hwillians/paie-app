package dev.paie.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.paie.entite.ProfilRemuneration;
import dev.paie.repositories.ProfilRemunerationRepository;

@Service
public class ProfilService {

	private ProfilRemunerationRepository profilRepository;

	/**
	 * @param profilRepository
	 */
	public ProfilService(ProfilRemunerationRepository profilRepository) {
		this.profilRepository = profilRepository;
	}

	public List<ProfilRemuneration> lister() {

		return profilRepository.findAll();
	}

}
