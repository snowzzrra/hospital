package com.demo.test.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.test.datatransfer.DataTransferQuery;
import com.demo.test.datatransfer.ConsultationForm;
import com.demo.test.model.Consultation;
import com.demo.test.service.ConsultationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
@Component
public class ControllerClinic {

	@Autowired
	private ConsultationService<?> consultaService;

	@GetMapping
	public List<DataTransferQuery> listAll() {
		return consultaService.searchAll();

	}

	@GetMapping("/{id}")
	public DataTransferQuery listAllById(@PathVariable Long id) {
		return consultaService.getConsultById(id);

	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid ConsultationForm data)
			throws MethodArgumentNotValidException, HttpMessageNotReadableException {
		Consultation consult;
		consult = consultaService.post(data);
		DataTransferQuery postedConsult = new DataTransferQuery(consult,
				consultaService.fetchDoctor(consult.getDoctor()),
				consultaService.fetchPatient(consult.getPatient()));
		return new ResponseEntity<>(postedConsult, HttpStatus.CREATED);
	}
}
