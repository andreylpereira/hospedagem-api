package com.senai.api.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String senha;
	private String perfil;
	private String nome;
	private String email;
	private boolean habilitado;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Reserva> responsavel;

	public Usuario() {
	}

	public Usuario(Integer id, String cpf, String senha, String perfil, String nome, String email, boolean habilitado,
			Set<Reserva> responsavel) {
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
		this.perfil = perfil;
		this.nome = nome;
		this.email = email;
		this.habilitado = habilitado;
		this.responsavel = responsavel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer usuario_id) {
		this.id = usuario_id;
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

	public boolean ishabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Set<Reserva> getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Set<Reserva> responsavel) {
		this.responsavel = responsavel;
	}

}