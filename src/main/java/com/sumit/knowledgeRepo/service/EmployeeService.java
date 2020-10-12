package com.sumit.knowledgeRepo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sumit.knowledgeRepo.model.Employee;
import com.sumit.knowledgeRepo.pojo.EmployeePojo;

@Service
public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee saveEmployee(EmployeePojo employee);

	Employee updateEmployee(Long employeeId, Employee employeeDetails);

}
