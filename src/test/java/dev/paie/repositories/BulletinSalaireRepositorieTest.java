package dev.paie.repositories;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import dev.paie.entite.Cotisation;

@WebMvcTest(BulletinSalaireRepositorie.class)
class BulletinSalaireRepositorieTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BulletinSalaireRepositorie bull;

	@Test
	void testFindCotisationsNonImp() {
		List<Cotisation> list = new ArrayList<Cotisation>();
		Cotisation c1 = new Cotisation();
		c1.setLibelle("10");
		Cotisation c2 = new Cotisation();

		when(bull.findCotisationsNonImp()).thenReturn((Arrays.asList(c1, c2)));

	}

}
