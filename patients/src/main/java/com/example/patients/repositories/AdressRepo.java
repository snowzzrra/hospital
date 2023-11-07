package com.example.patients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patients.models.Address;

public interface AdressRepo extends JpaRepository<Address, Long> {

}
