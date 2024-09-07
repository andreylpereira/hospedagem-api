package com.senai.api.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "acomodacoes")
public class Acomodacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Integer capacidade;

	@OneToMany(mappedBy = "acomodacao", cascade = CascadeType.ALL)
	private Set<Reserva> reservas;

	@ManyToMany
	@JoinTable(name = "acomodacao_amenidade", joinColumns = @JoinColumn(name = "acomodacao_id"), inverseJoinColumns = @JoinColumn(name = "amenidade_id", nullable = false))
	private Set<Amenidade> amenidades;

	public Acomodacao() {
	}

	public Acomodacao(Long id, String nome, String descricao, Integer capacidade, Set<Reserva> reservas,
			Set<Amenidade> amenidades) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.reservas = reservas;
		this.amenidades = amenidades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Set<Amenidade> getAmenidades() {
		return amenidades;
	}

	public void setAmenidades(Set<Amenidade> amenidades) {
		this.amenidades = amenidades;
	}

}
