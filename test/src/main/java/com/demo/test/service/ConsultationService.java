package com.demo.test.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.test.datatransfer.DataTransferQuery;
import com.demo.test.datatransfer.ConsultationForm;
import com.demo.test.datatransfer.DoctorDataTransfer;
import com.demo.test.datatransfer.PatientDataTransfer;
import com.demo.test.model.Consultation;
import com.demo.test.model.ConsultationDate;
import com.demo.test.repositories.ConsultationRepo;
import com.demo.test.repositories.ConsultationDateRepo;

@Service
public class ConsultationService<R> {

	@Autowired
	private ConsultationRepo consultationRepository;

	@Autowired
	private ConsultationDateRepo dataRepository;

	@Autowired
	private DoctorFeignService doctorFeign;

	@Autowired
	private PatientFeignService patientFeign;

	public List<DataTransferQuery> consultationDateConversion(List<Consultation> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().map(emp -> new DataTransferQuery(emp, this.fetchDoctor(emp.getDoctor()),
				this.fetchPatient(emp.getPatient()))).collect(Collectors.toList());

	}

	public List<DataTransferQuery> searchAll() {
		return this.consultationDateConversion(this.consultationRepository.findAll());
	}

	public DoctorDataTransfer fetchDoctor(Long id) {
		DoctorDataTransfer medico = doctorFeign.getDoctors(id);
		if (medico == null) {
			System.out.println("nuloooooooooooooooooooooooooooooooooooooooooooooooooooo \n \n"
					+ "\n\n *********************************************");
			System.out.println("\n\n ************************************************************");
		}
		return medico;
	}

	public List<DoctorDataTransfer> fetchAllMedico() {
		List<DoctorDataTransfer> medicos = doctorFeign.getAllDoctors();
		return medicos;
	}

	public PatientDataTransfer fetchPatient(Long id) {
		PatientDataTransfer paciente = patientFeign.getPatient(id);
		return paciente;
	}

	public DataTransferQuery getConsultById(Long id) {
		@SuppressWarnings("deprecation")
		Consultation consulta = consultationRepository.getById(id);
		DataTransferQuery consultaDados = new DataTransferQuery(consulta, this.fetchDoctor(consulta.getDoctor()),
				this.fetchPatient(consulta.getPatient()));
		return consultaDados;
	}

	public Consultation post(ConsultationForm dados) {
		Consultation consulta = new Consultation(dados);
		ConsultationDate data = new ConsultationDate(dados.dataConsulta());
		consulta.setData(data);
		DoctorDataTransfer medico = null;
		if (dados.medico() == null)
			medico = this.pickRandomDoctor(this.fetchAllMedico());
		else
			medico = this.fetchDoctor(consulta.getDoctor());
		consulta.setMedico(medico.id());
		dataRepository.save(data);
		consultationRepository.save(consulta);
		return consulta;
	}

	public DoctorDataTransfer pickRandomDoctor(List<DoctorDataTransfer> medicos) {
		if (medicos.size() == 0)
			return null;
		Random rndm = new Random();
		DoctorDataTransfer rndmElem = medicos.get(rndm.nextInt(medicos.size()));
		return rndmElem;
	}
}
