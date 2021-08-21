package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.retail.ecom.model.DeliveryAgent;

@Repository("deliveryRepository")
public interface DeliveryRepository extends CrudRepository<DeliveryAgent, Integer> {

	DeliveryAgent findUserByUserName(String userName);

	@Query("select d from DeliveryAgent d where d.id=?1")
	DeliveryAgent getDeliveryPersonById(Integer id);

	@Query("select d from DeliveryAgent d where d.companyId=?1 and d.storeId=?2")
	List<DeliveryAgent> getDeliveryAgentListByCompanyIdAndStoreId(Integer companyId, Integer storeId);
	
	@Query("select d from DeliveryAgent d where d.organisationId=?1")
	List<DeliveryAgent> getDeliveryAgentListByOrganisationId(Integer organisationId);

	@Query("select d from DeliveryAgent d")
	List<DeliveryAgent> getDeliveryAgentList();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update DeliveryAgent d set d.isActive =?2  where d.id = ?1")
	public void disableOrEnableDeliveryPerson(int id, int isActive);

	//SELECT a, b FROM Author a LEFT JOIN a.books b
	//@Query("select d from DeliveryAgent d where d.isActive=1")in ?1
	@Query("select d from DeliveryAgent d left outer join d.deliveryPin dp where dp.pincode in ?1 and d.storeId=0 and d.companyId=0")
	List<DeliveryAgent> getDeliveryPersonByPinCode(String pincode,int uId);

}
