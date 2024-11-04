package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.ClienteDto;

@Service
public interface ClienteService {

	ResponseEntity<?> cadastrar(ClienteDto clienteDto, Integer usuarioId);

	ResponseEntity<?> editar(ClienteDto clienteDto, Integer usuarioId, Integer clienteId);

	ResponseEntity<?> recuperarClientes();

	ResponseEntity<?> recuperarCliente(Integer clienteId);

}
