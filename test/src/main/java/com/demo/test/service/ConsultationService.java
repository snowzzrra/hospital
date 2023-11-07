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
	private ConsultationRepo consultaRepository;

	@Autowired
	private ConsultationDateRepo dataRepository;

	@Autowired
	private DoctorFeignService medicoFeign;

	@Autowired
	private PatientFeignService pacienteFeign;

	public List<DataTransferQuery> converterDadosConsulta(List<Consultation> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().map(emp -> new DataTransferQuery(emp, this.fetchMedico(emp.getMedico()),
				this.fetchPaciente(emp.getPaciente()))).collect(Collectors.toList());

	}

	public List<DataTransferQuery> buscarTodos() {
		return this.converterDadosConsulta(this.consultaRepository.findAll());
	}

	public DoctorDataTransfer fetchMedico(Long id) {
		DoctorDataTransfer medico = medicoFeign.getMedicos(id);
		if (medico == null) {
			System.out.println("nuloooooooooooooooooooooooooooooooooooooooooooooooooooo \n \n"
					+ "\n\n *********************************************");
			System.out.println("\n\n ************************************************************");
		}
		return medico;
	}

	public List<DoctorDataTransfer> fetchAllMedico() {
		List<DoctorDataTransfer> medicos = medicoFeign.getAllMedicos();
		return medicos;
	}

	public PatientDataTransfer fetchPaciente(Long id) {
		PatientDataTransfer paciente = pacienteFeign.getPaciente(id);
		return paciente;
	}

	public DataTransferQuery pegarConsultaPelaId(Long id) {
		@SuppressWarnings("deprecation")
		Consultation consulta = consultaRepository.getById(id);
		DataTransferQuery consultaDados = new DataTransferQuery(consulta, this.fetchMedico(consulta.getMedico()),
				this.fetchPaciente(consulta.getPaciente()));
		return consultaDados;
	}

	public Consultation cadastrar(ConsultationForm dados) {
		Consultation consulta = new Consultation(dados);
		ConsultationDate data = new ConsultationDate(dados.dataConsulta());
		consulta.setData(data);
		DoctorDataTransfer medico = null;
		if (dados.medico() == null)
			medico = this.pegarMedicoAleatorio(this.fetchAllMedico());
		else
			medico = this.fetchMedico(consulta.getMedico());
		consulta.setMedico(medico.id());
		dataRepository.save(data);
		consultaRepository.save(consulta);
		return consulta;
	}

	public DoctorDataTransfer pegarMedicoAleatorio(List<DoctorDataTransfer> medicos) {
		if (medicos.size() == 0)
			return null;
		Random rndm = new Random();
		DoctorDataTransfer rndmElem = medicos.get(rndm.nextInt(medicos.size()));
		return rndmElem;
	}
}
