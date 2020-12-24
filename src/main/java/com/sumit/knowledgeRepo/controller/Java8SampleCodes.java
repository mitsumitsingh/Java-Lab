package com.sumit.knowledgeRepo.controller;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Java8SampleCodes {
	
	private static Logger log = LoggerFactory.getLogger(Java8SampleCodes.class);

	public static void main (String[] args) {

	    List<Employee> employees = new LinkedList<>();

	    Employee employee = new Employee(1,"Test0",2000.0);
	    employees.add(employee);
	    employee = new Employee(2,"Test1",5000.0);
	    employees.add(employee);
	    employee = new Employee(3,"Test2",1000.0);
	    employees.add(employee);
	    employee = new Employee(4,"Test3",15000.0);
	    employees.add(employee);
	    log.info("Employees : "+employees.toString());
	    
	    //Sort on the basis of employee name in ascending order.
	    employees.sort(Comparator.comparing(Employee::getEmployeeName));
	    log.info("Employees : "+employees.toString());

	    //Sort on the basis of employee name in descdending order.
	    employees.sort(Comparator.comparing(Employee::getEmployeeName).reversed());
	    log.info("Employees : "+employees.toString());

	    //Sort on the basis of employee name and after that sort on the basis of employee salary.
	    employees.sort(Comparator.comparing(Employee::getEmployeeName).
	    		thenComparingDouble(Employee::getEmployeeSalary));
	    log.info("Sorted Result after sort by employee name then employee salary : "+employees.toString());

	    // Print the sum of the entire organisation.(Salary).
	    Double sumOfSalaryOfOrganisation = employees.stream()
	    		.collect(Collectors.summingDouble(Employee::getEmployeeSalary));
	    
	    log.info("Sum of salary of all the employess : " + sumOfSalaryOfOrganisation.toString());
	    
	    
	    // Increase all the employee salary by 500
	    employees.stream().forEach( e -> e.setEmployeeSalary(e.getEmployeeSalary()+500.0));
	    log.info("Employees record after salary incremented by 500."+employees);
	    
	    Predicate<Employee> salaryLessThan6000 = e -> e.getEmployeeSalary() < 6000; 
	    // Get all the employees whose salary less than 6000.
	    List<Employee> filteredEmployees = employees.stream().filter(salaryLessThan6000).collect(Collectors.toList());
	    log.info("Employees details whose salary is less than 6000 : "+filteredEmployees);
	    
	    // Increment all the employees salary by 200 whose salary is less than 6000.
	    employees.stream().filter(salaryLessThan6000).collect(Collectors.toList()).forEach(e ->{
		    log.info("Employess salary before increment : "+e);
	    	e.setEmployeeSalary(e.getEmployeeSalary()+200);
		    log.info("Employess salary after increment : "+e);
	    });   
	    
	    // Finding avg of employee salary. I.e : totalOrganisationSalary / totalNoOfEmployee
	    Double avgOfSalary = employees.stream().collect(Collectors.averagingDouble(Employee :: getEmployeeSalary));
	    log.info("Avg of all the employees salary : "+avgOfSalary);

	    // Use of toMap
	    Map<String, Double> mapOfENameAndSalary = employees.stream().collect(
                Collectors.toMap(Employee::getEmployeeName, Employee::getEmployeeSalary));
	    log.info("Map of employeeName and Salary : "+mapOfENameAndSalary);
	    
	    // In the above case if employee name will be same for two employees we will get error (Duplicate Key)
	    // To solve this.
	    Map<String, Double> mapOfENameAndSalaryRemovingDuplicateIssue = employees.stream().collect(
                Collectors.toMap(Employee::getEmployeeName, Employee::getEmployeeSalary, (oldValue, newValue) -> oldValue));
	    // (oldValue, newValue) -> oldValue ==> If the key is duplicated, do you prefer oldKey or newKey?
	    // For old key :-> (oldValue, newValue) -> oldValue)
	    // For new Key :-> (oldValue, newValue) -> newValue)
	    log.info("Map of employeeName and Salary : "+mapOfENameAndSalaryRemovingDuplicateIssue);
	    
	  }	
}


class Employee{
	  private int employeeId;
	  private String employeeName;
	  private Double employeeSalary;

	  Employee(int employeeId, String employeeName, Double employeeSalary){
	    this.employeeSalary = employeeSalary;
	    this.employeeName = employeeName;
	    this.employeeId = employeeId;
	  }

	  public int getEmployeeId(){
	    return this.employeeId;
	  }

	  public void setEmployeeId(int employeeId){
	    this.employeeId = employeeId;
	  }

	  public String getEmployeeName(){
	    return this.employeeName;
	  }

	  public void setEmployeeName(String employeeName){
	    this.employeeName = employeeName;
	  }

	  public Double getEmployeeSalary(){
	    return this.employeeSalary;
	  }

	  public void setEmployeeSalary(Double employeeSalary){
	    this.employeeSalary = employeeSalary;
	  }

	  public String toString(){
	    return "employeeId ="+this.employeeId+" "+"employeeSalary ="+this.employeeSalary+" employeeName = "+this.employeeName;
	  }
	}