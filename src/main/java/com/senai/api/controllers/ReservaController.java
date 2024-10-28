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

import com.senai.api.dto.ReservaDto;
import com.senai.api.services.ReservaService;

@RestController
@RequestMapping("/api/hospedagem")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@PostMapping("/reservas")
	public ResponseEntity<?> insertReserva(@RequestBody ReservaDto reservaDto) {
		return reservaService.cadastrar(reservaDto);
	}

	@PutMapping("/reservas/{reservaId}")
	public ResponseEntity<?> updateReserva(@RequestBody ReservaDto reservaDto, @PathVariable Integer reservaId) {
		return reservaService.editar(reservaDto, reservaId);
	}

	@GetMapping("/reservas")
	public List<ReservaDto> getReservas() {
		return reservaService.listarReservas();
	}

	@GetMapping("/reservas/{reservaId}")
	public ReservaDto recuperarReservas(@PathVariable Integer reservaId) {
		return reservaService.reservaById(reservaId);
	}

	@PutMapping("/reservas/{reservaId}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable Integer reservaId, @PathVariable String status) {
		return reservaService.editarStatus(reservaId, status);
	}
}
