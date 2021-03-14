package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import com.example.request.CreateAddressRequest;
import com.example.response.AddressResponse;

@Service
public class AddressService {
	Logger log = LoggerFactory.getLogger(AddressService.class);
	@Autowired
	AddressRepository addressRepository;
	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		Address address = new Address();
		address.setStreet(createAddressRequest.getStreet());
		address.setCity(createAddressRequest.getCity());
		addressRepository.save(address);
		return new AddressResponse(address);
		
	}
	
	public AddressResponse getById(long id) {
		log.info("GET ID ADDRESS + "+id);
		Address address = addressRepository.findById(id).orElse(null);
		return new AddressResponse(address);
	}
}
