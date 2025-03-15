package com.senai.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dto.UsuarioDto;
import com.senai.api.models.Usuario;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuário", description = "Operações para gerenciar usuários")
public class UsuarioController {

	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/lista")
	@Operation(summary = "Retorna uma lista de usuários", description = "Recupera uma lista de usuários e seus respectivos dados do banco de dados.")
	@ApiResponse(responseCode = "200", description = "A recuperação da lista de usuários e seus dados foi realizada com sucesso.")
	@ApiResponse(responseCode = "400", description = "Não foi possível recuperar a lista de usuários.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<List<Usuario>> findUsuarios() {
		return usuarioService.recuperarUsuarios();
	}

	@GetMapping("/lista/{usuarioId}")
	@Operation(summary = "Procura um usuário pelo ID", description = "Com usuarioId como parâmetro, recupera um objeto contendo dados do usuário.")
	@ApiResponse(responseCode = "200", description = "A recuperação de dados do usuário realizada com sucesso.")
	@ApiResponse(responseCode = "400", description = "Não foi possível recuperar dados do usuário.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<Usuario> findUsuario(@PathVariable Integer usuarioId) {
		return usuarioService.recuperarUsuario(usuarioId);
	}

	@PostMapping("/cadastrar")
	@Operation(summary = "Cadastra usuário", description = "Com um objeto usuário no corpo da requisição, efetua cadastro da usuário no banco de dados.")
	@ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
		    content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "400", description = "Os dados do usuário estão incompletos ou inválidos.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<?> insertUsuario(@RequestBody UsuarioDto usuarioDto) throws NoSuchAlgorithmException {
		return usuarioService.cadastrar(usuarioDto);
	}

	@PutMapping("/atualizar/{usuarioId}")
	@Operation(summary = "Atualiza dados de usuário", description = "Com usuarioId como parâmetro e um objeto usuário no corpo da requisição, atualiza o usuário que possui o valor do id do parâmetro.")
	@ApiResponse(responseCode = "201", description = "Dados do usuário foram atualizados com sucesso.",
		    content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "400", description = "Os dados estão incompletos.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<?> editUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer usuarioId)
			throws NoSuchAlgorithmException {
		return usuarioService.editar(usuarioDto, usuarioId);
	}

	@PutMapping("/atualizarSenha/{usuarioId}")
	@Operation(summary = "Atualiza a senha do usuário", description = "Com usuarioId como parâmetro e uma string com o valor de senha válido no corpo da requisição, atualiza a senha do usuário.")
	@ApiResponse(responseCode = "201", description = "A senha atualizada com sucesso.",
		    content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "400", description = "Os dados estão incompletos/inválido.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<?> resetUsuario(@RequestBody String senha, @PathVariable Integer usuarioId) {
		return usuarioService.editarSenha(senha, usuarioId);
	}

	@PutMapping("/lista/{usuarioId}/{habilitado}")
	@Operation(summary = "Habilita ou desabilita um usuário", description = "Com usuarioId e um boolean como parâmetros, altera a habilitacao do usuário com id do parametro no banco de dados, habilitando e desabilitando acesso do usuário no sistema.")
	@ApiResponse(responseCode = "201", description = "A senha atualizada com sucesso.",
		    content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "400", description = "Os dados estão incompletos/inválido.",
		    content = @Content(mediaType = "application/json"))
	public ResponseEntity<?> updateHabilitado(@PathVariable Integer usuarioId, @PathVariable boolean habilitado) {
		return usuarioService.editarPermissao(usuarioId, habilitado);
	}

}
