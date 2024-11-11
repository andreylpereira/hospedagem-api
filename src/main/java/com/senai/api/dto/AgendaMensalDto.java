package com.senai.api.dto;

import java.time.LocalDateTime;

public class AgendaMensalDto {

    private Integer reservaId;
    private String clienteNome;
    private String clienteEmail;
    private String clienteTelefone;
    private String funcionarioNome;
    private String acomodacaoNome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public AgendaMensalDto(Integer reservaId, String clienteNome, String clienteEmail, String clienteTelefone,
                           String funcionarioNome, String acomodacaoNome, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.reservaId = reservaId;
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.clienteTelefone = clienteTelefone;
        this.funcionarioNome = funcionarioNome;
        this.acomodacaoNome = acomodacaoNome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

    public String getAcomodacaoNome() {
        return acomodacaoNome;
    }

    public void setAcomodacaoNome(String acomodacaoNome) {
        this.acomodacaoNome = acomodacaoNome;
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
