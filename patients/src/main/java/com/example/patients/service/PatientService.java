package com.example.patients.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.patients.datatransfer.PatientDataList;
import com.example.patients.datatransfer.PatientData;
import com.example.patients.datatransfer.ConsultationData;
import com.example.patients.datatransfer.PatientForm;
import com.example.patients.datatransfer.UpdatePatientForm;
import com.example.patients.models.Address;
import com.example.patients.models.Patients;
import com.example.patients.repositories.AdressRepo;
import com.example.patients.repositories.PatientRepo;

@Service
public class PatientService {

	@Autowired
	private PatientRepo pacienteRepository;

	@Autowired
	private AdressRepo AdressRepo;

	public List<PatientData> converterDadosPacientes(List<Patients> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isApagado() == false).map(PatientData::new).collect(Collectors.toList());
	}

	public List<PatientDataList> converterOrdenado(List<Patients> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(PatientDataList::new).collect(Collectors.toList());
	}

	public List<PatientData> buscarTodos() {
		return this.converterDadosPacientes(this.pacienteRepository.findAll());
	}

	public List<PatientDataList> buscarOrdenado() {
		return this.converterOrdenado(this.pacienteRepository.findAll());
	}

	@SuppressWarnings("deprecation")
	public ConsultationData getPelaId(Long id) {
		java.util.Optional<Patients> op = pacienteRepository.findById(id);
		if (op.isPresent() && op.get().isApagado() == false) {
			return new ConsultationData(this.pacienteRepository.getById(id));
		} else
			return null;
	}

	public Patients cadastrar(PatientForm dados) {
		Patients paciente = new Patients(dados);
		Address endereco = new Address(dados.endereco());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		paciente.setEndereco(endereco);
		AdressRepo.save(endereco);
		pacienteRepository.save(paciente);

		return paciente;
	}

	public Patients atualizar(UpdatePatientForm dados, Long id) {
		@SuppressWarnings("deprecation")
		Patients paciente = pacienteRepository.getById(id);
		Address endereco = new Address(dados.endereco());

		paciente.setNome(dados.nome());
		paciente.setTelefone(dados.telefone());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		paciente.setEndereco(endereco);
		AdressRepo.save(endereco);
		pacienteRepository.save(paciente);
		return paciente;
	}

	public Patients deletar(Long id) {
		@SuppressWarnings("deprecation")
		Patients paciente = pacienteRepository.getById(id);
		@SuppressWarnings("deprecation")
		Address endereco = AdressRepo.getById(id);
		paciente.setApagado(true);
		endereco.setApagado(true);
		AdressRepo.save(endereco);
		pacienteRepository.save(paciente);
		return paciente;
	}

}
