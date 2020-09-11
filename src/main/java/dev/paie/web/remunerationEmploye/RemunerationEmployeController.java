package dev.paie.web.remunerationEmploye;

import java.util.List;

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

	private RemunerationEmployeService eServ;

	/**
	 * @param eServ
	 */
	public RemunerationEmployeController(RemunerationEmployeService eServ) {
		this.eServ = eServ;
	}

	@PostMapping
	public ResponseEntity<?> creerEmploye(@RequestBody CreerRemunerationEmployeResquestDto employeRq, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			RemunerationEmploye EmployeServ = eServ.creerEmploye(employeRq.getMatricule(), employeRq.getEntrepriseId(),
					employeRq.getProfilId(), employeRq.getGradeId());

			CreerRemunerationEmployeReponseDto employeRep = new CreerRemunerationEmployeReponseDto(EmployeServ);
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
