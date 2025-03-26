package com.senai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.senai.api.dto.AuthDto;
import com.senai.api.models.Usuario;
import com.senai.api.security.AdminInitializer;
import com.senai.api.services.impl.UsuarioServiceImpl;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class TesteUnitario {

	@InjectMocks
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();
	private String token;
	
	@Autowired
	private AdminInitializer adminInitializer;

	    

//	Testes de cpf válido e inválido
	@Test
	public void testCPFValido() {
		String cpf = "32008729001";
		assertEquals(true, usuarioServiceImpl.isCpf(cpf));
	}

	@Test
	public void testCPFInvalido() {
		String cpf = "12345678900";
		assertEquals(false, usuarioServiceImpl.isCpf(cpf));
	}
	
	//Teste para executar o criador de admin na inicialização da aplicação
	@Test
    public void testCriacaoAdmin() throws Exception {
        adminInitializer.run();
    }

	//teste para efetuar login e pegar o token a ser utilizada nas demais
	@BeforeEach
	public void testLogin() throws Exception {

		AuthDto credenciais = new AuthDto();
		credenciais.setCpf("32008729001");
		credenciais.setSenha("123456");

		MvcResult resultado = mockMvc
				.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(credenciais)))
				.andExpect(status().isAccepted()).andReturn();

		String response = resultado.getResponse().getContentAsString();
		JsonNode jsonNode = objectMapper.readTree(response);
		token = jsonNode.get("acessToken").asText();
	}

	//Requisições básicas de GET usuarios, acomodacoes, clientes
	@Test
	void testGetUsuarios() throws Exception {
		mockMvc.perform(get("/api/usuario/lista").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetClientes() throws Exception {
		mockMvc.perform(get("/api/hospedagem/clientes").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isOk());
	}
	
	@Test
	void testGetAcomodacoes() throws Exception {
		mockMvc.perform(get("/api/hospedagem/acomodacoes").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
				.andExpect(status().isOk());
	}

}