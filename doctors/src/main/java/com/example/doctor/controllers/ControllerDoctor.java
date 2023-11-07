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
	public String listarTodos() {
		return doctorService.buscarTodos().toString();
	}

	@GetMapping("consulta/{id}")
	public ConsultData getPelaId(@PathVariable Long id) {
		return doctorService.getPelaId(id);
	}

	@GetMapping("consulta")
	public List<ConsultData> getAllParaConsultas() {
		return doctorService.buscarTodosParaConsulta();
	}

	@GetMapping("/ordenado")
	public List<DoctorListData> listarTodosOrdenado() {
		return doctorService.buscarOrdenado();
	}

	@PostMapping
	public ResponseEntity<DoctorData> cadastrar(@RequestBody DoctorForm dados) {
		Doctor medico;
		medico = doctorService.cadastrar(dados);
		return new ResponseEntity<DoctorData>(new DoctorData(medico), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorData> atualizar(@RequestBody DoctorFormUpdate dados, @PathVariable Long id) {
		Doctor medico;
		medico = doctorService.atualizar(dados, id);
		return new ResponseEntity<DoctorData>(new DoctorData(medico), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		@SuppressWarnings("unused")
		Doctor medico;
		medico = doctorService.deletar(id);
		return "Deletado";
	}
}
