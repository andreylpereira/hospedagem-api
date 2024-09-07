package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dto.UsuarioDto;
import com.senai.api.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastrarUsuario")
	public ResponseEntity<?> insertUsuario(@RequestBody UsuarioDto usuarioDto) {
		return usuarioService.cadastrar(usuarioDto);
	}

}
