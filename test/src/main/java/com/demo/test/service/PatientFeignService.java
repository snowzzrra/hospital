package com.demo.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.test.datatransfer.PatientDataTransfer;

@FeignClient(value = "paciente", url = "http://10.0.0.5:8082/paciente-ms/pacientes")
@Service
public interface PatientFeignService {

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	PatientDataTransfer getPatient(@PathVariable Long id);

}
