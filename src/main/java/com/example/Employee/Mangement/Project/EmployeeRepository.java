package com.example.Employee.Mangement.Project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeMangement, Integer> {

	EmployeeMangement findByEmail(String email);
	EmployeeMangement deleteByEmail(String email);
	
}
