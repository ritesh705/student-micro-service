package com.ritesh.microservice.service;

import com.ritesh.microservice.feignclient.AddressFeignClient;
import com.ritesh.microservice.repository.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceClient.class);

    private static int count = 0;

    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "address-service-cb", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId)
    {
        count++;
        logger.info("Count: "+count);
        return addressFeignClient.getAddressById(addressId);
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable th)
    {
        logger.error(th.getMessage());
        return new AddressResponse();
    }
}
