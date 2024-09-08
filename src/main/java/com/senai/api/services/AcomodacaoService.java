package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.AcomodacaoDto;

@Service
public interface AcomodacaoService {

	ResponseEntity<?> editar(AcomodacaoDto acomodacaoDto, Integer acomodacaoId);

	ResponseEntity<?> cadastrar(AcomodacaoDto acomodacaoDto);
}
