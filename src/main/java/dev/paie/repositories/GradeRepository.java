package dev.paie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
