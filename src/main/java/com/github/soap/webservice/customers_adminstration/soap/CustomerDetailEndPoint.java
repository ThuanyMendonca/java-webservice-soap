package com.github.soap.webservice.customers_adminstration.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.github.soap.webservice.customers_adminstration.bean.Customer;
import com.github.soap.webservice.customers_adminstration.service.CustomerDetailService;

import br.com.thuany.CustomerDetail;
import br.com.thuany.GetCustomerDetailRequest;
import br.com.thuany.GetCustomerDetailResponse;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace="http://thuany.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req) throws Exception{
		Customer customer = service.findById(req.getId());
		
		if(customer == null) {
			throw new Exception("Invalid Customer id " + req.getId());
		}
		
		return convertToGetCustomerDetailResponse(customer);
	}
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse resp = new GetCustomerDetailResponse();
		
		resp.setCustomerDetail(convertToCustomerDetail(customer));
		
		return resp;
	}
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		
		return customerDetail;
	}
}
