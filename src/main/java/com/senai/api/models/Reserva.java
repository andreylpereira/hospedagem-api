package com.senai.api.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_criador_id", nullable = false)
	private Usuario usuario_criador;

	@ManyToOne
	@JoinColumn(name = "usuario_editor_id", nullable = false)
	private Usuario usuario_editor;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "acomodacao_id", nullable = false)
	private Acomodacao acomodacao;

	private LocalDateTime criado;
	private LocalDateTime editado;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;

	public Reserva() {
	}

	public Reserva(Long id, Usuario usuario_criador, Usuario usuario_editor, Cliente cliente, Acomodacao acomodacao,
			LocalDateTime criado, LocalDateTime editado, LocalDateTime dataInicio, LocalDateTime dataFim) {
		this.id = id;
		this.usuario_criador = usuario_criador;
		this.usuario_editor = usuario_editor;
		this.cliente = cliente;
		this.acomodacao = acomodacao;
		this.criado = criado;
		this.editado = editado;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario_criador() {
		return usuario_criador;
	}

	public void setUsuario_criador(Usuario usuario_criador) {
		this.usuario_criador = usuario_criador;
	}

	public Usuario getUsuario_editor() {
		return usuario_editor;
	}

	public void setUsuario_editor(Usuario usuario_editor) {
		this.usuario_editor = usuario_editor;
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

	public LocalDateTime getCriado() {
		return criado;
	}

	public void setCriado(LocalDateTime criado) {
		this.criado = criado;
	}

	public LocalDateTime getEditado() {
		return editado;
	}

	public void setEditado(LocalDateTime editado) {
		this.editado = editado;
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

}