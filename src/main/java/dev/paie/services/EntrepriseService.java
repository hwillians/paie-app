package dev.paie.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.paie.entite.Entreprise;
import dev.paie.repositories.EntrepriseRepository;

@Service
public class EntrepriseService {

	private EntrepriseRepository entRep;

	/**
	 * @param entRep
	 */
	public EntrepriseService(EntrepriseRepository entRep) {
		this.entRep = entRep;
	}

	@Transactional
	public Entreprise getEnterprise(Integer entrepriseId) {

		return entRep.getOne(entrepriseId);
	}

}
