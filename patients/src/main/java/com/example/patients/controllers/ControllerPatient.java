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
	public List<PatientData> listarTodos() {
		return pacienteService.buscarTodos();
	}

	@GetMapping("/{id}")
	public ConsultationData getPelaId(@PathVariable Long id) {
		return pacienteService.getPelaId(id);
	}

	@GetMapping("/ordenado")
	public List<PatientDataList> listarTodosOrdenado() {
		return pacienteService.buscarOrdenado();
	}

	@PostMapping
	public ResponseEntity<PatientData> cadastrar(@RequestBody PatientForm dados) {
		Patients paciente;
		paciente = pacienteService.cadastrar(dados);
		return new ResponseEntity<PatientData>(new PatientData(paciente), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientData> atualizar(@RequestBody UpdatePatientForm dados, @PathVariable Long id) {
		Patients paciente;
		paciente = pacienteService.atualizar(dados, id);
		return new ResponseEntity<PatientData>(new PatientData(paciente), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		@SuppressWarnings("unused")
		Patients paciente;
		paciente = pacienteService.deletar(id);
		return "Deletado com Sucesso";
	}
}
