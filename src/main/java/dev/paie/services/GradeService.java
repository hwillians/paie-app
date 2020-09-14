package dev.paie.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;
import dev.paie.repositories.GradeRepository;

@Service
public class GradeService {

	private GradeRepository gradeRepository;

	/**
	 * @param gradeRepository
	 */
	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}

	public List<Grade> listerGrades() {

		return gradeRepository.findAll();
	}

}
