package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.Country;
import com.retail.ecom.model.Order;

public interface AddressRepository extends CrudRepository<Address, Integer>,AddressRepositoryCustom {
	
	//@EntityGraph(attributePaths = "user")
	//@EntityGraph(value="address.without.user", type = EntityGraphType.LOAD)
	//@Query("select new Address(a.id, a.user.id, a.streetAddress, a.countryId, a.stateId, a.city,a.landmark, a.pincode, a.latitude, a.longitude, a.contactPhone,a.alternatePhone, a.invoiceMail, a.alternateInvoiceMail, a.isActive, a.isDefault,a.type) from Address a")
//	@Query()
	List<Address> findAll();
	
	@Query("select a from Address a join fetch a.country c join fetch a.state s join fetch a.city ci where a.id=?1")
	Address findAddressById(int id);
	
	@Query("select a from AddressShipping a join fetch a.country c join fetch a.state s join fetch a.city ci where a.orderId=?1")
	AddressShipping findShippingAddressByOrderId(int orderId);//15-01-2020

	@Query(value="select postcode from in_t_user_pincode_mapping where user_id=?1 and map_type=?2",nativeQuery = true)
	List<Integer> getPinCodeByUserId(int userId,int mapType);
}
