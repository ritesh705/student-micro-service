package com.ritesh.microservice.repository.response;

import com.ritesh.microservice.entity.Student;

public class StudentResponse {

	private long id;

	private long addressId;

	private String firstName;

	private String lastName;

	private String email;

	private AddressResponse addressResponse;

	public StudentResponse(Student student)
	{
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		this.addressId = student.getAddressId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}
}
