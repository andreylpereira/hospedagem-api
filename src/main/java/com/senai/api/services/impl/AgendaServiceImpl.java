package com.senai.api.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.senai.api.dto.AgendaDto;
import com.senai.api.dto.ReservaDto;
import com.senai.api.repository.AcomodacaoRepository;
import com.senai.api.repository.ClienteRepository;
import com.senai.api.services.AgendaService;
import com.senai.api.services.ReservaService;


@Service
public class AgendaServiceImpl implements AgendaService {

	@SuppressWarnings("unused")
	private ClienteRepository clienteRepository;
	private ReservaService reservaService;
	@SuppressWarnings("unused")
	private AcomodacaoRepository acomodacaoRepository;


	public AgendaServiceImpl(ClienteRepository clienteRepository,
			AcomodacaoRepository acomodacaoRepository, ReservaService reservaService) {
		this.clienteRepository = clienteRepository;
		this.acomodacaoRepository = acomodacaoRepository;
		this.reservaService = reservaService;
	}

	public List<AgendaDto> gerarAgenda(LocalDateTime mes, Integer acomodacaoId) {
        List<AgendaDto> agenda = new ArrayList<>();
        Map<LocalDate, Boolean> disponibilidade = new HashMap<>();
        Map<LocalDate, Integer> reservaPorDia = new HashMap<>();

        List<ReservaDto> reservas = reservaService.listarReservas();

        LocalDate primeiroDiaDoMesAtual = mes.toLocalDate().withDayOfMonth(1);
        LocalDate ultimoDiaDoMesAtual = mes.toLocalDate().withDayOfMonth(mes.toLocalDate().lengthOfMonth());
        LocalDate primeiroDiaDoMesAnterior = primeiroDiaDoMesAtual.minusMonths(1);
        @SuppressWarnings("unused")
		LocalDate ultimoDiaDoMesAnterior = primeiroDiaDoMesAtual.minusDays(1);

        LocalDate dataInicial = primeiroDiaDoMesAnterior;
        LocalDate dataFinal = ultimoDiaDoMesAtual;
        for (LocalDate data = dataInicial; !data.isAfter(dataFinal); data = data.plusDays(1)) {
            disponibilidade.put(data, false);
        }

        for (ReservaDto reserva : reservas) {
            if (!reserva.getAcomodacaoId().equals(acomodacaoId)) {
                continue;
            }

            LocalDateTime entrada = reserva.getDataInicio();
            LocalDateTime saida = reserva.getDataFim();

            LocalDate entradaDia = entrada.toLocalDate();
            LocalDate saidaDia = saida.toLocalDate();

            if (saidaDia.isAfter(ultimoDiaDoMesAtual)) {
                saidaDia = ultimoDiaDoMesAtual;
            }

            if (entradaDia.isBefore(primeiroDiaDoMesAtual)) {
                entradaDia = primeiroDiaDoMesAtual;
            }

            for (LocalDate data = entradaDia; !data.isAfter(saidaDia); data = data.plusDays(1)) {
                if (!data.isBefore(primeiroDiaDoMesAnterior) && !data.isAfter(ultimoDiaDoMesAtual)) {
                    disponibilidade.put(data, true);
                    reservaPorDia.put(data, reserva.getId());
                }
            }
        }

        for (LocalDate data = primeiroDiaDoMesAtual; !data.isAfter(ultimoDiaDoMesAtual); data = data.plusDays(1)) {
            boolean ocupado = disponibilidade.getOrDefault(data, false);
            Integer reservaId = ocupado ? reservaPorDia.get(data) : null;
            LocalDateTime dataCompleta = data.atStartOfDay(); 
            agenda.add(new AgendaDto(dataCompleta, ocupado, reservaId));
        }

        return agenda;
    }

	
}