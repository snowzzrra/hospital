package com.example.doctor.datatransfer;

import com.example.doctor.models.Address;
import com.example.doctor.models.Expertise;
import com.example.doctor.models.Doctor;

public record DoctorData(Long id, String nome, String email, String telefone, String crm, Address endereco,
		Expertise especialidade) {

	public DoctorData(Doctor medico) {
		this(medico.getId(), medico.getName(), medico.getEmail(), medico.getPhoneNumber(), medico.getCrm(),
				medico.getAddress(), medico.getExpertise());
	}

}
