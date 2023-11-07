package com.example.doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.doctor.models.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
