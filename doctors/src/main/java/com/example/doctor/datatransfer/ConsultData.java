package com.example.doctor.datatransfer;

import com.example.doctor.models.Expertise;
import com.example.doctor.models.Doctor;

public record ConsultData(Long id, String nome, String email, String telefone, String crm, Expertise especialidade) {

	public ConsultData(Doctor medico) {
		this(medico.getId(), medico.getName(), medico.getEmail(), medico.getPhoneNumber(), medico.getCrm(),
				medico.getExpertise());
	}

}
