package dev.paie.services;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Grade;

public interface GradeReposirtory extends JpaRepository<Grade, Integer> {

}
