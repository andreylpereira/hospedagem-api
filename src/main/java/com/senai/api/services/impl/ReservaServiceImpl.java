package com.senai.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.api.dto.ReservaDto;
import com.senai.api.enums.Status;
import com.senai.api.models.Acomodacao;
import com.senai.api.models.Cliente;
import com.senai.api.models.Reserva;
import com.senai.api.models.Usuario;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.repository.ClienteRepository;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.repository.ReservaRepository;
import com.senai.api.services.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	private final UsuarioRepository usuarioRepository;
	private final ClienteRepository clienteRepository;
	private final AcomodacaoRepository acomodacaoRepository;
	private final ReservaRepository reservaRepository;

	public ReservaServiceImpl(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository,
			AcomodacaoRepository acomodacaoRepository, ReservaRepository reservaRepository) {
		this.usuarioRepository = usuarioRepository;
		this.clienteRepository = clienteRepository;
		this.acomodacaoRepository = acomodacaoRepository;
		this.reservaRepository = reservaRepository;
	}

	@Override
	@Transactional
	public ResponseEntity<?> cadastrar(ReservaDto reservaDto) {

		// Validações
		ResponseEntity<?> validacaoDatas = validarDatas(reservaDto);
		if (validacaoDatas != null) {
			return validacaoDatas;
		}
		ResponseEntity<?> validacaoReserva = validarReserva(reservaDto);
		if (validacaoReserva != null) {
			return validacaoReserva;
		}

		// Verificar Disponibilidade de forma otimizada
		Boolean isAvailable = verificarDisponibilidade(reservaDto.getAcomodacaoId(), reservaDto.getDataInicio(),
				reservaDto.getDataFim());
		if (!isAvailable) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O período solicitado de reserva está ocupado.");
		}

		// Buscar dados necessários
		Usuario funcionario = fetchUsuario(reservaDto.getFuncionarioId());
		Cliente cliente = fetchCliente(reservaDto.getClienteId());
		Acomodacao acomodacao = fetchAcomodacao(reservaDto.getAcomodacaoId());

		if (funcionario == null || cliente == null || acomodacao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados não encontrados.");
		}

		// Criar e salvar a nova reserva
		Reserva reserva = new Reserva();
		BeanUtils.copyProperties(reservaDto, reserva);
		reserva.setFuncionario(funcionario);
		reserva.setCliente(cliente);
		reserva.setAcomodacao(acomodacao);
		reservaRepository.save(reserva);

		return ResponseEntity.status(HttpStatus.CREATED).body("Reserva efetuada com sucesso.");
	}

	@Override
	@Transactional
	public ResponseEntity<?> editar(ReservaDto reservaDto, Integer reservaId) {
		Optional<Reserva> reservaExistenteOpt = reservaRepository.findById(reservaId);
		if (reservaExistenteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada.");
		}

		// Validações
		ResponseEntity<?> validacaoDatas = validarDatas(reservaDto);
		if (validacaoDatas != null) {
			return validacaoDatas;
		}
		ResponseEntity<?> validacaoReserva = validarReserva(reservaDto);
		if (validacaoReserva != null) {
			return validacaoReserva;
		}

		// Verificar Disponibilidade considerando a edição
		Boolean isAvailable = verificarDisponibilidade(reservaDto.getAcomodacaoId(), reservaDto.getDataInicio(),
				reservaDto.getDataFim(), reservaId);
		if (!isAvailable) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O período solicitado de reserva está ocupado.");
		}

		// Buscar dados necessários
		Usuario funcionario = fetchUsuario(reservaDto.getFuncionarioId());
		Cliente cliente = fetchCliente(reservaDto.getClienteId());
		Acomodacao acomodacao = fetchAcomodacao(reservaDto.getAcomodacaoId());

		if (funcionario == null || cliente == null || acomodacao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados não encontrados.");
		}

		// Atualizar a reserva existente
		Reserva reserva = reservaExistenteOpt.get();
		BeanUtils.copyProperties(reservaDto, reserva);
		reserva.setFuncionario(funcionario);
		reserva.setCliente(cliente);
		reserva.setAcomodacao(acomodacao);
		reservaRepository.save(reserva);

		return ResponseEntity.status(HttpStatus.OK).body("Reserva atualizada com sucesso.");
	}

	private ResponseEntity<?> validarReserva(ReservaDto reservaDto) {
		if (reservaDto.getClienteId() == null || reservaDto.getAcomodacaoId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados obrigatórios não fornecidos.");
		}
		return null;
	}

	private ResponseEntity<?> validarDatas(ReservaDto reservaDto) {
		if (reservaDto.getDataInicio() == null || reservaDto.getDataFim() == null
				|| reservaDto.getDataFim().isBefore(reservaDto.getDataInicio())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("O período de reserva não foi definido corretamente.");
		}
		return null;
	}

	// Método otimizado de verificação de disponibilidade
	@Override
	public Boolean verificarDisponibilidade(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim) {
		return reservaRepository.existsReservaConflitante(acomodacaoId, dataInicio, dataFim);
	}

	@Override
	public Boolean verificarDisponibilidade(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim,
			Integer reservaId) {
		return reservaRepository.existsReservaConflitanteParaEdicao(acomodacaoId, dataInicio, dataFim, reservaId);
	}

	private Usuario fetchUsuario(Integer usuarioId) {
		return usuarioId != null ? usuarioRepository.findById(usuarioId).orElse(null) : null;
	}

	private Cliente fetchCliente(Integer clienteId) {
		return clienteId != null ? clienteRepository.findById(clienteId).orElse(null) : null;
	}

	private Acomodacao fetchAcomodacao(Integer acomodacaoId) {
		return acomodacaoId != null ? acomodacaoRepository.findById(acomodacaoId).orElse(null) : null;
	}

	// Localiza uma reserva pelo ID
	@Override
	public ReservaDto reservaById(Integer reservaId) {
		Reserva reserva = reservaRepository.findById(reservaId).orElseThrow();

		Integer responsavelId = reserva.getFuncionario() != null ? reserva.getFuncionario().getId() : null;
		Integer clienteId = reserva.getCliente() != null ? reserva.getCliente().getId() : null;
		Integer acomodacaoId = reserva.getAcomodacao() != null ? reserva.getAcomodacao().getId() : null;

		return new ReservaDto(reserva.getId(), responsavelId, clienteId, acomodacaoId, reserva.getDataInicio(),
				reserva.getDataFim(), reserva.getStatus());
	}

	// Lista todas as reservas
	@Override
	public List<ReservaDto> listarReservas() {
		List<Reserva> reservas = reservaRepository.findAll();

		return reservas.stream().map(reserva -> {
			Integer responsavelId = reserva.getFuncionario().getId();
			Integer clienteId = reserva.getCliente().getId();
			Integer acomodacaoId = reserva.getAcomodacao().getId();

			return new ReservaDto(reserva.getId(), responsavelId, clienteId, acomodacaoId, reserva.getDataInicio(),
					reserva.getDataFim(), reserva.getStatus());
		}).collect(Collectors.toList());
	}
}
