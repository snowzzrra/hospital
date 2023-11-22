package com.example.patients.controllers;

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
import com.example.patients.datatransfer.PatientData;
import com.example.patients.datatransfer.ConsultationData;
import com.example.patients.datatransfer.PatientDataList;
import com.example.patients.datatransfer.PatientForm;
import com.example.patients.datatransfer.UpdatePatientForm;
import com.example.patients.models.Patients;
import com.example.patients.service.PatientService;

@RestController
@RequestMapping("/pacientes")
public class ControllerPatient {

	@Autowired
	private PatientService pacienteService;

	@GetMapping
	public List<PatientData> listAll() {
		return pacienteService.searchAll();
	}

	@GetMapping("/{id}")
	public ConsultationData getById(@PathVariable Long id) {
		return pacienteService.getById(id);
	}

	@GetMapping("/ordenado")
	public List<PatientDataList> listAllSorted() {
		return pacienteService.searchSorted();
	}

	@PostMapping
	public ResponseEntity<PatientData> post(@RequestBody PatientForm dados) {
		Patients patient;
		patient = pacienteService.post(dados);
		return new ResponseEntity<PatientData>(new PatientData(patient), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientData> update(@RequestBody UpdatePatientForm dados, @PathVariable Long id) {
		Patients patient;
		patient = pacienteService.update(dados, id);
		return new ResponseEntity<PatientData>(new PatientData(patient), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		@SuppressWarnings("unused")
		Patients patient;
		patient = pacienteService.delete(id);
		return "Deletado com Sucesso";
	}
}
