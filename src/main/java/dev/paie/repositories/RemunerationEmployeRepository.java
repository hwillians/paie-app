package dev.paie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.paie.entite.RemunerationEmploye;

public interface RemunerationEmployeRepository extends JpaRepository<RemunerationEmploye, Integer> {

	@Query("select r from  RemunerationEmploye r join fetch r.profilRemuneration p join fetch p.cotisations c where r.id =?1 and c.imposable = ?2")
	Optional<RemunerationEmploye> listerCotisations(Integer integer, boolean b);

}
