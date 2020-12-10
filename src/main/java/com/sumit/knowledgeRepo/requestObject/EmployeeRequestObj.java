package com.sumit.knowledgeRepo.requestObject;

import java.util.List;

import com.sumit.knowledgeRepo.model.Address;

public class EmployeeRequestObj {

	private Long id;
	private String employeeName;	
	private Double employeeSalary;
	private Address address;
	private Integer departmentId;
	private List<Long> roles;	
	private Long mobileNumber;	
	private String emailId;
	private String userName;
	
	public EmployeeRequestObj() {
		super();
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
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public List<Long> getRoles() {
		return roles;
	}
	public void setRoles(List<Long> roles) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "EmployeeRequestObject [id=" + id + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + ", address=" + address + ", departmentId=" + departmentId + ", roles=" + roles
				+ ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", userName=" + userName + "]";
	}
	
}
