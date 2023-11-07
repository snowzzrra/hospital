package com.demo.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.demo.test.model.Consultation;

@Service
public interface ConsultationRepo extends JpaRepository<Consultation, Long> {

}
