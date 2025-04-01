package com.senai.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senai.api.enums.Status;
import com.senai.api.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.acomodacao.id = :acomodacaoId "
			+ "AND r.status NOT IN ('CONCLUIDO', 'CANCELADO') "
			+ "AND r.dataInicio < :dataFim AND r.dataFim > :dataInicio")
	boolean existsReservaConflitante(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim);

	@Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.acomodacao.id = :acomodacaoId "
			+ "AND r.status NOT IN ('CONCLUIDO', 'CANCELADO') " + "AND r.id != :reservaId "
			+ "AND r.dataInicio < :dataFim AND r.dataFim > :dataInicio")
	boolean existsReservaConflitanteParaEdicao(Integer acomodacaoId, LocalDateTime dataInicio, LocalDateTime dataFim,
			Integer reservaId);

}