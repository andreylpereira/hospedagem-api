package com.senai.api.dto;
import java.time.LocalDateTime;

import com.senai.api.enums.Status;

public class ReservaDto {
    private Integer id;
    private Integer responsavelId;
    private Integer clienteId;
    private Integer acomodacaoId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Status status;

    public ReservaDto(Integer id, Integer responsavelId, Integer clienteId, Integer acomodacaoId,
                      LocalDateTime dataInicio, LocalDateTime dataFim, Status status) {
        this.id = id;
        this.responsavelId = responsavelId;
        this.clienteId = clienteId;
        this.acomodacaoId = acomodacaoId;
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

	public Integer getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(Integer responsavelId) {
		this.responsavelId = responsavelId;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getAcomodacaoId() {
		return acomodacaoId;
	}

	public void setAcomodacaoId(Integer acomodacaoId) {
		this.acomodacaoId = acomodacaoId;
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
