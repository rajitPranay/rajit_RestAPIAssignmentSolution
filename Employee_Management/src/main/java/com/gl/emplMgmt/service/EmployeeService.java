package com.gl.emplMgmt.service;

import java.util.List;

import com.gl.emplMgmt.entity.Employee;
import com.gl.emplMgmt.entity.Role;
import com.gl.emplMgmt.entity.User;


public interface EmployeeService {
	
	// add role
	public Role saveRole(Role role);
	// add user
	public User saveUser(User user);
	// add or update Employee
	public void save(Employee theEmployee);
	// list all employees
	public List<Employee> findAll();
	// list employees by ID
	public Employee findById(int theId);	
	// delete employee by ID
	public void deleteById(int theId);
	// fetch all employees by firstName
	public List<Employee> searchByFirstName(String firstName);
	// sort employee in ascending or descending order
	List<Employee> sortEmployeeByOrder(String order);

	
}
