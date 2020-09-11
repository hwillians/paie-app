package dev.paie.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;

public interface BulletinSalaireRepositorie extends JpaRepository<BulletinSalaire, Integer> {

	@Query("select b from BulletinSalaire b join fetch b.periode p join fetch b.remunerationEmploye r "
			+ "where b.dateCreation = ?1 and p.id = ?2 and r.matricule = ?3")
	List<BulletinSalaire> findAllByDateCreationAndPeriodeAndMatricules(LocalDate date, Integer periodeId,
			List<String> matricules);

	@Query("select b from BulletinSalaire b join fetch b.remunerationEmploye r join fetch r.grade where b.id = ?1")
	Optional<BulletinSalaire> findGradeByBulletinId(Integer id);

	@Query("select b from BulletinSalaire b join fetch b.remunerationEmploye where b.id = ?1")
	Optional<BulletinSalaire> findMatriculeByBulletinId(Integer id);

	@Query("select b from BulletinSalaire b join fetch b.remunerationEmploye r join fetch r.profilRemuneration p "
			+ "join fetch p.cotisations c where c.imposable = 0")
	List<Cotisation> findCotisationsImp();

	@Query("select b from BulletinSalaire b join fetch b.remunerationEmploye r join fetch r.profilRemuneration p "
			+ "join fetch p.cotisations c where c.imposable = 1")
	List<Cotisation> findCotisationsNonImp();
}
