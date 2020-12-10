package com.sumit.knowledgeRepo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.knowledgeRepo.pojo.EmployeePojo;
import com.sumit.knowledgeRepo.requestObject.EmployeeRequestObj;
import com.sumit.knowledgeRepo.service.EmployeeService;

@RestController
public class EmployeeController extends RestCtrl{

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeePojo>> getAllEmployees() {
		List<EmployeePojo> employeeList = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeePojo>>(employeeList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeePojo> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		EmployeePojo employeeList = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employeeList, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<EmployeePojo> saveEmployee(@RequestBody EmployeeRequestObj pojo) {
		EmployeePojo employee = employeeService.saveEmployee(pojo);
		return new ResponseEntity<EmployeePojo>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<EmployeePojo> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody EmployeeRequestObj employeeDetails) {
		EmployeePojo employee = employeeService.updateEmployee(employeeId, employeeDetails);
		return new ResponseEntity<EmployeePojo>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<EmployeePojo> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		EmployeePojo employee = employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<EmployeePojo>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/employeeByPage")
	public ResponseEntity<List<EmployeePojo>> getAllEmployeesByPaging(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			List<EmployeePojo> employeeList = employeeService.getAllEmployeesByPaging(pageNumber, pageSize);
			return new ResponseEntity<List<EmployeePojo>>(employeeList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<EmployeePojo>>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/downloadEmployeesXlsx")
	public ResponseEntity<String> downloadEmployeesXlsx(HttpServletResponse response) throws Exception {

		employeeService.downloadEmployeesXlsx(response);	
		return new ResponseEntity<String>("Successfully Downloaded", new HttpHeaders(), HttpStatus.OK);
		
	}
	
	@PostMapping(value="/uploadEmployeesXlsx")
	public ResponseEntity<String> uploadEmployeesXlsx(@RequestParam("file") MultipartFile file) throws Exception {

		List<EmployeePojo> response = employeeService.uploadEmployeesXlsx(file);
		
		return new ResponseEntity<String>("Successfully uploaded."+response, new HttpHeaders(), HttpStatus.OK);
		
	}
	
}
