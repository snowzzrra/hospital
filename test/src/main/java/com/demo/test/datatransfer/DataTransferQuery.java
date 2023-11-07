package com.demo.test.datatransfer;

import com.demo.test.model.Consultation;
import com.demo.test.model.ConsultationDate;
import com.demo.test.model.Cancels;

public record DataTransferQuery(Long id, DoctorDataTransfer medico, PatientDataTransfer paciente, Cancels motivo,
		boolean cancelado, ConsultationDate data) {
	public DataTransferQuery(Consultation consulta, DoctorDataTransfer medico, PatientDataTransfer paciente) {
		this(consulta.getId(), medico, paciente, consulta.getMotivo(), consulta.isCancelado(), consulta.getData());
	}

	public DoctorDataTransfer medico() {
		return medico;
	}

	public PatientDataTransfer paciente() {
		return paciente;
	}

	public ConsultationDate data() {
		return data;
	}
}
