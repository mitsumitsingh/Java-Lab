package com.sumit.knowledgeRepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.knowledgeRepo.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
