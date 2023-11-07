package com.demo.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.demo.test.model.ConsultationDate;

@Service
public interface ConsultationDateRepo extends JpaRepository<ConsultationDate, Long> {

}
