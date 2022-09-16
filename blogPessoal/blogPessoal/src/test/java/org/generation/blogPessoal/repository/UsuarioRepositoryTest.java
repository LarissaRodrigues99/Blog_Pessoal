package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() {
		
		repository.deleteAll();
		
		repository.save(new Usuario(0L,"Micaele da Silva","micaele@gmail.com","12547896","https://www.google.com.br/search?q=fotos+para+perfil&hl=pt-BR&authuser=0&tbm=isch&sxsrf=ALiCzsbybmJc07vHq_cFymlYHYevISZPRA%3A1663249796284&source=hp&biw=1707&bih=821&ei=hC0jY_myD7nf1sQPpdWFgAo&iflsig=AJiK0e8AAAAAYyM7lN-8vKF2tgurvykFAqxYWKo9r-M2&oq=fo&gs_lcp=CgNpbWcQAxgAMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgsIABCABBCxAxCDATIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgUIABCABDoECCMQJzoICAAQsQMQgwFQAFivAWDfG2gAcAB4AIABf4gB2wGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZw&sclient=img#imgrc=no1mUC_UaOK-6M"));
	
		repository.save(new Usuario(0L,"Otaviano da Costa Silva","otavio@gmail.com","12364857","https://www.google.com.br/search?q=fotos+para+perfil&hl=pt-BR&authuser=0&tbm=isch&sxsrf=ALiCzsbybmJc07vHq_cFymlYHYevISZPRA%3A1663249796284&source=hp&biw=1707&bih=821&ei=hC0jY_myD7nf1sQPpdWFgAo&iflsig=AJiK0e8AAAAAYyM7lN-8vKF2tgurvykFAqxYWKo9r-M2&oq=fo&gs_lcp=CgNpbWcQAxgAMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgsIABCABBCxAxCDATIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgUIABCABDoECCMQJzoICAAQsQMQgwFQAFivAWDfG2gAcAB4AIABf4gB2wGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZw&sclient=img#imgrc=_jROiLMHVkRU_M"));
				
		repository.save(new Usuario(0L,"Clarice Silva","clarice@gmail.com","85467945","https://www.google.com.br/search?q=fotos+para+perfil&hl=pt-BR&authuser=0&tbm=isch&sxsrf=ALiCzsbybmJc07vHq_cFymlYHYevISZPRA%3A1663249796284&source=hp&biw=1707&bih=821&ei=hC0jY_myD7nf1sQPpdWFgAo&iflsig=AJiK0e8AAAAAYyM7lN-8vKF2tgurvykFAqxYWKo9r-M2&oq=fo&gs_lcp=CgNpbWcQAxgAMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgsIABCABBCxAxCDATIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgUIABCABDoECCMQJzoICAAQsQMQgwFQAFivAWDfG2gAcAB4AIABf4gB2wGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZw&sclient=img#imgrc=l0ERNTtgoIEclM"));
		
		repository.save(new Usuario(0L,"Jose Alfredo","jose@gmail.com","65478912","https://www.google.com.br/search?q=fotos+para+perfil&hl=pt-BR&authuser=0&tbm=isch&sxsrf=ALiCzsbybmJc07vHq_cFymlYHYevISZPRA%3A1663249796284&source=hp&biw=1707&bih=821&ei=hC0jY_myD7nf1sQPpdWFgAo&iflsig=AJiK0e8AAAAAYyM7lN-8vKF2tgurvykFAqxYWKo9r-M2&oq=fo&gs_lcp=CgNpbWcQAxgAMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgsIABCABBCxAxCDATIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgUIABCABDoECCMQJzoICAAQsQMQgwFQAFivAWDfG2gAcAB4AIABf4gB2wGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZw&sclient=img#imgrc=haWcbdOUFW7eMM"));
		
	}
	
	@Test
	@DisplayName("Retornar um usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = repository.findByUsuario("otavio@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("otavio@gmail.com"));
	}
	
	@Test
	@DisplayName("Retornar três usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Micaele da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Otaviano da Costa Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Clarice Silva"));
	}
	
	@AfterAll
	public void end() {
		
		repository.deleteAll();
	}

}
