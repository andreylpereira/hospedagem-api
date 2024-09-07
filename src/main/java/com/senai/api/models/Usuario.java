package com.senai.api.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String cpf;
	private String senha;
	private String perfil;
	private String nome;
	private String email;
	private boolean ativo;

	@OneToMany(mappedBy = "usuario_criador", cascade = CascadeType.ALL)
	private Set<Reserva> criador;

	@OneToMany(mappedBy = "usuario_editor", cascade = CascadeType.ALL)
	private Set<Reserva> editor;

	public Usuario() {
	}

	public Usuario(int id, String cpf, String senha, String perfil, String nome, String email, boolean ativo,
			Set<Reserva> criador, Set<Reserva> editor) {
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
		this.perfil = perfil;
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
		this.criador = criador;
		this.editor = editor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Reserva> getCriador() {
		return criador;
	}

	public void setCriador(Set<Reserva> criador) {
		this.criador = criador;
	}

	public Set<Reserva> getEditor() {
		return editor;
	}

	public void setEditor(Set<Reserva> editor) {
		this.editor = editor;
	}

}