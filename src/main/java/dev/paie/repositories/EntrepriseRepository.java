package dev.paie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}
