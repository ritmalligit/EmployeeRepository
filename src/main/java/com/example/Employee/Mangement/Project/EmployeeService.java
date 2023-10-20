package com.example.Employee.Mangement.Project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	
	  public List<String> validateName(String name) {
	        List<String> errors = new ArrayList<>();
	        if (name == null || !name.matches("^[A-Za-z\\s]+$")) {
	            errors.add("Invalid name. Please provide a valid name.");
	        }
	        return errors;
	    }

}
