package com.sumit.knowledgeRepo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employeeList = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employeeList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Employee employeeList = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<Employee>(employeeList, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeePojo pojo) {
		Employee employee = employeeService.saveEmployee(pojo);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) {
		Employee employee = employeeService.updateEmployee(employeeId, employeeDetails);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/employeeByPage")
	public ResponseEntity<List<Employee>> getAllEmployeesByPaging(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			List<Employee> employeeList = employeeService.getAllEmployeesByPaging(pageNumber, pageSize);
			return new ResponseEntity<List<Employee>>(employeeList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Employee>>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
