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
	private AddressRepo addressRepository;

	public List<DoctorData> doctorDataConversion(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isErased() == false).map(DoctorData::new).collect(Collectors.toList());
	}

	public List<DoctorListData> convertSorted(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isErased() == false)
				.sorted((object1, object2) -> object1.getName().compareTo(object2.getName())).map(DoctorListData::new)
				.collect(Collectors.toList());
	}

	public List<ConsultData> convertToConsult(List<Doctor> lista) {
		if (lista.isEmpty())
			return null;
		return lista.stream().filter(c -> c.isErased() == false)
				.sorted((object1, object2) -> object1.getName().compareTo(object2.getName())).map(ConsultData::new)
				.collect(Collectors.toList());
	}

	public List<DoctorData> searchAll() {
		return this.doctorDataConversion(this.doctorRepository.findAll());
	}

	public List<DoctorListData> searchSorted() {
		return this.convertSorted(this.doctorRepository.findAll());
	}

	public List<ConsultData> searchAllConsult() {
		return this.convertToConsult(this.doctorRepository.findAll());
	}

	@SuppressWarnings("deprecation")
	public ConsultData getById(Long id) {

		java.util.Optional<Doctor> op = doctorRepository.findById(id);
		if (op.isPresent() && op.get().isErased() == false) {
			return new ConsultData(this.doctorRepository.getById(id));
		} else
			return null;
	}

	public Doctor post(DoctorForm data) {
		Doctor doctor = new Doctor(data);
		Address address = new Address(data.endereco());
		address.setComplemento(data.endereco().complemento());
		address.setNumero(data.endereco().numero());
		doctor.setAddress(address);
		addressRepository.save(address);
		doctorRepository.save(doctor);
		return doctor;
	}

	public Doctor update(DoctorFormUpdate dados, Long id) {
		@SuppressWarnings("deprecation")
		Doctor doctor = doctorRepository.getById(id);
		Address address = new Address(dados.endereco());

		doctor.setName(dados.nome());
		doctor.setPhoneNumber(dados.telefone());
		address.setComplemento(dados.endereco().complemento());
		address.setNumero(dados.endereco().numero());
		doctor.setAddress(address);
		addressRepository.save(address);
		doctorRepository.save(doctor);
		return doctor;
	}

	public Doctor delete(Long id) {
		@SuppressWarnings("deprecation")
		Doctor doctor = doctorRepository.getById(id);
		@SuppressWarnings("deprecation")
		Address address = addressRepository.getById(id);
		doctor.setErased(true);
		address.setApagado(true);
		addressRepository.save(address);
		doctorRepository.save(doctor);
		return doctor;
	}

}
