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

import com.senai.api.dto.ClienteDto;
import com.senai.api.models.Cliente;
import com.senai.api.repository.ClienteRepository;
import com.senai.api.services.ClienteService;

@RestController
@RequestMapping("/api/hospedagem")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping("/{usuarioId}/clientes")
	public ResponseEntity<?> insertCliente(@RequestBody ClienteDto clienteDto, @PathVariable Integer usuarioId) {
		return clienteService.cadastrar(clienteDto, usuarioId);
	}

	@PutMapping("{usuarioId}/clientes/{clienteId}")
	public ResponseEntity<?> updateCliente(@RequestBody ClienteDto clienteDto, @PathVariable Integer usuarioId,
			@PathVariable Integer clienteId) {
		return clienteService.editar(clienteDto, usuarioId, clienteId);
	}

	@GetMapping("/clientes")
	public List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}

	@GetMapping("/clientes/{clienteId}")
	public Cliente recuperarAmenidades(@PathVariable Integer clienteId) {
		return clienteRepository.getReferenceById(clienteId);
	}

}
