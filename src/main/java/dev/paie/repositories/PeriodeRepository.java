package dev.paie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Periode;

public interface PeriodeRepository extends JpaRepository<Periode, Integer> {

}
