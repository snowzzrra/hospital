package com.demo.test.model;

import com.demo.test.datatransfer.ConsultationForm;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "consultas")

public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private ConsultationDate data;
	private boolean cancelado;
	private Long medico;
	private Long paciente;
	@Enumerated(EnumType.STRING)
	private Cancels motivo;

	public Consultation() {
	}

	public Consultation(ConsultationForm dados) {
		this.medico = dados.medico();
		this.paciente = dados.paciente();
		this.cancelado = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConsultationDate getData() {
		return data;
	}

	public void setData(ConsultationDate data) {
		this.data = data;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Long getDoctor() {
		return medico;
	}

	public void setMedico(Long medico) {
		this.medico = medico;
	}

	public Long getPatient() {
		return paciente;
	}

	public void setPaciente(Long paciente) {
		this.paciente = paciente;
	}

	public Cancels getMotivo() {
		return motivo;
	}

	public void setMotivo(Cancels motivo) {
		this.motivo = motivo;
	}

}