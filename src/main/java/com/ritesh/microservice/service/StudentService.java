package com.ritesh.microservice.service;

import com.ritesh.microservice.entity.Student;
import com.ritesh.microservice.repository.StudentRepository;
import com.ritesh.microservice.request.CreateStudentRequest;
import com.ritesh.microservice.repository.response.AddressResponse;
import com.ritesh.microservice.repository.response.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService
{
	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AddressServiceClient addressServiceClient;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest)
	{
		StudentResponse response = null;
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		response = new StudentResponse(student);
		response.setAddressResponse(getAddressById(student.getAddressId()));
		return response;
	}

	public StudentResponse getById (long id)
	{
		logger.info("StudentService.getById() called with id: " + id);
		StudentResponse studentResponse = new StudentResponse(studentRepository.findById(id).get());
		studentResponse.setAddressResponse(getAddressById(studentResponse.getAddressId()));
		return studentResponse;
	}

	public AddressResponse getAddressById(long addressId)
	{
		/*
		Mono<AddressResponse> response =
				addressWebClient.get().uri("/api/address/getById/"+ addressId)
						.retrieve()
						.bodyToMono(AddressResponse.class);
		return response.block();
		*/

		return addressServiceClient.getAddressById(addressId);
	}
}
