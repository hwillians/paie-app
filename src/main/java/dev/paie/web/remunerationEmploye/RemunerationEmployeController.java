package dev.paie.web.remunerationEmploye;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.exception.PaieException;
import dev.paie.services.RemunerationEmployeService;

@RestController
@RequestMapping("employes")
public class RemunerationEmployeController {

	private RemunerationEmployeService remunerationEmployeService;

	/**
	 * @param eServ
	 */
	public RemunerationEmployeController(RemunerationEmployeService remunerationEmployeService) {
		this.remunerationEmployeService = remunerationEmployeService;
	}

	@PostMapping
	public ResponseEntity<?> creerEmploye(@RequestBody @Valid CreerRemunerationEmployeResquestDto employeRq,
			BindingResult resValid) {
		if (!resValid.hasErrors()) {
			RemunerationEmploye EmployeServ = remunerationEmployeService.creerEmploye(employeRq.getMatricule(),
					employeRq.getEntrepriseId(), employeRq.getProfilId(), employeRq.getGradeId());

			CreerRemunerationEmployeReponseDto employeRep = new CreerRemunerationEmployeReponseDto(EmployeServ);
			return ResponseEntity.ok(employeRep);
		} else {
			return ResponseEntity.badRequest().body("tous les champs sont obligatoires !");
		}
	}

	@ExceptionHandler(PaieException.class)
	public ResponseEntity<List<String>> onPaieException(PaieException ex) {
		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());
	}
}
