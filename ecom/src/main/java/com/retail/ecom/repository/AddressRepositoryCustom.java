package com.retail.ecom.repository;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;

public interface AddressRepositoryCustom {

	public Address saveAddress(Address address);
	public AddressShipping saveShippingAddress(AddressShipping addressShipping);
}
