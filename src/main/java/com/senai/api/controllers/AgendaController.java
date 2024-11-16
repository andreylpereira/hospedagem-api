package com.senai.api.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dto.AgendaDto;
import com.senai.api.dto.AgendaMensalDto;
import com.senai.api.services.AgendaService;

@RestController
@RequestMapping("/api/hospedagem")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@GetMapping("/agenda/datas/{acomodacaoId}/{mes}")
	public List<AgendaDto> recuperarReservas(@PathVariable LocalDateTime mes, @PathVariable Integer acomodacaoId) {
		return agendaService.gerarAgenda(mes, acomodacaoId);
	}

	  @GetMapping("/agenda/{acomodacaoId}/{mes}")
	    public List<AgendaMensalDto> obterAgendaMensal(@PathVariable LocalDateTime mes, @PathVariable Integer acomodacaoId) {
	        return agendaService.gerarAgendaMensal(mes, acomodacaoId);
	    }
	  
	  @GetMapping("/agenda/{mes}")
	    public List<AgendaMensalDto> obterAgendaTempoReal(@PathVariable LocalDateTime mes) {
	        return agendaService.gerarAgendaTempoReal(mes);
	    }
}
