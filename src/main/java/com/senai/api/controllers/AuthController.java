package com.senai.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dto.AuthResponseDto;
import com.senai.api.models.Usuario;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.security.JWTGenerator;
import com.senai.api.services.UsuarioService;

@RestController
@RequestMapping("/")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;
	private JWTGenerator jwtGenerator;
	private UsuarioService usuarioService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository,
			PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, UsuarioService usuarioService) {
		this.authenticationManager = authenticationManager;
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtGenerator = jwtGenerator;
		this.usuarioService = usuarioService;
	}

	@PostMapping("/api/auth/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				usuarioService.formatCpf(usuario.getCpf()), usuario.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);

		return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.ACCEPTED);
	}

}