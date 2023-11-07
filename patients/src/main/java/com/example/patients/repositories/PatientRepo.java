package com.example.patients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patients.models.Patients;

public interface PatientRepo extends JpaRepository<Patients, Long> {

}
