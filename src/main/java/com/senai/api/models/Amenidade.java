package com.senai.api.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "amenidades")
public class Amenidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;

	@ManyToMany(mappedBy = "amenidades")
	private Set<Acomodacao> acomodacoes;

	public Amenidade() {
	}

	public Amenidade(Long id, String nome, String descricao, Set<Acomodacao> acomodacoes) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.acomodacoes = acomodacoes;
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

	public Set<Acomodacao> getAcomodacoes() {
		return acomodacoes;
	}

	public void setAcomodacoes(Set<Acomodacao> acomodacoes) {
		this.acomodacoes = acomodacoes;
	}

}
