package com.senai.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.ReservaDto;

@Service
public interface ReservaService {

	ResponseEntity<?> cadastrar(ReservaDto reservaDto);

	ResponseEntity<?> editar(ReservaDto reservaDto, Integer reservaId);

	List<ReservaDto> listarReservas();

	ReservaDto reservaById(Integer reservaId);

	ResponseEntity<?> editarStatus(Integer reservaId, String status);

	Boolean verificarDisponibilidade(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim);

}
