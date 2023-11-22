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

	public List<PatientData> convertDataPatients(List<Patients> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isDeleted() == false).map(PatientData::new).collect(Collectors.toList());
	}

	public List<PatientDataList> convertSorted(List<Patients> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome()))
				.map(PatientDataList::new).collect(Collectors.toList());
	}

	public List<PatientData> searchAll() {
		return this.convertDataPatients(this.pacienteRepository.findAll());
	}

	public List<PatientDataList> searchSorted() {
		return this.convertSorted(this.pacienteRepository.findAll());
	}

	@SuppressWarnings("deprecation")
	public ConsultationData getById(Long id) {
		java.util.Optional<Patients> op = pacienteRepository.findById(id);
		if (op.isPresent() && op.get().isDeleted() == false) {
			return new ConsultationData(this.pacienteRepository.getById(id));
		} else
			return null;
	}

	public Patients post(PatientForm dados) {
		Patients patient = new Patients(dados);
		Address address = new Address(dados.endereco());
		address.setComplemento(dados.endereco().complemento());
		address.setNumero(dados.endereco().numero());
		patient.setEndereco(address);
		AdressRepo.save(address);
		pacienteRepository.save(patient);

		return patient;
	}

	public Patients update(UpdatePatientForm dados, Long id) {
		@SuppressWarnings("deprecation")
		Patients patient = pacienteRepository.getById(id);
		Address address = new Address(dados.endereco());

		patient.setNome(dados.nome());
		patient.setTelefone(dados.telefone());
		address.setComplemento(dados.endereco().complemento());
		address.setNumero(dados.endereco().numero());
		patient.setEndereco(address);
		AdressRepo.save(address);
		pacienteRepository.save(patient);
		return patient;
	}

	public Patients delete(Long id) {
		@SuppressWarnings("deprecation")
		Patients patient = pacienteRepository.getById(id);
		@SuppressWarnings("deprecation")
		Address address = AdressRepo.getById(id);
		patient.setDeleted(true);
		address.setApagado(true);
		AdressRepo.save(address);
		pacienteRepository.save(patient);
		return patient;
	}

}
