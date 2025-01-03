package com.senai.api.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senai.api.enums.Status;

import jakarta.persistence.*;

@Entity
@Table(name = "reservas")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	@JsonIgnore
	private Usuario funcionario;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonBackReference
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "acomodacao_id")
	@JsonIgnore
	private Acomodacao acomodacao;

	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	public Reserva() {
	}

	public Reserva(Integer id, Usuario funcionario, Cliente cliente, Acomodacao acomodacao, LocalDateTime dataInicio,
			LocalDateTime dataFim, Status status) {
		this.id = id;
		this.funcionario = funcionario;
		this.cliente = cliente;
		this.acomodacao = acomodacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Acomodacao getAcomodacao() {
		return acomodacao;
	}

	public void setAcomodacao(Acomodacao acomodacao) {
		this.acomodacao = acomodacao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
