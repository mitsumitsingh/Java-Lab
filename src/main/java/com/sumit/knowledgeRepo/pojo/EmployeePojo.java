package com.sumit.knowledgeRepo.pojo;

import java.math.BigInteger;
import java.util.List;

import com.sumit.knowledgeRepo.model.Address;

public class EmployeePojo {

	private Long id;
	private String employeeName;	
	private String employeeSalary;
	private Address address;
	private int departmentId;
	private List<Long> roles;	
	private BigInteger mobileNumber;	
	private String emailId;
	private String userName;
	
	public EmployeePojo() {
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
	public String getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public List<Long> getRoles() {
		return roles;
	}
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
	public BigInteger getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(BigInteger mobileNumber) {
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
		return "EmployeePojo [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary
				+ ", address=" + address + ", departmentId=" + departmentId + ", roles=" + roles + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", userName=" + userName + "]";
	}
	
}
