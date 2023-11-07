package com.example.doctor.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.doctor.datatransfer.DoctorData;
import com.example.doctor.datatransfer.ConsultData;
import com.example.doctor.datatransfer.DoctorListData;
import com.example.doctor.datatransfer.DoctorForm;
import com.example.doctor.datatransfer.DoctorFormUpdate;
import com.example.doctor.models.Doctor;
import com.example.doctor.models.Address;
import com.example.doctor.repositories.AddressRepo;
import com.example.doctor.repositories.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepo doctorRepository;

	@Autowired
	private AddressRepo enderecoRepository;

	public List<DoctorData> converterDadosMedicos(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isApagado() == false).map(DoctorData::new).collect(Collectors.toList());
	}

	public List<DoctorListData> converterOrdenado(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isApagado() == false)
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome())).map(DoctorListData::new)
				.collect(Collectors.toList());
	}

	public List<ConsultData> converterParaConsulta(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isApagado() == false)
				.sorted((object1, object2) -> object1.getNome().compareTo(object2.getNome())).map(ConsultData::new)
				.collect(Collectors.toList());
	}

	public List<DoctorData> buscarTodos() {
		return this.converterDadosMedicos(this.doctorRepository.findAll());
	}

	public List<DoctorListData> buscarOrdenado() {
		return this.converterOrdenado(this.doctorRepository.findAll());
	}

	public List<ConsultData> buscarTodosParaConsulta() {
		return this.converterParaConsulta(this.doctorRepository.findAll());
	}

	@SuppressWarnings("deprecation")
	public ConsultData getPelaId(Long id) {

		java.util.Optional<Doctor> op = doctorRepository.findById(id);
		if (op.isPresent() && op.get().isApagado() == false) {
			return new ConsultData(this.doctorRepository.getById(id));
		} else
			return null;
	}

	public Doctor cadastrar(DoctorForm dados) {
		Doctor medico = new Doctor(dados);
		Address endereco = new Address(dados.endereco());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		medico.setEndereco(endereco);
		enderecoRepository.save(endereco);
		doctorRepository.save(medico);
		return medico;
	}

	public Doctor atualizar(DoctorFormUpdate dados, Long id) {
		@SuppressWarnings("deprecation")
		Doctor medico = doctorRepository.getById(id);
		Address endereco = new Address(dados.endereco());

		medico.setNome(dados.nome());
		medico.setTelefone(dados.telefone());
		endereco.setComplemento(dados.endereco().complemento());
		endereco.setNumero(dados.endereco().numero());
		medico.setEndereco(endereco);
		enderecoRepository.save(endereco);
		doctorRepository.save(medico);
		return medico;
	}

	public Doctor deletar(Long id) {
		@SuppressWarnings("deprecation")
		Doctor medico = doctorRepository.getById(id);
		@SuppressWarnings("deprecation")
		Address endereco = enderecoRepository.getById(id);
		medico.setApagado(true);
		endereco.setApagado(true);
		enderecoRepository.save(endereco);
		doctorRepository.save(medico);
		return medico;
	}

}
