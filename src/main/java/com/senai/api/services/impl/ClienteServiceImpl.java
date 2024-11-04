package com.senai.api.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.senai.api.dto.ClienteDto;
import com.senai.api.models.Cliente;
import com.senai.api.models.Usuario;
import com.senai.api.repository.ClienteRepository;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.services.ClienteService;
import com.senai.api.services.UsuarioService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioService usuarioService,
			UsuarioRepository usuarioRepository) {
		this.clienteRepository = clienteRepository;
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public ResponseEntity<?> cadastrar(ClienteDto clienteDto, Integer usuarioId) {

		String cpf = usuarioService.formatCpf(clienteDto.getCpf());
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();
		Boolean isExists = clienteRepository.existsByCpf(cpf);
		Boolean isValid = usuarioService.validCpf(cpf);

		if (!isValid) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente com o CPF inválido.");
		} else if (isExists) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente com o mesmo CPF já existe.");
		} else if (!isUser) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Funcionário não existe.");
		}
		Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		cliente.setCpf(cpf);
		cliente.setFuncionario(funcionario);
		clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cliente adicionado ao banco de dados.");

	}

	@Override
	public ResponseEntity<?> editar(ClienteDto clienteDto, Integer usuarioId, Integer clienteId) {
		String cpf = usuarioService.formatCpf(clienteDto.getCpf());
		Boolean isUser = usuarioRepository.findById(usuarioId).isPresent();

		Boolean isExists = clienteRepository.existsById(clienteId);
		if (!isExists) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Não existe cliente com este ID.");
		} else if (!isUser) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Funcionário não existe.");
		}

		Cliente cliente = new Cliente();
		Usuario funcionario = usuarioRepository.getReferenceById(usuarioId);
		BeanUtils.copyProperties(clienteDto, cliente);
		cliente.setId(clienteId);
		cliente.setCpf(cpf);
		cliente.setFuncionario(funcionario);
		clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body("Cliente atualizado no banco de dados.");

	}

	@Override
	public ResponseEntity<?> recuperarClientes() {
		try {
			List<Cliente> clientes = clienteRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(clientes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Não foi possível recuperar dados.");
		}
	}

	@Override
	public ResponseEntity<?> recuperarCliente(Integer clienteId) {
		try {
			Cliente cliente = clienteRepository.getReferenceById(clienteId);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Não foi possível recuperar dados.");
		}
	}

}
