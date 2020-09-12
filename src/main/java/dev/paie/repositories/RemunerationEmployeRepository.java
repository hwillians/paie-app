package dev.paie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.paie.entite.RemunerationEmploye;

public interface RemunerationEmployeRepository extends JpaRepository<RemunerationEmploye, Integer> {

	@Query("select r from RemunerationEmploye r join fetch r.profilRemuneration p join fetch p.cotisations c where c.imposable = 0 and r.id =?1 ")
	Optional<RemunerationEmploye> listerCotisationNonImp(Integer integer);

	@Query("select r from  RemunerationEmploye r join fetch r.profilRemuneration p join fetch p.cotisations c where c.imposable = 1 and r.id =?1")
	Optional<RemunerationEmploye> listerCotisationImposable(Integer integer);

}
