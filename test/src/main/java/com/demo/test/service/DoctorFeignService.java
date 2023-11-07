package com.demo.test.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.test.datatransfer.DoctorDataTransfer;

@FeignClient(value = "medico", url = "http://10.0.0.5:8082/medico-ms/medicos/consulta")
@Service
public interface DoctorFeignService {
	@RequestMapping(method = RequestMethod.GET, value = "")
	List<DoctorDataTransfer> getAllMedicos();

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	DoctorDataTransfer getMedicos(@PathVariable Long id);
}
