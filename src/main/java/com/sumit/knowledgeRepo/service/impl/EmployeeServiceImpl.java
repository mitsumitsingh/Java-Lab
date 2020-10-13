package com.sumit.knowledgeRepo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sumit.knowledgeRepo.exception.ResourceNotFoundException;
import com.sumit.knowledgeRepo.model.Department;
import com.sumit.knowledgeRepo.model.Employee;
import com.sumit.knowledgeRepo.model.Roles;
import com.sumit.knowledgeRepo.pojo.EmployeePojo;
import com.sumit.knowledgeRepo.repository.DepartmentRepository;
import com.sumit.knowledgeRepo.repository.EmployeeRepository;
import com.sumit.knowledgeRepo.repository.RolesRepository;
import com.sumit.knowledgeRepo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	DepartmentRepository departmentRepo;
	
	@Autowired
	RolesRepository rolesRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		log.info("Employee data : "+employee);
		
		if(!employee.isPresent()) {
			throw new ResourceNotFoundException("Resource Not Found");
		}
		return employee.get();
	}
	
	@Override
	public Employee saveEmployee(EmployeePojo emp) {
		
		log.info("Employee Pojo : "+emp);
		Employee employee = new Employee(emp);
		employee.setCreatedBy(emp.getUserName());
		employee.setCreatedDate(new Date());
		Department department = departmentRepo.findById((long)emp.getDepartmentId());
		List<Roles> roles = rolesRepo.findByIdIn(emp.getRoles());
		log.info("Employee Role Id's : + "+emp.getRoles());
		employee.setRoles(roles);
		employee.setDepartment(department);
		employee = employeeRepo.save(employee);
		
		return employee;
		
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employeeDetails){
		
		Optional<Employee> employee1 = employeeRepo.findById(employeeId);
		log.info("Employee data : "+employee1);
		
		if(!employee1.isPresent()) {
			throw new ResourceNotFoundException("Resource Not Found");
		}
		
		Employee employee = employee1.get();
		employee.setEmployeeName(employeeDetails.getEmployeeName());
		employee.setEmployeeSalary(employeeDetails.getEmployeeSalary());
		employee.setAddress(employeeDetails.getAddress());
		employee.setMobileNumber(employeeDetails.getMobileNumber());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setUpdatedDate(new Date());
		employee.setUpdatedBy(employeeDetails.getUpdatedBy());
		
		final Employee updatedEmployee = employeeRepo.save(employee);
		log.info("updated employee : "+updatedEmployee);
		return updatedEmployee;
	}

	@Override
	public Employee deleteEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		if(!employee.isPresent()) {
			log.info("Employee not present with the employee id : "+employeeId);
			throw new ResourceNotFoundException("Resource Not Found");
		}else {
			employeeRepo.delete(employee.get());
			return employee.get();
		}
	}
	
	@Override
	public List<Employee> getAllEmployeesByPaging(int pageNumber, int pageSize) {
        log.info("Page Number : "+pageNumber +" Page Size :  "+pageSize);
//        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("emailId"));

        log.info("Paging : " + paging);
        Page<Employee> pageResult = employeeRepo.findAll(paging);

        return pageResult.toList();
	}

}
