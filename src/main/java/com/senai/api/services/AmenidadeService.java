package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.AmenidadeDto;

@Service
public interface AmenidadeService {

	ResponseEntity<?> cadastrar(AmenidadeDto amenidadeDto, Integer usuarioId);

	ResponseEntity<?> editar(AmenidadeDto amenidadeDto, Integer usuarioId, Integer amenidadeId);

	ResponseEntity<?> recuperarAmenidades();

	ResponseEntity<?> recuperarAmenidade(Integer amenidadeId);

}
