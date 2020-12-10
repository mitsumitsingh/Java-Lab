package com.sumit.knowledgeRepo.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.knowledgeRepo.pojo.EmployeePojo;
import com.sumit.knowledgeRepo.requestObject.EmployeeRequestObj;

@Service
public interface EmployeeService {

	List<EmployeePojo> getAllEmployees();

	EmployeePojo getEmployeeById(Long employeeId);

	EmployeePojo saveEmployee(EmployeeRequestObj employee);

	EmployeePojo updateEmployee(Long employeeId, EmployeeRequestObj employeeDetails);

	EmployeePojo deleteEmployee(Long employeeId);

	List<EmployeePojo> getAllEmployeesByPaging(int pageNumber, int pageSize);

	void downloadEmployeesXlsx(HttpServletResponse response) throws IOException;

	List<EmployeePojo> uploadEmployeesXlsx(MultipartFile file) throws IOException;

}
