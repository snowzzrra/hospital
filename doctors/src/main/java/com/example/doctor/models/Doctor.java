package com.example.doctor.models;

import com.example.doctor.datatransfer.DoctorForm;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "medicos")
@NoArgsConstructor

public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Expertise especialidade;
	@OneToOne
	private Address endereco;
	private boolean apagado;

	public Doctor() {

	}

	public Doctor(DoctorForm dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.setErased(false);
	}

	public Address getAddress() {
		return this.endereco;
	}

	public void setAddress(Address endereco) {
		this.endereco = endereco;
	}

	public String getName() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return telefone;
	}

	public void setPhoneNumber(String telefone) {
		this.telefone = telefone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Expertise getExpertise() {
		return especialidade;
	}

	public void setExpertise(Expertise especialidade) {
		this.especialidade = especialidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isErased() {
		return apagado;
	}

	public void setErased(boolean apagado) {
		this.apagado = apagado;
	}

}
