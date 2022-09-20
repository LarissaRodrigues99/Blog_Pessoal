package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {

		repository.deleteAll();

		usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", " "));

	}

	@Test
	@DisplayName("Cadastrar um usuário")
	public void deveCriarUmUsuario() {

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, "Jose Alfredo", "jose@gmail.com","65478912","https://www.google.com.br/search?q=fotos+para+perfil&hl=pt-BR&authuser=0&tbm=isch&sxsrf=ALiCzsbybmJc07vHq_cFymlYHYevISZPRA%3A1663249796284&source=hp&biw=1707&bih=821&ei=hC0jY_myD7nf1sQPpdWFgAo&iflsig=AJiK0e8AAAAAYyM7lN-8vKF2tgurvykFAqxYWKo9r-M2&oq=fo&gs_lcp=CgNpbWcQAxgAMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgsIABCABBCxAxCDATIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyBQgAEIAEMgUIABCABDoECCMQJzoICAAQsQMQgwFQAFivAWDfG2gAcAB4AIABf4gB2wGSAQMxLjGYAQCgAQGqAQtnd3Mtd2l6LWltZw&sclient=img#imgrc=haWcbdOUFW7eMM"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());

	}

	@Test
	@DisplayName("Não deve permitir duplicação de usuário")
	public void naoDevePermitirDuplicacaoDeUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Louis Davi", "louis@gmail.com", "Ld581234","https://www.google.com.br/search?q=desenho+mulher+com+cachos+fotos+para+perfil&tbm=isch&ved=2ahUKEwjavtPFmJf6AhVAMrkGHeqBB9AQ2-cCegQIABAA&oq=desenho+mulher+com+cachos+fotos+para+perfil&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEB4QBzoICAAQHhAIEAdQrAZY8Udg6kpoAHAAeACAAeEBiAGMEJIBBjEyLjYuMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=S08jY5rtKsDk5OUP6oOegA0&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=SiD1J3IF5LcotM"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, "Louis Davi","louis@gmail.com", "Ld581234","https://www.google.com.br/search?q=desenho+mulher+com+cachos+fotos+para+perfil&tbm=isch&ved=2ahUKEwjavtPFmJf6AhVAMrkGHeqBB9AQ2-cCegQIABAA&oq=desenho+mulher+com+cachos+fotos+para+perfil&gs_lcp=CgNpbWcQAzoECCMQJzoFCAAQgAQ6BggAEB4QBzoICAAQHhAIEAdQrAZY8Udg6kpoAHAAeACAAeEBiAGMEJIBBjEyLjYuMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=S08jY5rtKsDk5OUP6oOegA0&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=SiD1J3IF5LcotM"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,corpoRequisicao, Usuario.class);

		
		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualizar um usuário")
	public void deveAtualizarUmUsuario ()
	{
		
		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,"Jonata Tores","jonata@gmail.com","JT123467","https://www.google.com.br/search?q=desenho+homem+com+cachos+fotos+para+perfil&tbm=isch&ved=2ahUKEwjt3_bOmJf6AhWlCbkGHe9fCKIQ2-cCegQIABAA&oq=desenho+homem+com+cachos+fotos+para+perfil&gs_lcp=CgNpbWcQA1CrBliCH2DVJGgAcAB4AIAB4AOIAZkMkgEHOC4zLjQtMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=X08jY620CaWT5OUP77-hkAo&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=W0Gui2sbfTXi5M&imgdii=XpSx38KHzP_gMM"));
		
		Usuario usuarioUpdate = new  Usuario (usuarioCadastrado.get().getId(),"Jonata Tores","jonata@gmail.com","JT123467","https://www.google.com.br/search?q=desenho+homem+com+cachos+fotos+para+perfil&tbm=isch&ved=2ahUKEwjt3_bOmJf6AhWlCbkGHe9fCKIQ2-cCegQIABAA&oq=desenho+homem+com+cachos+fotos+para+perfil&gs_lcp=CgNpbWcQA1CrBliCH2DVJGgAcAB4AIAB4AOIAZkMkgEHOC4zLjQtMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=X08jY620CaWT5OUP77-hkAo&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=W0Gui2sbfTXi5M&imgdii=XpSx38KHzP_gMM");
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);
		
		ResponseEntity<Usuario> corpoResposta = testRestTemplate
				.withBasicAuth("root@root.com","rootroot")
				.exchange("/usuarios/atualizar", HttpMethod.PUT,corpoRequisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK,corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(),corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getUsuario(),corpoResposta.getBody().getUsuario());	
		
	}

	@Test
	@DisplayName("Listar todos os usuários")
	public void deveMostrarTodosOsUsuarios() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Katarina Bastos", "katarina@gmail.com", "K3258794",
				"https://www.google.com.br/search?q=desenho+homem+com+cachos+fotos+para+perfil&tbm=isch&ved=2ahUKEwjt3_bOmJf6AhWlCbkGHe9fCKIQ2-cCegQIABAA&oq=desenho+homem+com+cachos+fotos+para+perfil&gs_lcp=CgNpbWcQA1CrBliCH2DVJGgAcAB4AIAB4AOIAZkMkgEHOC4zLjQtMZgBAKABAaoBC2d3cy13aXotaW1nwAEB&sclient=img&ei=X08jY620CaWT5OUP77-hkAo&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=rlOjOTnRZSBQmM&imgdii=8Sf4xovQh0QEdM"));

		usuarioService.cadastrarUsuario(new Usuario(0L, "Benancio Alves", "benancio@gmail.com", "NB256151",
				"https://www.google.com.br/search?q=desenho+homem++fotos+para+perfil&tbm=isch&ved=2ahUKEwjxkum7npf6AhVUBLkGHTIqDPkQ2-cCegQIABAA&oq=desenho+homem++fotos+para+perfil&gs_lcp=CgNpbWcQAzIGCAAQHhAIMgYIABAeEAgyBggAEB4QCDIGCAAQHhAIMgYIABAeEAhQqglYiRlgwTFoAHAAeACAAd0BiAHSCZIBBTIuOC4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=gVUjY7HOKtSI5OUPstSwyA8&authuser=0&bih=821&biw=1707&hl=pt-BR#imgrc=AihdpzwnMhxQtM"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
