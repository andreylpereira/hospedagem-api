package com.senai.api.dto;

import java.util.Set;

import com.senai.api.models.Amenidade;

public class AcomodacaoDto {

	private Integer id;
	private String nome;
	private String descricao;
	private int capacidade;
	private Integer reservaId;
	private double preco;
	private Set<Amenidade> amenidades;
	private boolean habilitado;

	public AcomodacaoDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public Integer getReservaId() {
		return reservaId;
	}

	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Set<Amenidade> getAmenidades() {
		return amenidades;
	}

	public void setAmenidades(Set<Amenidade> amenidades) {
		this.amenidades = amenidades;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}
