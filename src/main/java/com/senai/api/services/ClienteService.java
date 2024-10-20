package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.ClienteDto;

@Service
public interface ClienteService {

	ResponseEntity<?> cadastrar(ClienteDto clienteDto);

	ResponseEntity<?> editar(ClienteDto clienteDto, Integer clienteId);

}
