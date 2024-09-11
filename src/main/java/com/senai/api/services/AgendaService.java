package com.senai.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.senai.api.dto.AgendaDto;

@Service
public interface AgendaService {

	List<AgendaDto> gerarAgenda(LocalDateTime mes, Integer acomodacaoId);

}
