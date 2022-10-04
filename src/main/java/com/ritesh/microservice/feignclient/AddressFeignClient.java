package com.ritesh.microservice.feignclient;

import com.ritesh.microservice.repository.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
Without Eureka
@FeignClient(url = "${service.address-service.url}", path = "/api/address", value = "address-feign-client")
*/

// With Eureka
@FeignClient(value = "address-service", path = "/api/address")
public interface AddressFeignClient
{
    @GetMapping("/getById/{id}")
    AddressResponse getAddressById(@PathVariable Long id);

    /*Mapping Response To String*/
    @GetMapping("/getById/{id}")
    String getAddressStringById(@PathVariable Long id);
}
