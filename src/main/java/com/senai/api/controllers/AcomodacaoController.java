package com.senai.api.controllers;

import java.util.List;

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
import com.senai.api.models.Acomodacao;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.services.AcomodacaoService;

@RestController
@RequestMapping("/api/hospedagem")
public class AcomodacaoController {

	@Autowired
	private AcomodacaoService acomodacaoService;
	@Autowired
	private AcomodacaoRepository acomodacaoRepository;

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
	public List<Acomodacao> getAcomodacoes() {
		return acomodacaoRepository.findAll();
	}

	@GetMapping("/acomodacoes/{acomodacaoId}")
	public Acomodacao recuperarAmenidades(@PathVariable Integer acomodacaoId) {
		return acomodacaoRepository.getReferenceById(acomodacaoId);
	}

	@PutMapping("/acomodacoes/{acomodacaoId}/{habilitado}")
	public ResponseEntity<?> updateHabilitado(@PathVariable Integer acomodacaoId, @PathVariable boolean habilitado) {
		return acomodacaoService.habilitadoDesabilitado(acomodacaoId, habilitado);
	}

}
