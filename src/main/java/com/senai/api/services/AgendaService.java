package com.senai.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.api.dto.AgendaDto;
import com.senai.api.dto.AgendaMensalDto;

@Service
public interface AgendaService {

	List<AgendaDto> gerarAgenda(LocalDateTime mes, Integer acomodacaoId);

	List<AgendaMensalDto> gerarAgendaMensal(LocalDateTime mes, Integer acomodacaoId);

}
