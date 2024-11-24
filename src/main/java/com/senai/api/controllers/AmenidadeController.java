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

import com.senai.api.dto.AmenidadeDto;
import com.senai.api.repository.AmenidadeRepository;
import com.senai.api.services.AmenidadeService;

@RestController
@RequestMapping("/api/hospedagem")
public class AmenidadeController {

	private AmenidadeService amenidadeService;
	private AmenidadeRepository amenidadeRepository;

	@Autowired
	public AmenidadeController(AmenidadeService amenidadeService, AmenidadeRepository amenidadeRepository) {
		this.amenidadeService = amenidadeService;
		this.amenidadeRepository = amenidadeRepository;
	}

	@PostMapping("{usuarioId}/amenidades")
	public ResponseEntity<?> insertAmenidade(@RequestBody AmenidadeDto amenidadeDto, @PathVariable Integer usuarioId) {
		return amenidadeService.cadastrar(amenidadeDto, usuarioId);
	}

	@PutMapping("{usuarioId}/amenidades/{amenidadeId}")
	public ResponseEntity<?> updateAmenidades(@RequestBody AmenidadeDto amenidadeDto, @PathVariable Integer usuarioId,
			@PathVariable Integer amenidadeId) {
		return amenidadeService.editar(amenidadeDto, usuarioId, amenidadeId);
	}

	@GetMapping("/amenidades")
	public ResponseEntity<?> findAmenidades() {
		return amenidadeService.recuperarAmenidades();
	}

	@GetMapping("/amenidades/{amenidadeId}")
	public ResponseEntity<?> findAmenidade(@PathVariable Integer amenidadeId) {
		return amenidadeService.recuperarAmenidade(amenidadeId);
	}

}
