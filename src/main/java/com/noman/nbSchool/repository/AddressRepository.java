package com.noman.nbSchool.repository;

import com.noman.nbSchool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
