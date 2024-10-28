package com.senai.api.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.UsuarioDto;

@Service
public interface UsuarioService {

	ResponseEntity<?> cadastrar(UsuarioDto usuarioDto) throws NoSuchAlgorithmException;

	String formatCpf(String cpf);

	Boolean isCpf(String cpf);

	Boolean validCpf(String cpf);
	
	Boolean verificarCpfExistente(String cpf) throws Exception;

	ResponseEntity<?> editar(UsuarioDto usuarioDto, Integer usuarioId) throws NoSuchAlgorithmException;

	ResponseEntity<?> editarSenha(UsuarioDto usuarioDto, Integer usuarioId);
	
	ResponseEntity<?> editarPermissao(Integer usuarioId, boolean habilitado);

}
