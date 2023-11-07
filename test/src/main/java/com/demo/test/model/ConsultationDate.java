package com.demo.test.model;

import com.demo.test.datatransfer.ConsultationDateForm;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "datas")

public class ConsultationDate {
	@Override
	public String toString() {
		return "DataConsulta [id=" + id + ", dia_da_semana=" + dia_da_semana + ", mes=" + mes + ", ano=" + ano
				+ ", dia=" + dia + ", hora=" + hora + ", minuto=" + minuto + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Dias dia_da_semana;
	private int mes;
	private int ano;
	private int dia;
	private int hora;
	private int minuto;

	public ConsultationDate() {
	}

	public ConsultationDate(ConsultationDateForm dados) {
		this.ano = dados.ano();
		this.mes = dados.mes();
		this.dia = dados.dia();
		this.dia_da_semana = dados.dia_da_semana();
		this.hora = dados.hora();
		this.minuto = dados.minuto();
	}

	public Long getId() {
		return id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dias getDia_da_semana() {
		return dia_da_semana;
	}

	public void setDia_da_semana(Dias dia_da_semana) {
		this.dia_da_semana = dia_da_semana;
	}
}
