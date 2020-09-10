package dev.paie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {

	List<Employe> findByMatricule(Integer matricule);

}
