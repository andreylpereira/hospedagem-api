package com.senai.api.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dto.ReservaDto;
import com.senai.api.enums.Status;
import com.senai.api.models.Acomodacao;
import com.senai.api.models.Cliente;
import com.senai.api.models.Reserva;
import com.senai.api.models.Usuario;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.repository.ClienteRepository;
import com.senai.api.repository.UsuarioRepository;
import com.senai.api.services.ReservaService;
import com.senai.api.repository.ReservaRepository;

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

	/*
	 * Valida os dados de entrada da reserva, depois se está disponivel para reserva.
	 * Caso passar pelas validações efetua a reserva.
	 * */
	@Override
	public ResponseEntity<?> cadastrar(ReservaDto reservaDto) {

		ResponseEntity<?> validarReserva = validarReservaDto(reservaDto);
		if (validarReserva != null) {
			return validarReserva;
		}

		if (reservaDto.getDataInicio() == null || reservaDto.getDataFim() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("O período de reserva não foi definido corretamente.");
		}

		Boolean isAvailable = verificarDisponibilidade(reservaDto.getAcomodacaoId(), reservaDto.getDataInicio(),
				reservaDto.getDataFim());
		if (!isAvailable) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O período solicitado de reserva está ocupado.");
		}

		Usuario funcionario = fetchUsuario(reservaDto.getFuncionarioId());
		Cliente cliente = fetchCliente(reservaDto.getClienteId());
		Acomodacao acomodacao = fetchAcomodacao(reservaDto.getAcomodacaoId());

		if (funcionario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário criador não encontrado.");
		}
		if (cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		if (acomodacao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada.");
		}

		Reserva reserva = new Reserva();
		BeanUtils.copyProperties(reservaDto, reserva);
		reserva.setFuncionario(funcionario);
		reserva.setCliente(cliente);
		reserva.setAcomodacao(acomodacao);
		reservaRepository.save(reserva);

		return ResponseEntity.status(HttpStatus.CREATED).body("Reserva efetuada com sucesso.");
	}

	/*
	 * Busca uma reserva do banco de dados e verifica se ela existe ou não. Também valida os dados do corpo da requisição.
	 * Faz também validações comparando o corpo da requisição com a reserva que foi buscada do banco, caso estiver OK, faz atualização/edição no banco.
	 * */
	@Override
	public ResponseEntity<?> editar(ReservaDto reservaDto, Integer reservaId) {
		Optional<Reserva> reservaExistenteOpt = reservaRepository.findById(reservaId);

		if (reservaDto.getDataInicio() == null || reservaDto.getDataFim() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("O período de reserva não foi definido corretamente.");
		}

		if (reservaExistenteOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada.");
		}

		Reserva reservaExistente = reservaExistenteOpt.get();

		ResponseEntity<?> validarReserva = validarReservaDto(reservaDto);
		if (validarReserva != null) {
			return validarReserva;
		}

		boolean isAlterado = false;

		if (!reservaExistente.getStatus().equals(reservaDto.getStatus())) {
			isAlterado = true;
			reservaExistente.setStatus(reservaDto.getStatus());
		}

			Boolean isAvailable = verificarDisponibilidade(reservaDto.getAcomodacaoId(), reservaDto.getDataInicio(),
					reservaDto.getDataFim(), reservaId);
			if (!isAvailable) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("O período solicitado de reserva está ocupado.");
			} else {				
				isAlterado = true;
				reservaExistente.setDataInicio(reservaDto.getDataInicio());
				reservaExistente.setDataFim(reservaDto.getDataFim());	
			}


		if (!reservaExistente.getCliente().getId().equals(reservaDto.getClienteId())) {
			isAlterado = true;
			Cliente cliente = fetchCliente(reservaDto.getClienteId());
			if (cliente == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
			}
			reservaExistente.setCliente(cliente);
		}

		if (!reservaExistente.getAcomodacao().getId().equals(reservaDto.getAcomodacaoId())) {
			isAlterado = true;
			Acomodacao acomodacao = fetchAcomodacao(reservaDto.getAcomodacaoId());
			if (acomodacao == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acomodação não encontrada.");
			}
			reservaExistente.setAcomodacao(acomodacao);
		}

		if (!reservaExistente.getFuncionario().getId().equals(reservaDto.getFuncionarioId())) {
			isAlterado = true;
			Usuario funcionario = fetchUsuario(reservaDto.getFuncionarioId());
			if (funcionario == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário criador não encontrado.");
			}
			reservaExistente.setFuncionario(funcionario);
		}

		if (!isAlterado) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum dado possível foi alterado.");
		}

		reservaRepository.save(reservaExistente);

		return ResponseEntity.status(HttpStatus.OK).body("Reserva atualizada com sucesso.");
	}

	//Validador da reserva utilizado no cadastrar/editar
	private ResponseEntity<?> validarReservaDto(ReservaDto reservaDto) {
		if (reservaDto.getClienteId() == null || reservaDto.getAcomodacaoId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados obrigatórios não fornecidos.");
		}
		return null;
	}

	/*
	 * Verificar se os ID's não são nulo, caso contrario retorna o respectivo objeto do banco. 
	  */
	private Usuario fetchUsuario(Integer usuarioId) {
		return usuarioId != null ? usuarioRepository.findById(usuarioId).orElse(null) : null;
	}

	private Cliente fetchCliente(Integer clienteId) {
		return clienteId != null ? clienteRepository.findById(clienteId).orElse(null) : null;
	}

	private Acomodacao fetchAcomodacao(Integer acomodacaoId) {
		return acomodacaoId != null ? acomodacaoRepository.findById(acomodacaoId).orElse(null) : null;
	}

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

	
	/*
	 * Método não utilizado, tendo em vista que na regra de negócio a edição do status é feita por meio do editar. 
	  */
	@Override
	public ResponseEntity<?> editarStatus(Integer reservaId, String status) {
		Optional<Reserva> reservaOpt = reservaRepository.findById(reservaId);
		if (reservaOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva com ID " + reservaId + " não encontrada.");
		}

		Reserva reserva = reservaOpt.get();
		Status novoStatus;
		try {
			novoStatus = Status.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil inválido.");
		}

		reserva.setStatus(novoStatus);
		reservaRepository.save(reserva);
		return ResponseEntity.status(HttpStatus.OK).body("Status da reserva atualizado com sucesso.");
	}

	@Override
	public ReservaDto reservaById(Integer reservaId) {
		Reserva reserva = reservaRepository.findById(reservaId).orElseThrow();

		Integer responsavelId = reserva.getFuncionario() != null ? reserva.getFuncionario().getId() : null;
		Integer clienteId = reserva.getCliente() != null ? reserva.getCliente().getId() : null;
		Integer acomodacaoId = reserva.getAcomodacao() != null ? reserva.getAcomodacao().getId() : null;

		return new ReservaDto(reserva.getId(), responsavelId, clienteId, acomodacaoId, reserva.getDataInicio(),
				reserva.getDataFim(), reserva.getStatus());
	}

	
	
	/*
	 * Verificador de disponibilidade para o cadastro, verifica se o periodo esta ocupado, comparando o que trás do banco com os LocalDateTime da requisição.
	 * */
	@Override
	public Boolean verificarDisponibilidade(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim) {

		List<Reserva> reservasAcomodacao = reservaRepository.findByAcomodacaoId(acomodacaoId);

		for (Reserva reserva : reservasAcomodacao) {
			LocalDateTime reservaInicio = reserva.getDataInicio();
			LocalDateTime reservaFim = reserva.getDataFim();

			if (dataInicio.isBefore(reservaFim) && dataFim.isAfter(reservaInicio)) {

				return false;
			}
		}

		return true;
	}
	
	/*
	 * Verificador de disponibilidade para o editar, verifica se o periodo esta ocupado, comparando o que trás do banco com os LocalDateTime da requisição.
	 * Ele ignora os dados da reserva que está sendo editada/atualizada.
	 * */
	public Boolean verificarDisponibilidade(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim, Integer reservaId) {

	    List<Reserva> reservasAcomodacao = reservaRepository.findByAcomodacaoId(acomodacaoId);

	    for (Reserva reserva : reservasAcomodacao) {
	        LocalDateTime reservaInicio = reserva.getDataInicio();
	        LocalDateTime reservaFim = reserva.getDataFim();

	     
	        if (!reserva.getId().equals(reservaId)) {
	     
	            if (dataInicio.isBefore(reservaFim) && dataFim.isAfter(reservaInicio)) {
	                return false;
	            }
	        }
	    }

	    return true;
	}
}
