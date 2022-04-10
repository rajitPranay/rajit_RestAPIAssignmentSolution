package com.gl.emplMgmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gl.emplMgmt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE %:firstName%")
	List<Employee> findEmployeeByFirstName(@Param("firstName") String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();

	List<Employee> findAllByOrderByFirstNameDesc();

}
