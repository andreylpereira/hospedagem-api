package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.UsuarioDto;

@Service
public interface UsuarioService {

	ResponseEntity<?> cadastrar(UsuarioDto usuarioDto);

	String formatCpf(String cpf);

	Boolean isCpf(String cpf);

	Boolean validCpf(String cpf);
}
