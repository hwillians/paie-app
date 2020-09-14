package dev.paie.web.grade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Grade;
import dev.paie.services.GradeService;

@RestController
@RequestMapping("grades")
public class GradeController {

	private GradeService gradeService;

	/**
	 * @param gradeService
	 */
	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@GetMapping
	public List<CreerGradeDto> listerGrades() {

		List<Grade> listeGrade = gradeService.listerGrades();
		List<CreerGradeDto> listeGradeDto = new ArrayList<CreerGradeDto>();

		for (Grade g : listeGrade) {
			listeGradeDto.add(new CreerGradeDto(g.getCode(), g.getTauxBase(), g.getNbHeuresBase()));

		}

		return listeGradeDto;

	}

}
