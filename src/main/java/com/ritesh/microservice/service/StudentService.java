package com.ritesh.microservice.service;

import com.ritesh.microservice.entity.Student;
import com.ritesh.microservice.feignclient.AddressFeignClient;
import com.ritesh.microservice.repository.StudentRepository;
import com.ritesh.microservice.request.CreateStudentRequest;
import com.ritesh.microservice.repository.response.AddressResponse;
import com.ritesh.microservice.repository.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService
{
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WebClient addressWebClient;

	@Autowired
	AddressFeignClient addressFeignClient;

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
		StudentResponse studentResponse = new StudentResponse(studentRepository.findById(id).get());
		studentResponse.setAddressResponse(addressFeignClient.getAddressById(studentResponse.getAddressId()));
		return studentResponse;
	}

	public AddressResponse getAddressById(long addressId)
	{
		Mono<AddressResponse> response =
				addressWebClient.get().uri("/api/address/getById/"+ addressId)
						.retrieve()
						.bodyToMono(AddressResponse.class);
		return response.block();
	}
}
