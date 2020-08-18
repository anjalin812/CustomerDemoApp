package com.dtls.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.dtls.customer.controller.CustomerDtlsController;
import com.dtls.customer.exception.CustomerDtlsNotFoundException;
import com.dtls.customer.model.CustomerDtlsEntity;

@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerDemoAppApplicationTests {
	
	@Autowired
	private CustomerDtlsController customerDtlsController;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAddCustomerDtls() {
		CustomerDtlsEntity customerDtls = new CustomerDtlsEntity();
		customerDtls.setFirstName("Robert");
		customerDtls.setLastName("Portugal");
		customerDtls.setAge(30);
		customerDtls.setAddress("Banglore");
		ResponseEntity<CustomerDtlsEntity> customerDtlsEntity = customerDtlsController.createCustomerDtls(customerDtls);
		assertNotNull(customerDtlsEntity);		
	}
	
	@Test
	public void testGetAllCustomerDtls() throws CustomerDtlsNotFoundException{
		ResponseEntity<List<CustomerDtlsEntity>> customerDtlsEntity = customerDtlsController.getAllCustomerDetails();
		assertNotNull(customerDtlsEntity);		
	}
	
	@Test
	public void testGetCustomerDtlsById() throws CustomerDtlsNotFoundException {
		ResponseEntity<CustomerDtlsEntity> customerDtlsEntity = customerDtlsController.getCustomerDtlsById(2);
		String customerfirstName = "";
		if(!customerDtlsEntity.getBody().getFirstName().isEmpty()) {
			customerfirstName = customerDtlsEntity.getBody().getFirstName();
		}
		assertEquals("Aaarya",customerfirstName);		
	}
	
	@Test
	public void testUpdateCustomerDtlsById() throws CustomerDtlsNotFoundException {
		ResponseEntity<CustomerDtlsEntity> customerDtlsEntity = customerDtlsController.getCustomerDtlsById(2);
		CustomerDtlsEntity custDtls = customerDtlsEntity.getBody();
		if (custDtls !=null) {
			custDtls.setAddress("Guntur");
		}				
		ResponseEntity<CustomerDtlsEntity> customerDtls = customerDtlsController.updateCustomerDtlsById(custDtls.getId(), custDtls);
		
		assertNotNull(customerDtls);		
	}
	
	@Test
	public void testFindCustomersByFirstNameAndLastName() throws CustomerDtlsNotFoundException {
		ResponseEntity<List<CustomerDtlsEntity>> customerDtls = customerDtlsController.getCustomerDtlsByFirstNameAndLastName("Anj", "pon");
		String custAddress = "";
		CustomerDtlsEntity custDtls = customerDtls.getBody().get(0);	
		if(!custDtls.getAddress().isEmpty()) {
			custAddress = custDtls.getAddress();
		}
		assertEquals("Mum",custAddress);	
	}
	
	@Test
	public void testFindCustomersByFirstNameOrLastName() throws CustomerDtlsNotFoundException {
		ResponseEntity<List<CustomerDtlsEntity>> customerDtls = customerDtlsController.getCustomerDtlsByFirstNameOrLastName("Rohan", "abc");
		String custAddress = "";
		CustomerDtlsEntity custDtls = customerDtls.getBody().get(0);	
		if(!custDtls.getAddress().isEmpty()) {
			custAddress = custDtls.getAddress();
		}
		assertEquals("Pune",custAddress);	
	}
}
