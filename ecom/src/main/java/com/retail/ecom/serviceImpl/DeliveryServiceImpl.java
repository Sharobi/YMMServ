
package com.retail.ecom.serviceImpl;
import com.retail.ecom.service.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import com.retail.ecom.repository.*;
import com.retail.ecom.model.*;
import javax.transaction.*;
import java.util.*;

@Service
public class DeliveryServiceImpl implements DeliveryService
{
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private DeliveryPinRepository deliveryPinRepository;
    
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    
    @Autowired
    private DeliveryAgentTrackingRepository deliveryAgentTrackingRepository;
    
    public DeliveryAgent findUserByUserName(String userName) {
        return deliveryRepository.findUserByUserName(userName);
    }
    
    @Transactional
    public void saveUser(DeliveryAgent user) {
        user.setPassword(bCryptPasswordEncoder.encode((CharSequence)user.getPassword()));
        user.setIsActive(1);
        Role userRole = roleRepository.findByRole(user.getLoginType());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        deliveryRepository.save(user);
        if (user.getDeliveryPin() != null && user.getDeliveryPin().size() > 0) {
            for (DeliveryPin deliveryPin : user.getDeliveryPin()) {
                deliveryPin.setIsActive(1);
                deliveryPin.setZoneId(0);
                deliveryPin.setCreatedBy(0);
                deliveryPin.setAreaId(0);
                deliveryPin.setDeliveryAgentId(user.getId());
                deliveryPinRepository.save(deliveryPin);
            }
        }
    }
    
    public DeliveryAgent getDeliveryPersonById( Integer id) {
        return deliveryRepository.getDeliveryPersonById(id);
    }
    
    public List<DeliveryAgent> getDeliveryAgentListByCompanyIdAndStoreId(Integer companyId,  Integer storeId) {
        return deliveryRepository.getDeliveryAgentListByCompanyIdAndStoreId(companyId, storeId);
    }
    
    public List<DeliveryAgent> getDeliveryAgentListByOrganisationId( Integer organisationId) {
        return deliveryRepository.getDeliveryAgentListByOrganisationId(organisationId);
    }
    
    public List<DeliveryAgent> getDeliveryAgentList() {
        return deliveryRepository.getDeliveryAgentList();
    }
    
    public void disableOrEnableDeliveryPerson( int id, int isActive) {
        deliveryRepository.disableOrEnableDeliveryPerson(id, isActive);
    }
    
    public List<DeliveryAgent> getDeliveryPersonByPinCode(String pincode, int uId) {
        return deliveryRepository.getDeliveryPersonByPinCode(pincode, uId);
    }
    
    public void updateDeliveryPerson(DeliveryAgent deliveryAgent) {
        deliveryRepository.save(deliveryAgent);
    }

	@Override
	public OrderDetails findOrderDetailsById(int id) {
		// TODO Auto-generated method stub
		return orderDetailsRepository.findOrderDetailsById(id);
	}

	@Override
	public void assignDeliveryPerson(DeliveryAgentTracking dat) {
		
		orderDetailsRepository.updateDelveryAgentId(dat.getDeliveryAgentId(),dat.getSaleOrderDetailsId());
		
		deliveryAgentTrackingRepository.save(dat);
		
	}

	
    
}