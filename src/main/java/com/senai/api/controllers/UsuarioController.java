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

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/lista")
	public ResponseEntity<?> findUsuarios() {
		return usuarioService.recuperarUsuarios();
	}

	@GetMapping("/lista/{usuarioId}")
	public ResponseEntity<?> findUsuario(@PathVariable Integer usuarioId) {
		return usuarioService.recuperarUsuario(usuarioId);
	}

	@PostMapping("/cadastrarUsuario")
	public ResponseEntity<?> insertUsuario(@RequestBody UsuarioDto usuarioDto) throws NoSuchAlgorithmException {
		return usuarioService.cadastrar(usuarioDto);
	}

	@PutMapping("/atualizarUsuario/{usuarioId}")
	public ResponseEntity<?> editUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer usuarioId)
			throws NoSuchAlgorithmException {
		return usuarioService.editar(usuarioDto, usuarioId);
	}

	@PutMapping("/atualizarSenha/{usuarioId}")
	public ResponseEntity<?> resetUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer usuarioId) {
		return usuarioService.editarSenha(usuarioDto, usuarioId);
	}

	@PutMapping("/lista/{usuarioId}/{habilitado}")
	public ResponseEntity<?> updateHabilitado(@PathVariable Integer usuarioId, @PathVariable boolean habilitado) {
		return usuarioService.editarPermissao(usuarioId, habilitado);
	}

}
