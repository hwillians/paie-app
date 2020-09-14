package dev.paie.web.remunerationEmploye;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.services.RemunerationEmployeService;

@WebMvcTest(RemunerationEmployeController.class)
class RemunerationEmployeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RemunerationEmployeService remunerationEmployeService;

	@Test
	void testCreerEmploye() throws Exception {

		String matricule = "M2015";

		Integer entrepriseId = 1;

		Integer profilId = 1;

		Integer gradeId = 1;

		String json = "{\"matricule\":\"" + matricule + "\", \"entrepriseId\":\"" + entrepriseId + "\", \"profilId\":\""
				+ profilId + "\", \"gradeId\":\"+" + gradeId + "\"}";

		RemunerationEmploye emp = new RemunerationEmploye();
		Entreprise entreprise = new Entreprise();
		entreprise.setId(entrepriseId);
		entreprise.setDenomination("Las rosas");

		Grade grade = new Grade();
		grade.setId(gradeId);
		grade.setCode("aa000");

		ProfilRemuneration profilRemuneration = new ProfilRemuneration();
		profilRemuneration.setId(profilId);
		profilRemuneration.setCode("Cadre");

		emp.setId(1);
		emp.setMatricule(matricule);
		emp.setEntreprise(entreprise);
		emp.setGrade(grade);
		emp.setProfilRemuneration(profilRemuneration);

		when(remunerationEmployeService.creerEmploye(matricule, entrepriseId, profilId, gradeId)).thenReturn(emp);

		CreerRemunerationEmployeReponseDto reponse = new CreerRemunerationEmployeReponseDto(emp);

		this.mockMvc.perform(post("/employes").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.matricule").value("M2015"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nomEmtreprise").value("Las rosas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.codeGrade").value("aa000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.codeProfilRe").value("Cadre"));

	}

}
