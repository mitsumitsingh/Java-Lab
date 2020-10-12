package com.sumit.knowledgeRepo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.knowledgeRepo.model.Employee;
import com.sumit.knowledgeRepo.pojo.EmployeePojo;
import com.sumit.knowledgeRepo.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders = "true", allowedHeaders = "*")
@RequestMapping("/rest")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody EmployeePojo employee){
		return employeeService.saveEmployee(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,@RequestBody Employee employeeDetails){
		return employeeService.updateEmployee(employeeId, employeeDetails);
	}
}
