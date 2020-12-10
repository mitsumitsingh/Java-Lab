package com.sumit.knowledgeRepo.pojo;

import java.util.Date;
import java.util.List;

import com.sumit.knowledgeRepo.model.Address;
import com.sumit.knowledgeRepo.model.Department;
import com.sumit.knowledgeRepo.model.Employee;
import com.sumit.knowledgeRepo.model.Roles;

public class EmployeePojo {

	private Long id;
	private String employeeName;	
	private Double employeeSalary;
	private Address address;
	private Department department;
	private List<Roles> roles;	
	private Long mobileNumber;	
	private String emailId;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;
	
	public EmployeePojo() {
		super();
	}

	
	public EmployeePojo(Employee employee) {
		super();
		this.id = employee.getId();
		this.employeeName = employee.getEmployeeName();
		this.employeeSalary = employee.getEmployeeSalary();
		this.address = employee.getAddress();
		this.department = employee.getDepartment();
		this.roles = employee.getRoles();
		this.mobileNumber = employee.getMobileNumber();
		this.emailId = employee.getEmailId();
		this.createdBy = employee.getCreatedBy();
		this.updatedBy = employee.getUpdatedBy();
		this.createdDate = employee.getCreatedDate();
		this.updatedDate = employee.getUpdatedDate();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "EmployeePojo [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary
				+ ", address=" + address + ", department=" + department + ", roles=" + roles + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
