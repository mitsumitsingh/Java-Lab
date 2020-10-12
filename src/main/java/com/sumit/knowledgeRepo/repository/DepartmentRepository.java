package com.sumit.knowledgeRepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.knowledgeRepo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findById(long departmentId);

}
