package com.example.doctor.datatransfer;

import com.example.doctor.models.Expertise;
import com.example.doctor.models.Doctor;

public record DoctorListData(String nome, String email, String crm, Expertise especialidade) {

	public DoctorListData(Doctor medico) {
		this(medico.getName(), medico.getEmail(), medico.getCrm(), medico.getExpertise());
	}

}
