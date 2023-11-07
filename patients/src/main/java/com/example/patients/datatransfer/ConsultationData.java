package com.example.patients.datatransfer;

import com.example.patients.models.Patients;

public record ConsultationData(Long id, String nome, String email, String telefone, String cpf) {

	public ConsultationData(Patients paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
	}
}
