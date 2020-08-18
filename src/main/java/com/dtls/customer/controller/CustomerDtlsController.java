package com.dtls.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtls.customer.exception.CustomerDtlsNotFoundException;
import com.dtls.customer.model.CustomerDtlsEntity;
import com.dtls.customer.service.CustomerDtlsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer/v1")
@Api(value="Customer Details CRU operation", description="Operations persisting/updating/retrieving customer details in database table")
public class CustomerDtlsController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDtlsController.class);
	@Autowired
	private CustomerDtlsService customerDtlsService;
	
	@ApiOperation(value = "Get list of all customer details")
	@GetMapping(path="/getAllCustomerDtls", produces = "application/json")
	public ResponseEntity<List<CustomerDtlsEntity>> getAllCustomerDetails() throws CustomerDtlsNotFoundException {		
		logger.info("Calling customer details service to get all customer details:: :: ::");
		List<CustomerDtlsEntity> customerDtlsList = customerDtlsService.getAllCustomerDtls();
		return new ResponseEntity<List<CustomerDtlsEntity>>(customerDtlsList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get customer details by using identifier")
	@GetMapping(path="/getCustomerDtls/{id}", produces = "application/json")
	public ResponseEntity<CustomerDtlsEntity> getCustomerDtlsById(@PathVariable(value = "id") long id)
			throws CustomerDtlsNotFoundException {
		logger.info("Calling customer details service to get customer details for this id:: :: ::"+id);
		CustomerDtlsEntity customerDtls = customerDtlsService.getCustomerDtlsById(id);
		return new ResponseEntity<CustomerDtlsEntity>(customerDtls, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add customer details into database")
	@PostMapping(path="/addCustomerDtls", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerDtlsEntity> createCustomerDtls(@RequestBody CustomerDtlsEntity customerDtlsEntity) {
		logger.info("Calling customer details service to add customer details:: :: ::");
		CustomerDtlsEntity customerDtls = customerDtlsService.addCustomerDtls(customerDtlsEntity);
		return new ResponseEntity<CustomerDtlsEntity>(customerDtls, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find customer details by firstname and lastname")
	@GetMapping(path="/findCustomerDtlsByFirstNameAndLastName/", produces = "application/json")
	public ResponseEntity<List<CustomerDtlsEntity>> getCustomerDtlsByFirstNameAndLastName(@RequestParam(value="firstName", required=true) String firstName, @RequestParam(value="lastName", required=true) String lastName)
			throws CustomerDtlsNotFoundException {
		logger.info("Calling customer details service to get customer details by firstname and lastname:: :: ::"+firstName +lastName);
		List<CustomerDtlsEntity> customerDtls = customerDtlsService.findCustomerDtlsByFirstNameAndLastName(firstName, lastName);
		return new ResponseEntity<List<CustomerDtlsEntity>>(customerDtls, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find customer details by firstname or lastname")
	@GetMapping(path="/findCustomerDtlsByFirstNameOrLastName/", produces = "application/json")
	public ResponseEntity<List<CustomerDtlsEntity>> getCustomerDtlsByFirstNameOrLastName(@RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="lastName", required=false) String lastName)
			throws CustomerDtlsNotFoundException {
		logger.info("Calling customer details service to get customer details by firstname or lastname:: :: ::"+firstName +lastName);
		List<CustomerDtlsEntity> customerDtls = customerDtlsService.findCustomerDtlsByFirstNameOrLastName(firstName, lastName);
		return new ResponseEntity<List<CustomerDtlsEntity>>(customerDtls, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update customer details by using first name of the customer")
	@PutMapping(path="/updateCustomerDtlsById/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerDtlsEntity> updateCustomerDtlsById(
			@PathVariable(value = "id") long id, @RequestBody CustomerDtlsEntity customerDtlsEntity)
			throws CustomerDtlsNotFoundException {
		logger.info("Calling customer details service to update customer details by using first name:: :: ::");
		CustomerDtlsEntity custDtlsUpdated = customerDtlsService.updateCustomerDtlsById(id, customerDtlsEntity);
		return new ResponseEntity<CustomerDtlsEntity>(custDtlsUpdated, new HttpHeaders(), HttpStatus.OK);
	}
}
