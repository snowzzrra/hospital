package com.example.doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doctor.models.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
