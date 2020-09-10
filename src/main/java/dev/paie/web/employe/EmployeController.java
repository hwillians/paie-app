package dev.paie.web.employe;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Employe;
import dev.paie.exception.PaieException;
import dev.paie.services.EmployeService;

@RestController
@RequestMapping("employes")
public class EmployeController {

	private EmployeService eServ;

	/**
	 * @param eServ
	 */
	public EmployeController(EmployeService eServ) {
		this.eServ = eServ;
	}

	@PostMapping
	public ResponseEntity<?> creerEmploye(@RequestBody CreerEmployeResquestDto employeRq, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Employe EmployeServ = eServ.creerEmploye(employeRq.getMatricule(), employeRq.getEntrepriseId(),
					employeRq.getProfilId(), employeRq.getGradeId());

			CreerEmployeReponseDto employeRep = new CreerEmployeReponseDto(EmployeServ);
			return ResponseEntity.ok(employeRep);
		} else {
			return ResponseEntity.badRequest().body("tous les champs sont obligatoires ! ");
		}

	}

	@ExceptionHandler(PaieException.class)
	public ResponseEntity<List<String>> onPaieException(PaieException ex) {
		return ResponseEntity.badRequest().body(ex.getMessagesErreurs());

	}
}
