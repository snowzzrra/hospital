package com.example.patients.datatransfer;

import com.example.patients.models.Address;
import com.example.patients.models.Patients;

public record PatientData(Long id, String nome, String email, String telefone, String cpf, Address endereco) {

	public PatientData(Patients paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(),
				paciente.getEndereco());
	}

}
