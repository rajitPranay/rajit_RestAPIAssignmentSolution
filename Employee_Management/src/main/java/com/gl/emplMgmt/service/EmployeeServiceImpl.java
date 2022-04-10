package com.gl.emplMgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.emplMgmt.dao.EmployeeRepository;
import com.gl.emplMgmt.dao.RoleRepository;
import com.gl.emplMgmt.dao.UserRepository;
import com.gl.emplMgmt.entity.Employee;
import com.gl.emplMgmt.entity.Role;
import com.gl.emplMgmt.entity.User;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findEmployeeByFirstName(firstName.toLowerCase());
	}

	@Override
	public List<Employee> sortEmployeeByOrder(String order) {

		List<Employee> employees = null;

		if (order.toLowerCase().equals("asc")) {
			employees = employeeRepository.findAllByOrderByFirstNameAsc();
		} else if (order.toLowerCase().equals("desc")) {
			employees = employeeRepository.findAllByOrderByFirstNameDesc();
		} else {
			throw new RuntimeException("No proper order mentioned");
		}
		return employees;
	}

}
