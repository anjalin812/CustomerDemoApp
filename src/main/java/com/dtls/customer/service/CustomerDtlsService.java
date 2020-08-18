package com.dtls.customer.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtls.customer.exception.CustomerDtlsNotFoundException;
import com.dtls.customer.model.CustomerDtlsEntity;
import com.dtls.customer.repository.CustomerDtlsRepository;

@Service
public class CustomerDtlsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDtlsService.class);

	@Autowired
	private CustomerDtlsRepository customerDtlsRepository;

	public List<CustomerDtlsEntity> getAllCustomerDtls() throws CustomerDtlsNotFoundException{
		logger.info("Retrieving all customer details from the database ....");
		List<CustomerDtlsEntity> custDetails = customerDtlsRepository.findAll();
		return custDetails;
	}

	public CustomerDtlsEntity addCustomerDtls(CustomerDtlsEntity customerDtlsEntity) {
		logger.info("Adding customer details into database ....");
		return customerDtlsRepository.save(customerDtlsEntity);
	}

	public CustomerDtlsEntity getCustomerDtlsById(long id) throws CustomerDtlsNotFoundException{
		logger.info("Retrieving customer details for the specific id from the database ....");
		CustomerDtlsEntity customerDtlsEntity = customerDtlsRepository.findById(id)
		          .orElseThrow(() -> new CustomerDtlsNotFoundException("Customer Details not found for this id :: " + id));
		return customerDtlsEntity;
	}
	
	public CustomerDtlsEntity updateCustomerDtlsById(long id, CustomerDtlsEntity customerDtls) throws CustomerDtlsNotFoundException{
		logger.info("Update customer details by using firstname in database ....");
		CustomerDtlsEntity customerDtlsEntity = customerDtlsRepository.findById(id).
				orElseThrow(() -> new CustomerDtlsNotFoundException("Customer Details not found for this id :: " + id));
		if(customerDtlsEntity != null && customerDtlsEntity.getFirstName() != null &&
				customerDtlsEntity.getLastName() != null && customerDtlsEntity.getAge() >0 && customerDtlsEntity.getAddress() !=null) {
			
			customerDtlsEntity.setAddress(customerDtls.getAddress());
 
			customerDtlsEntity = customerDtlsRepository.save(customerDtlsEntity);
			return customerDtlsEntity;
            
        } else {
        	customerDtls = customerDtlsRepository.save(customerDtls);
            return customerDtls;
        }
	}
	
	public List<CustomerDtlsEntity> findCustomerDtlsByFirstNameAndLastName(String firstName, String lastName) throws CustomerDtlsNotFoundException{
		logger.info("calling repositry to get customers for given first name and lastname....");
		List<CustomerDtlsEntity> customerDtlsEntity = customerDtlsRepository.getCustomersByFirstNameAndLastName(firstName, lastName);
			return customerDtlsEntity;
	}
	
	public List<CustomerDtlsEntity> findCustomerDtlsByFirstNameOrLastName(String firstName, String lastName) throws CustomerDtlsNotFoundException{
		logger.info("calling repositry to get customers for given first name or lastname....");
		List<CustomerDtlsEntity> customerDtlsEntity = customerDtlsRepository.getCustomersByFirstNameOrLastName(firstName, lastName);
			return customerDtlsEntity;
	}
}
