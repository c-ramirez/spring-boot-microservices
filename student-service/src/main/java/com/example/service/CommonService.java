package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feignclient.AddressFeignClient;
import com.example.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class CommonService {
	@Autowired
	AddressFeignClient addressFeignClient;
	
	Logger logger = LoggerFactory.getLogger(CommonService.class);
	long count = 1;

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressbyId")
	public AddressResponse getAddressById(long addressId) {
		logger.info("count {}", count++);
//		Mono<AddressResponse> addressResponse = 
//				webClient.get().uri("/getById/" + addressId)
//		.retrieve().bodyToMono(AddressResponse.class);
//		
//		return addressResponse.block();
		AddressResponse response = addressFeignClient.getById(addressId);
		return response;
	}

	public AddressResponse fallbackGetAddressbyId(long addressId, Throwable th) {
		logger.info("Error {}", th);
		return new AddressResponse();
	}
}
