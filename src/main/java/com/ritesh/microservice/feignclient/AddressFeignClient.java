package com.ritesh.microservice.feignclient;

import com.ritesh.microservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${service.address-service.url}", path = "/", value = "address-feign-client")
public interface AddressFeignClient
{
    @GetMapping("/getById/{id}")
    AddressResponse getAddressById(@PathVariable Long id);
}
