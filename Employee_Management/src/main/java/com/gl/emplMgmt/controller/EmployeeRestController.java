package com.gl.emplMgmt.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.emplMgmt.entity.Employee;
import com.gl.emplMgmt.entity.Role;
import com.gl.emplMgmt.entity.User;
import com.gl.emplMgmt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	// return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	// Get employees list with ID
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}

	// add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}

	// update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);
		return theEmployee;
	}

	// delete employee with ID
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);

		// throw exception if null
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);
		return "Deleted employee id - " + employeeId;
	}

	// Search employee with first name
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	// sort employee in ascending or descending order
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String order) {
		return employeeService.sortEmployeeByOrder(order);
	}
	
	/*
	 * @RequestMapping(value = "/403") public String accesssDenied(Principal user) {
	 * 
	 * // ModelAndView model = new ModelAndView();
	 * 
	 * if (user != null) { return "Hi, " + user.getName() + ", "+
	 * "you do not have permission to access this page!"; } else { return
	 * "You do not have permission to access this page!"; }
	 * 
	 * // model.setViewName("403"); // return model;
	 * 
	 * }
	 */
}
