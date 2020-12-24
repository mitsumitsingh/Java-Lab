package com.sumit.knowledgeRepo.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumit.knowledgeRepo.config.ActiveMQConfig;
import com.sumit.knowledgeRepo.exception.KnowledgeRepoException;
import com.sumit.knowledgeRepo.exception.ResourceNotFoundException;
import com.sumit.knowledgeRepo.model.Department;
import com.sumit.knowledgeRepo.model.Employee;
import com.sumit.knowledgeRepo.model.Roles;
import com.sumit.knowledgeRepo.pojo.EmployeePojo;
import com.sumit.knowledgeRepo.repository.DepartmentRepository;
import com.sumit.knowledgeRepo.repository.EmployeeRepository;
import com.sumit.knowledgeRepo.repository.RolesRepository;
import com.sumit.knowledgeRepo.requestObject.EmployeeRequestObj;
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

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public List<EmployeePojo> getAllEmployees() {
		List<EmployeePojo> employeesPojo = new LinkedList<EmployeePojo>();
		List<Employee> employees = employeeRepo.findAll();
		employees.parallelStream().forEach(employee -> {
			employeesPojo.add(new EmployeePojo(employee));
		});
		return employeesPojo;
	}

	@Override
	public EmployeePojo getEmployeeById(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		log.info("Employee data : " + employee);

		if (!employee.isPresent()) {
			throw new ResourceNotFoundException("Resource Not Found");
		}
		jmsTemplate.convertAndSend(ActiveMQConfig.SENT_EMAIL_QUEUE, employee.toString());
		EmployeePojo employeePojo = new EmployeePojo(employee.get());
		return employeePojo;
	}

	@Override
	public EmployeePojo saveEmployee(EmployeeRequestObj emp) {

		log.info("Employee Pojo : " + emp);
		Employee employee = new Employee(emp);
		employee.setCreatedBy(emp.getUserName());
		employee.setCreatedDate(new Date());
		Department department = departmentRepo.findById((long) emp.getDepartmentId());
		List<Roles> roles = rolesRepo.findByIdIn(emp.getRoles());
		log.info("Employee Role Id's : + " + emp.getRoles());
		employee.setRoles(roles);
		employee.setDepartment(department);
		employee = employeeRepo.save(employee);

		EmployeePojo employeePojo = new EmployeePojo(employee);
		return employeePojo;

	}

	@Override
	public EmployeePojo updateEmployee(Long employeeId, EmployeeRequestObj employeeDetails) {

		Optional<Employee> employee1 = employeeRepo.findById(employeeId);
		log.info("Employee data : " + employee1);

		if (!employee1.isPresent()) {
			throw new ResourceNotFoundException("Resource Not Found");
		}

		Employee employee = employee1.get();
		if (employeeDetails.getEmployeeName() != null) {
			employee.setEmployeeName(employeeDetails.getEmployeeName());
		}
		if (employeeDetails.getEmployeeSalary() != null) {
			employee.setEmployeeSalary(employeeDetails.getEmployeeSalary());
		}
		if (employeeDetails.getAddress() != null) {
			employee.setAddress(employeeDetails.getAddress());
		}
		if (employeeDetails.getMobileNumber() != null) {
			employee.setMobileNumber(employeeDetails.getMobileNumber());
		}
		if (employeeDetails.getEmailId() != null) {
			employee.setEmailId(employeeDetails.getEmailId());
		}
		if (employeeDetails.getDepartmentId() != null) {
			Department department = departmentRepo.findById(employeeDetails.getDepartmentId());
			employee.setDepartment(department);
		}
		employee.setUpdatedDate(new Date());
		if (employeeDetails.getUserName() != null) {
			employee.setUpdatedBy(employeeDetails.getUserName());
		}

		final Employee updatedEmployee = employeeRepo.save(employee);

		EmployeePojo employeePojo = new EmployeePojo(updatedEmployee);
		log.info("updated employee : " + updatedEmployee);
		return employeePojo;
	}

	@Override
	public EmployeePojo deleteEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		if (!employee.isPresent()) {
			log.info("Employee not present with the employee id : " + employeeId);
			throw new ResourceNotFoundException("Resource Not Found");
		} else {
			employeeRepo.delete(employee.get());
			return new EmployeePojo(employee.get());
		}
	}

	@Override
	public List<EmployeePojo> getAllEmployeesByPaging(int pageNumber, int pageSize) {
		log.info("Page Number : " + pageNumber + " Page Size :  " + pageSize);
		Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
		// Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("emailId"));

		log.info("Paging : " + paging);
		Page<Employee> pageResult = employeeRepo.findAll(paging);

		List<EmployeePojo> employeePojo = new LinkedList<EmployeePojo>();
		pageResult.toList().stream().forEach(employee -> {
			employeePojo.add(new EmployeePojo(employee));
		});

		return employeePojo;
	}

	@Override
	public void downloadEmployeesXlsx(HttpServletResponse response) throws IOException {

		List<Employee> employeeList = employeeRepo.findAll();
		// Creating Workbook
		Workbook workbook = new XSSFWorkbook();
		// Create Sheet
		Sheet sheet = workbook.createSheet("employees");

		String[] columnHeadings = { "employeeId", "employeeName", "departmentId", "departmentName", "Email Id",
				"Salary", "Mobile Number" };
		// Make Header bold with a foreground color
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setColor(IndexedColors.BLACK.index);

		// Create a shell style with the font
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		// Create header row
		Row headerRow = sheet.createRow(0);

		// Iterate over the column headings to create columns
		for (int i = 0; i < columnHeadings.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnHeadings[i]);
			cell.setCellStyle(headerStyle);
		}

		// Freez Header Row
		sheet.createFreezePane(0, 1);

		// Fill data
		int rowNum = 1;
		for (Employee employee : employeeList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(employee.getId());
			row.createCell(1).setCellValue(employee.getEmployeeName());
			row.createCell(2).setCellValue(employee.getDepartment().getId());
			row.createCell(3).setCellValue(employee.getDepartment().getDepartmentName());
			row.createCell(4).setCellValue(employee.getEmailId());
			row.createCell(5).setCellValue(employee.getEmployeeSalary());
			row.createCell(6).setCellValue(employee.getMobileNumber().toString());
		}

		// Autosize columns
		for (int i = 0; i < columnHeadings.length; i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workbook.write(bos);
		byte[] bytes = bos.toByteArray();

		response.setContentType("application/ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		bos.close();

		workbook.close();
	}

	@Override
	public List<EmployeePojo> uploadEmployeesXlsx(MultipartFile file) throws IOException {
		List<Employee> employeeList = new LinkedList<Employee>();

		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Map<String, String> exception = new HashMap<String, String>();
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		boolean isValidExcel = true;
		int rowNumber = 1;
		while (rows.hasNext()) {
			boolean isError = false;

			Row row = rows.next();

			Double employeeSalary;
			Integer departmentId;
			long mobileNumber, roleId;
			String employeeName, emailId;
			Employee pojo = new Employee();
			Cell cell = row.getCell(0);

			if (cell == null)
				break;

			// Employee Name Validations.
			if (row.getCell(0).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Employee Name at Row Number : " + rowNumber, "Employee Name should not be Empty.");
			}
			if (row.getCell(0).getCellType() == CellType.STRING) {
				employeeName = row.getCell(0).getStringCellValue();
				pojo.setEmployeeName(employeeName);
			} else {
				isError = true;
				exception.put("Employee Name at Row Number : " + rowNumber, "Employee Name should have String Value");
			}

			// Employee Salary Validation.
			if (row.getCell(1).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Employee Salary at Row Number : " + rowNumber, "Employee Salary should not be Empty.");
			}
			if (row.getCell(1).getCellType() == CellType.NUMERIC) {
				employeeSalary = row.getCell(1).getNumericCellValue();
				pojo.setEmployeeSalary(employeeSalary);
			} else {
				isError = true;
				exception.put("Employee Salary at Row Number : " + rowNumber,
						"Employee Salary should have Double Value");
			}

			// Department Id Validation
			if (row.getCell(2).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Department Id at Row Number : " + rowNumber, "Department Id should not be Empty.");
			}
			if (row.getCell(2).getCellType() == CellType.NUMERIC) {
				departmentId = (int) row.getCell(2).getNumericCellValue();
				Department department = departmentRepo.findById(departmentId);
				pojo.setDepartment(department);
			} else {
				isError = true;
				exception.put("Department Id at Row Number : " + rowNumber, "Department Id should have Integer Value");
			}

			// Mobile Number Validation
			if (row.getCell(3).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Mobile Number at Row Number : " + rowNumber, "Mobile Number should not be Empty.");
			}
			if (row.getCell(3).getCellType() == CellType.NUMERIC) {
				mobileNumber = (long) row.getCell(3).getNumericCellValue();
				pojo.setMobileNumber(mobileNumber);
			} else {
				isError = true;
				exception.put("Mobile Number at Row Number : " + rowNumber, "Mobile Number should have Long Value");
			}

			// Email Id Validation
			if (row.getCell(4).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Email Id at Row Number : " + rowNumber, "Email Id should not be Empty.");
			}
			if (row.getCell(4).getCellType() == CellType.STRING) {
				emailId = row.getCell(4).getStringCellValue();
				pojo.setEmailId(emailId);
			} else {
				isError = true;
				exception.put("Email Id at Row Number : " + rowNumber, "Email Id should have String Value");
			}

			// Role Id Validation
			if (row.getCell(5).getCellType() == CellType.BLANK) {
				isError = true;
				exception.put("Role Id at Row Number : " + rowNumber, "Role Id should not be Empty.");
			}
			if (row.getCell(5).getCellType() == CellType.NUMERIC) {
				List<Long> roleIdList = new LinkedList<Long>();
				roleId = (long) row.getCell(5).getNumericCellValue();
				roleIdList.add(roleId);
				List<Roles> roles = rolesRepo.findByIdIn(roleIdList);
				pojo.setRoles(roles);
			} else {
				isError = true;
				exception.put("Role Id at Row Number : " + rowNumber, "Role Id should have Integer Value");
			}
			pojo.setCreatedBy("Excel Upload");
			pojo.setCreatedDate(new Date());

			rowNumber++;

			if (!isError) {
				employeeList.add(pojo);
			} else {
				isValidExcel = false;
			}
		}
		if (!isValidExcel) {
			log.error("Excel is not correct : " + exception.toString());
			throw new KnowledgeRepoException(exception.toString());
		}

		employeeList = employeeRepo.saveAll(employeeList);
		List<EmployeePojo> employeePojo = new LinkedList<EmployeePojo>();
		for (Employee employee : employeeList) {
			employeePojo.add(new EmployeePojo(employee));
		}

		return employeePojo;
	}

}
