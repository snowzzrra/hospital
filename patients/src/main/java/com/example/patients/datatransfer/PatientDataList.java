package com.example.patients.datatransfer;

import com.example.patients.models.Patients;

public record PatientDataList(String nome, String email, String cpf) {
	public PatientDataList(Patients paciente) {
		this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
