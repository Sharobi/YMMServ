package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.DeliveryAgent;
import com.retail.ecom.model.DeliveryAgentTracking;
import com.retail.ecom.model.OrderDetails;

public interface DeliveryService {

	DeliveryAgent findUserByUserName(String userName);

	void saveUser(DeliveryAgent user);

	DeliveryAgent getDeliveryPersonById(Integer id);

	List<DeliveryAgent> getDeliveryAgentListByCompanyIdAndStoreId(Integer companyId, Integer storeId);

	List<DeliveryAgent> getDeliveryAgentListByOrganisationId(Integer organisationId);

	List<DeliveryAgent> getDeliveryAgentList();

	void disableOrEnableDeliveryPerson(int id, int isActive);

	List<DeliveryAgent> getDeliveryPersonByPinCode(String pincode,int uId);

	void updateDeliveryPerson(DeliveryAgent deliveryAgent);

	OrderDetails findOrderDetailsById(int id);

	void assignDeliveryPerson(DeliveryAgentTracking dat);

	


	

	

	


}
