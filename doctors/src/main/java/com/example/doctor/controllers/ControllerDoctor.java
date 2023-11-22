package com.example.doctor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doctor.datatransfer.DoctorData;
import com.example.doctor.datatransfer.ConsultData;
import com.example.doctor.datatransfer.DoctorListData;
import com.example.doctor.datatransfer.DoctorForm;
import com.example.doctor.datatransfer.DoctorFormUpdate;
import com.example.doctor.models.Doctor;
import com.example.doctor.service.DoctorService;

@RestController
@RequestMapping("/medicos")
public class ControllerDoctor {

	@Autowired
	private DoctorService doctorService;

	@GetMapping
	public String listAll() {
		return doctorService.searchAll().toString();
	}

	@GetMapping("consulta/{id}")
	public ConsultData getById(@PathVariable Long id) {
		return doctorService.getById(id);
	}

	@GetMapping("consulta")
	public List<ConsultData> getAllConsults() {
		return doctorService.searchAllConsult();
	}

	@GetMapping("/ordenado")
	public List<DoctorListData> listAllSorted() {
		return doctorService.searchSorted();
	}

	@PostMapping
	public ResponseEntity<DoctorData> post(@RequestBody DoctorForm dados) {
		Doctor doctor;
		doctor = doctorService.post(dados);
		return new ResponseEntity<DoctorData>(new DoctorData(doctor), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorData> update(@RequestBody DoctorFormUpdate dados, @PathVariable Long id) {
		Doctor doctor;
		doctor = doctorService.update(dados, id);
		return new ResponseEntity<DoctorData>(new DoctorData(doctor), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		@SuppressWarnings("unused")
		Doctor doctor;
		doctor = doctorService.delete(id);
		return "Deletado";
	}
}
