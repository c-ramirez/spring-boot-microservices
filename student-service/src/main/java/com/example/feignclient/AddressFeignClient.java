package com.example.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.response.AddressResponse;

//@FeignClient(value = "address-service", path = "/api/address")
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {
	@GetMapping("/address-service/api/address/getById/{id}")
	public AddressResponse getById(@PathVariable long id);
}
