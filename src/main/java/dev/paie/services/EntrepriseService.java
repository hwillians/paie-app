package dev.paie.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.paie.entite.Entreprise;
import dev.paie.repositories.EntrepriseRepository;

@Service
public class EntrepriseService {

	private EntrepriseRepository entrepriseRpository;

	/**
	 * @param entRep
	 */
	public EntrepriseService(EntrepriseRepository entrepriseRpository) {
		this.entrepriseRpository = entrepriseRpository;
	}

	@Transactional
	public Entreprise getEnterprise(Integer entrepriseId) {
		return entrepriseRpository.getOne(entrepriseId);
	}

	public List<Entreprise> listerEntreprises() {

		return entrepriseRpository.findAll();
	}

}
