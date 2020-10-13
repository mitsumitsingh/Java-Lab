package com.sumit.knowledgeRepo.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sumit.knowledgeRepo.pojo.EmployeePojo;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String employeeName;
	
	@Column(name="salary")
	private String employeeSalary;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department department;
	
    @ManyToMany(targetEntity=Roles.class, fetch = FetchType.EAGER)  
	@JoinColumn(name="roles")
	private List<Roles> roles;
	
	@Column(name="mobile_number")
	private BigInteger mobileNumber;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp()
	private Date createdDate;
	
	@Column
	@UpdateTimestamp()
	private Date updatedDate;

	public Employee() {
		super();
	}

	public Employee(Long id, String employeeName, String employeeSalary, Address address, Department department,
			List<Roles> roles, BigInteger mobileNumber, String emailId, String createdBy, String updatedBy,
			Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.address = address;
		this.department = department;
		this.roles = roles;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	
	public Employee(EmployeePojo employeePojo) {
		super();
		this.employeeName = employeePojo.getEmployeeName();
		this.employeeSalary = employeePojo.getEmployeeSalary();
		this.address = employeePojo.getAddress();
		this.mobileNumber = employeePojo.getMobileNumber();
		this.emailId = employeePojo.getEmailId();
		this.updatedDate = new Date();
		this.updatedBy = employeePojo.getUserName();
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
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary
				+ ", address=" + address + ", department=" + department + ", roles=" + roles + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
