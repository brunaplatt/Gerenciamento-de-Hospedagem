package com.gerenciamento.gerenciamento.entity;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "A quantidade de adultos é obrigatória")
    private Integer adultos;

    @Column
    private Integer criancas;
   
    @Column
    private Integer condicaoPagamento;

    @NotBlank(message = "O responsável é obrigatório")
    private long responsavel;

    @NotBlank(message = "A data de entrada é obrigatória")
    private Date checkIn;

    @NotBlank(message = "A data de saída é obrigatória")
    private Date checkOut;
    
    private Boolean cafeIncluso;

    @NotBlank(message = "O tipo de acomodação é obrigatório")
    private Integer acomodacao;

    private double valorTotal; // Adiciona a nova coluna
    
    public Reserva(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAdultos() {
        return adultos;
    }

    public void setAdultos(Integer adultos) {
        this.adultos = adultos;
    }

    public Integer getCriancas() {
        return criancas;
    }

    public void setCriancas(Integer criancas) {
        this.criancas = criancas;
    }

    public Integer getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(Integer condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public long getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(long responsavel) {
        this.responsavel = responsavel;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Integer acomodacao) {
        this.acomodacao = acomodacao;
    }

    public boolean getCafeIncluso() {
        return cafeIncluso;
    }

    public void setCafeIncluso(boolean cafeIncluso) {
        this.cafeIncluso = cafeIncluso;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "hospede [id=" + id 
        + ", adultos=" + adultos 
        + ", criancas=" + criancas 
        + ", condicaoPagamento=" + condicaoPagamento 
        + ", responsavel=" + responsavel 
        + ", checkIn=" + checkIn 
        + ", checkOut=" + checkOut 
        + ", acomodacao=" + acomodacao 
        + ", cafeIncluso=" + cafeIncluso 
        + ", valorTotal=" + valorTotal 
        + "]";
    }
}
