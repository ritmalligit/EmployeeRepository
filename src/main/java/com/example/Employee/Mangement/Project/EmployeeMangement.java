package com.example.Employee.Mangement.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@Entity
public class EmployeeMangement {
@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Pattern(regexp = "^[a-zA-Z.]*$", message = "First name can only contain letters and dots.")
	private String firstName;
	@Pattern(regexp = "^[a-zA-Z.]*$", message = "Last name can only contain letters and dots.")
	private String lastName;
	@Valid
	private String email;
	@Valid
	private long number;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	

}
