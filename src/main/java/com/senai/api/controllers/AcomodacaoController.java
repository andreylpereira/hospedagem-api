package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dto.AcomodacaoDto;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.services.AcomodacaoService;

@RestController
@RequestMapping("/api/hospedagem")
public class AcomodacaoController {

	private AcomodacaoService acomodacaoService;
	private AcomodacaoRepository acomodacaoRepository;

	@Autowired
	public AcomodacaoController(AcomodacaoService acomodacaoService, AcomodacaoRepository acomodacaoRepository) {
		this.acomodacaoService = acomodacaoService;
		this.acomodacaoRepository = acomodacaoRepository;
	}

	@PostMapping("{usuarioId}/acomodacoes")
	public ResponseEntity<?> insertAcomodacao(@RequestBody AcomodacaoDto acomodacaoDto,
			@PathVariable Integer usuarioId) {
		return acomodacaoService.cadastrar(acomodacaoDto, usuarioId);
	}

	@PutMapping("{usuarioId}/acomodacoes/{acomodacaoId}")
	public ResponseEntity<?> updateAcomodacao(@RequestBody AcomodacaoDto acomodacaoDto, @PathVariable Integer usuarioId,
			@PathVariable Integer acomodacaoId) {
		return acomodacaoService.editar(acomodacaoDto, usuarioId, acomodacaoId);
	}

	@GetMapping("/acomodacoes")
	public ResponseEntity<?> findAcomodacoes() {
		return acomodacaoService.recuperarAcomodacoes();
	}

	@GetMapping("/acomodacoes/{acomodacaoId}")
	public ResponseEntity<?> findAcomodacao(@PathVariable Integer acomodacaoId) {
		return acomodacaoService.recuperarAcomodacao(acomodacaoId);

	}

	@PutMapping("/acomodacoes/{acomodacaoId}/{habilitado}")
	public ResponseEntity<?> updateHabilitado(@PathVariable Integer acomodacaoId, @PathVariable boolean habilitado) {
		return acomodacaoService.habilitadoDesabilitado(acomodacaoId, habilitado);
	}

}
