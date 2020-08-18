package com.dtls.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dtls.customer.model.CustomerDtlsEntity;

@Repository
public interface CustomerDtlsRepository extends JpaRepository<CustomerDtlsEntity, Long>{
	//Native Query
	@Query(value="select * from Customer_Details cd where cd.first_Name=?1 and cd.last_Name=?2", nativeQuery=true)
	public List<CustomerDtlsEntity> getCustomersByFirstNameAndLastName(String firstName, String lastName);
	
	@Query(value="select * from Customer_Details cd where cd.first_Name=?1 or cd.last_Name=?2", nativeQuery=true)
	public List<CustomerDtlsEntity> getCustomersByFirstNameOrLastName(String firstName, String lastName);
	
}
