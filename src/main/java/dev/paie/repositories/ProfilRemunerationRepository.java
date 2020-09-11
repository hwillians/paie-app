package dev.paie.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.paie.entite.ProfilRemuneration;

public interface ProfilRemunerationRepository extends JpaRepository<ProfilRemuneration, Integer> {

	@Query("select p from  ProfilRemuneration p join fetch p.cotisations c where c.imposable = 0 and p.id =?1 ")
	Optional<ProfilRemuneration> listerCotisationNonImp(Integer integer);

	@Query("select p from  ProfilRemuneration p join fetch p.cotisations c where c.imposable = 1 and  p.id =?1")
	Optional<ProfilRemuneration> listerCotisationImposable(Integer integer);

}
