package com.retail.ecom.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.retail.ecom.exception.InvalidOrder;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.LatLngGeoLocations;
import com.retail.ecom.model.Membership;
import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.OrderBenefit;
import com.retail.ecom.model.OrderDetails;
import com.retail.ecom.model.OrderEligibleStore;
import com.retail.ecom.model.PaytmDetails;
import com.retail.ecom.model.Prescription;
import com.retail.ecom.model.Store;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.CartRepository;
import com.retail.ecom.repository.ItemMappingRepository;
import com.retail.ecom.repository.ItemRepository;
import com.retail.ecom.repository.OrderBenefitRepository;
import com.retail.ecom.repository.OrderDetailsRepository;
import com.retail.ecom.repository.OrderEligibleStoreRepository;
import com.retail.ecom.repository.OrderRepository;
import com.retail.ecom.repository.PaytmDetailRepository;
import com.retail.ecom.repository.PrescriptionRepository;
import com.retail.ecom.repository.StoreRepository;
import com.retail.ecom.service.MembershipFeatureService;
import com.retail.ecom.service.MembershipService;
import com.retail.ecom.service.OrderService;
import com.retail.ecom.service.PointTrackingService;
import com.retail.ecom.service.StoreService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository or;

	@Autowired
	private OrderDetailsRepository odr;

	@Autowired
	private ItemRepository ir;

	@Autowired
	private StoreService ss;

	@Autowired
	private OrderEligibleStoreRepository oesr;

	@Autowired
	private CartRepository cr;

	@Autowired
	private StoreRepository sr;

	@Autowired
	private PrescriptionRepository pr;

	@Autowired
	private ItemMappingRepository imr;

	@Autowired
	private MembershipService ms;

	@Autowired
	private MembershipFeatureService mfs;

	@Autowired
	private PointTrackingService pts;

	@Autowired
	private OrderBenefitRepository obr;
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	PaytmDetailRepository pdr;

	@Override
	public List<Order> getAllByUser(int uid, int page, int limit) {
		return or.getAllByUser(uid, page, limit);
	}

	@Override
	public Order getOrderById(int saleOrderId, int uid) {
		// return or.getOrderById(saleOrderId,uid);
		return or.findByOrderId(saleOrderId);
	}

	@Override
	public List<Order> getAllForStoreAdmin(int sid, int cid, int page, int limit, String startDate, String endDate,
			int itemId, int status) {
//		Integer sid = sr.getGlobalStoreId(cid, lsid);
		Optional<Store> s = sr.findById(sid);
		List<Integer> oids = new ArrayList<Integer>();
		if (!s.isPresent()) {
			throw new NullPointerException("No Store Found, Please select a valid store.");
		}

		if (status != 1 && status != 2 && status != 3 && status != 4) {
			oids = oesr.getOrderIdsByStore(sid);
			if (oids == null || oids.size() <= 0) {
				throw new NullPointerException("No online order for your store.");
			}
			return or.getAllforStoreAdmin(oids, page, limit, startDate, endDate, itemId, status);
		} else {
			return or.getAllforStoreAdmin(sid, page, limit, startDate, endDate, itemId, status);
		}
	}

	@Override
	@Transactional
	public ResponseDetails addOrder(Order o, int uid) {
		List<LatLngGeoLocations> lisOflatlngs;
		lisOflatlngs = imr.getLatLngByPin(o.getPinCode());
		

		/*
		 * if(o.getLat()<=0 || o.getLng()<=0) { return new ResponseDetails(new Date(),
		 * "Please select Latitude and Longitude", null, 0); }
		 */
		o.setCustomerId(uid);
		or.save(o);
		o.getAddressShipping().setUserId(uid);
		o.getAddressShipping().setOrderId(o.getId());
		addressRepository.saveShippingAddress(o.getAddressShipping());

		if (o.getOrderDetails().size() > 0) {
			for (Iterator iterator = o.getOrderDetails().iterator(); iterator.hasNext();) {
				OrderDetails od = (OrderDetails) iterator.next();
				od.setSaleOrderId(o.getId());
				odr.save(od);
				//System.err.println(od.getCartId());
				// System.out.println();
				if (cr.findCartById(od.getCartId()) == null) {
					throw new InvalidOrder("Item not present in cart");
				}

				cr.deleteCartById(od.getCartId(), uid);

				///Check LatLngGeoLocations here
				
				/*if (!lisOflatlngs.isEmpty()) {
					for (Iterator iterator1 = lisOflatlngs.iterator(); iterator1.hasNext();) {
						LatLngGeoLocations latlngs = (LatLngGeoLocations) iterator1.next();
						//List<Integer> sids = ss.getStoreIdsInRadius(latlngs.getLatitude(), latlngs.getLongitude());//changed for redious issue 11-1-2021 by Sayantan Mahanty
						List<Integer> sids = ss.getStoreIdsByPincode(o.getAddressShipping().getPincode().toString());
						List<Integer> eligiblesids = ir.getEligibleStoreIdsByIdRadius(od.getItemId(), sids,
								od.getPackQty());
						if (eligiblesids != null && eligiblesids.size() > 0) {
							for (Iterator i2 = eligiblesids.iterator(); i2.hasNext();) {
								Integer sid = (Integer) i2.next();
								OrderEligibleStore oes = new OrderEligibleStore();
								oes.setOrderId(o.getId());
								oes.setOrderDetailsId(od.getId());
								oes.setStoreId(sid);
								oesr.save(oes);
							}
						}
					}
				}*/
				List<Integer> sids = ss.getStoreIdsByPincode(o.getAddressShipping().getPincode().toString());
				if(!sids.isEmpty()) {
					List<Integer> eligiblesids = ir.getEligibleStoreIdsByIdRadius(od.getItemId(), sids,
							od.getPackQty());
					if (eligiblesids != null && eligiblesids.size() > 0) {
						for (Iterator i2 = eligiblesids.iterator(); i2.hasNext();) {
							Integer sid = (Integer) i2.next();
							OrderEligibleStore oes = new OrderEligibleStore();
							oes.setOrderId(o.getId());
							oes.setOrderDetailsId(od.getId());
							oes.setStoreId(sid);
							oesr.save(oes);
						}
					}   
				}
				else {
					return new ResponseDetails(new Date(), "InvalidPinCode", null, 0);
				}
				/*
				 * List<Integer> sids = ss.getStoreIdsInRadius(o.getLat(), o.getLng());
				 * List<Integer> eligiblesids = ir.getEligibleStoreIdsByIdRadius(od.getItemId(),
				 * sids, od.getPackQty()); if(eligiblesids != null && eligiblesids.size()>0) {
				 * for (Iterator i2 = eligiblesids.iterator(); i2.hasNext();) { Integer sid =
				 * (Integer) i2.next(); OrderEligibleStore oes = new OrderEligibleStore();
				 * oes.setOrderId(o.getId()); oes.setOrderDetailsId(od.getId());
				 * oes.setStoreId(sid); oesr.save(oes); } }
				 */
			}
		}

		if (o.getPrescriptionId() != null) {
			Prescription p = pr.findPrescriptionById(o.getPrescriptionId());
			p.setSaleOrderId(o.getId());
			pr.save(p);
		}

		if (o.getPrescriptionIds() != null && o.getPrescriptionIds().size() > 0) {
			for (Iterator iterator = o.getPrescriptionIds().iterator(); iterator.hasNext();) {
				int pid = (int) iterator.next();
				Prescription p = pr.findPrescriptionById(pid);
				p.setSaleOrderId(o.getId());
				pr.save(p);
			}
		}
		/**/
		if (o.getMembershipFeatureIds().length > 0) {
			// System.out.println("mfids = "+ o.getMembershipFeatureIds()[0]);

			Membership membership = ms.getMembershipByUid(uid);
			membership.getMembershipFeatures();
			boolean valid = false;
			for (Iterator itr = membership.getMembershipFeatures().iterator(); itr.hasNext();) {
				MembershipFeature mf = (MembershipFeature) itr.next();
				if (Arrays.asList(o.getMembershipFeatureIds()).contains(mf.getId())) {
					valid = true;
					break;
				}
			}
			if (!valid) {
				throw new InvalidOrder("Not a valid order, please try again.");
			}
//			System.out.println("Membership = "+membership.getMembershipFeatures().get(0));
			/*
			 * if(mfs.checkValidFeatures(membership.getId(),o.getMembershipFeatureIds())) {
			 * System.out.println("test"); }
			 */
		}

		if (o.getOrderBenefits() != null && o.getOrderBenefits().size() > 0) {
			obr.saveAll(o.getOrderBenefits());
			for (Iterator iterator = o.getOrderBenefits().iterator(); iterator.hasNext();) {
				OrderBenefit ob = (OrderBenefit) iterator.next();
				ob.setOrderId(o.getId());
				obr.save(ob);
			}
		}
		return new ResponseDetails(new Date(), "Your order has been successfully submitted", null, o.getId());
	}

	@Override
	public ResponseDetails cancelOrder(int oid, Integer id) {
		or.cancelOrder(oid, id);
		pts.delete(oid);
		return new ResponseDetails(new Date(), "Your order cancelled successfully", null, 1);
	}

	@Override
	@Transactional
	public ResponseDetails acceptOrder(Integer oid, int companyId, int storeId) {
		Optional<OrderDetails> od = odr.findById(oid);
//		System.out.println("oid = "+oid + "order = "+od.get()+" check = "+od.isPresent());
		if (od.isPresent()) {
			if (od.get().getStatus() == 0) {
				double avlQty = imr.getQuantityByItemIdStore(od.get().getItemId(), companyId, storeId);
				if (od.get().getPackQty() <= avlQty) {
					od.get().setStatus(1);
					od.get().setCompanyId(companyId);
					od.get().setStoreId(storeId);
					odr.save(od.get());
					imr.updateQuantityOnAccept(companyId, storeId, od.get().getItemId(), (int) od.get().getPackQty());
				} else {
					throw new InvalidOrder("Sorry Ordered quantity not available. You have : " + avlQty);
					// return new ResponseDetails(new Date(), "Sorry Ordered quantity not available.
					// You have : "+avlQty, null, 0);
				}
			} else if (od.get().getStatus() == 1) {
//				return new ResponseDetails(new Date(), "Order is already accepted", null, 0);
				throw new InvalidOrder("Order is already accepted");
			} else if (od.get().getStatus() == 2) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully accepted the order", null, 1);
	}

	@Override
	public ResponseDetails acceptAllOrder(List<OrderDetails> orderDetails, int companyId, int storeId) {
		ResponseDetails ods = null;
		for (Iterator iterator = orderDetails.iterator(); iterator.hasNext();) {
			OrderDetails singleOrderDetails = (OrderDetails) iterator.next();
			ods = acceptOrder(singleOrderDetails.getId(), companyId, storeId);
		}
		return ods;
	}

	/*
	 * @Override public ResponseDetails saleOrder(Integer oid, int companyId, int
	 * storeId) { Optional<OrderDetails> od = odr.findById(oid); if(od.isPresent())
	 * { if(od.get().getStatus()==1 && od.get().getCompanyId()==companyId &&
	 * od.get().getStoreId()==storeId ) { od.get().setStatus(2); odr.save(od.get());
	 * //oesr.deleteByOrderDetailsId(oid); } else if(od.get().getStatus()==2) { //
	 * return new ResponseDetails(new Date(), "Order is already sold", null, 0);
	 * throw new InvalidOrder("Order is already sold"); } else { // return new
	 * ResponseDetails(new Date(), "Sorry not a  valid order", null, 0); throw new
	 * InvalidOrder("Sorry not a  valid order"); } } else { // return new
	 * ResponseDetails(new Date(), "Sorry not a  valid order", null, 0); throw new
	 * InvalidOrder("Sorry not a  valid order"); } return new ResponseDetails(new
	 * Date(), "You have successfully sold the order", null, 1); }
	 */
	@Override
	public ResponseDetails saleAllOrder(List<OrderDetails> orderDetails, int companyId, int storeId) {
		ResponseDetails ods = null;
		for (Iterator iterator = orderDetails.iterator(); iterator.hasNext();) {
			OrderDetails singleOrderDetails = (OrderDetails) iterator.next();
			// ods = saleOrder(singleOrderDetails.getId(),companyId,storeId);
		}
		return ods;
	}

	@Override
	public List<Order> getAllForAdmin(int page, int limit, String startDate, String endDate, int itemId, int status) {
//		Integer sid = sr.getGlobalStoreId(cid, lsid);
		return or.getAllforAdmin(page, limit, startDate, endDate, itemId, status);
	}

	@Override
	public List<Order> getAllForAgentAdmin(int page, int limit, String startDate, String endDate, int itemId,
			int status, Integer userId) {//mapType added
//		Integer sid = sr.getGlobalStoreId(cid, lsid);
		int mapType=1;
		List<Integer> pinCodes = addressRepository.getPinCodeByUserId(userId,mapType);/// use it in the
		return or.getAllforAgentAdmin(page, limit, startDate, endDate, itemId, status, pinCodes);
	}

	@Override
	public List<Order> getAllTopSaleOrderItems() {
		return or.getAllTopSaleOrderItems(new PageRequest(0, 100));

		// return or.getAllTopSaleOrderItems(new PageRequest(0,100));
	}

	/// new added 11-3-2020
	@Override
	public int getLastOneHourOrdersForStore(int storeId) {
		// TODO Auto-generated method stub
		Optional<Store> s = sr.findById(storeId);
		List<Integer> oids = new ArrayList<Integer>();
		if (!s.isPresent()) {
			throw new NullPointerException("No Store Found, Please select a valid store.");
		}
		oids = oesr.getOrderIdsByStore(storeId);
		if (oids == null || oids.size() <= 0) {
			throw new NullPointerException("No online order for your store.");
		}
		return or.getLastOneHourOrdersForStore(oids, new Date(System.currentTimeMillis() - 5 * 60 * 60 * 1000));
	}

	@Override
	public ResponseDetails assignOrderItemStore(int id, int storeId, int companyId, String deliveryType) {
		// TODO Auto-generated method stub
		odr.assignOrderItemStore(id, storeId, companyId, deliveryType);
		return new ResponseDetails(new Date(), "Your order Assigned successfully", null, 1);
	}

	@Override
	public ResponseDetails saleOrderItemFromStore(int id, String batchNo, String expiryDate) {
		// TODO Auto-generated method stub
		odr.saleOrderItemFromStore(id, batchNo, expiryDate);
		return new ResponseDetails(new Date(), "Your order Sold successfully", null, 1);
	}

	@Override
	public void savePgDetails(PaytmDetails pd) {
		pdr.save(pd);
	}

	@Override
	public ResponseDetails saleOrder(Integer oid, String expiryDate, String batchNo, int companyId, int storeId,
			String pickupDate, String pickupTime) {
		Optional<OrderDetails> od = odr.findById(oid);
		if (od.isPresent()) {
			if (od.get().getStatus() == 1 && od.get().getCompanyId() == companyId && od.get().getStoreId() == storeId) {
				od.get().setStatus(2);
				od.get().setBatchNo(batchNo);
				od.get().setExpiryDate(expiryDate);

				// oesr.deleteByOrderDetailsId(oid);
				try {
					LocalDate currentdate = LocalDate.now();

					if (pickupDate.equals("1"))
						od.get().setPickupDate(currentdate.toString());

					if (pickupDate.equals("2"))
						od.get().setPickupDate(currentdate.plusDays(1).toString());

					if (pickupDate.equals("3"))
						od.get().setPickupDate(currentdate.plusDays(2).toString());

					od.get().setPickupTime(pickupTime);
				} catch (Exception e) {

				}
				odr.save(od.get());
			} else if (od.get().getStatus() == 2) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully sold the order", null, 1);
	}

	@Override
	public ResponseDetails dispatchOrder(Integer oid, int companyId, int storeId) {
		Optional<OrderDetails> od = odr.findById(oid);
		if (od.isPresent()) {
			if (od.get().getStatus() == 2 && od.get().getCompanyId() == companyId && od.get().getStoreId() == storeId) {
				od.get().setStatus(3);

				odr.save(od.get());
				// oesr.deleteByOrderDetailsId(oid);
			} else if (od.get().getStatus() == 3) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully dispatched the order", null, 1);
	}

	@Override
	public ResponseDetails deliverOrder(Integer oid, int companyId, int storeId) {
		Optional<OrderDetails> od = odr.findById(oid);
		if (od.isPresent()) {
			if (od.get().getStatus() == 3 && od.get().getCompanyId() == companyId && od.get().getStoreId() == storeId) {
				od.get().setStatus(4);

				odr.save(od.get());
				// oesr.deleteByOrderDetailsId(oid);
			} else if (od.get().getStatus() == 4) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully deliver the order", null, 1);
	}

	@Override
	public ResponseDetails dispatchItemFromStore(Integer oid) {
		Optional<OrderDetails> od = odr.findById(oid);
		if (od.isPresent()) {
			if (od.get().getStatus() == 2) {
				od.get().setStatus(3);

				odr.save(od.get());

			}

			else if (od.get().getStatus() == 3) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully dispatched the order", null, 1);
	}

	@Override
	public ResponseDetails deliverOrderItemFromStore(Integer oid) {
		Optional<OrderDetails> od = odr.findById(oid);
		if (od.isPresent()) {
			if (od.get().getStatus() == 3) {
				od.get().setStatus(4);

				odr.save(od.get());

			}

			else if (od.get().getStatus() == 4) {
//				return new ResponseDetails(new Date(), "Order is already sold", null, 0);
				throw new InvalidOrder("Order is already sold");
			} else {
//				return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
				throw new InvalidOrder("Sorry not a  valid order");
			}
		} else {
//			return new ResponseDetails(new Date(), "Sorry not a  valid order", null, 0);
			throw new InvalidOrder("Sorry not a  valid order");
		}
		return new ResponseDetails(new Date(), "You have successfully deliver the order", null, 1);
	}

	@Override
	public OrderDetails getSaleOrderDetailsById(Integer id) {
		// TODO Auto-generated method stub
		return odr.findOrderDetailsById(id);
	}

	@Override
	public void setDaliveryTimeAndDate(OrderDetails orderdet) {
		odr.save(orderdet);
	}

	@Override
	public List<Order> getOrderDetailsByDeliveryTypeAndStatus(String deliveryType, int status) {
		// TODO Auto-generated method stub
		return or.getOrderDetailsByDeliveryTypeAndStatus(deliveryType,status);
	}


	@Override
	public void setPickUpOrder(Integer id, int status) {
		// TODO Auto-generated method stub
		odr.setPickUpOrder(id,status);
	}

	@Override
	public List<OrderDetails> getOrderDetailsById(Integer storeId, Integer companyId, Integer id) {
		// TODO Auto-generated method stub
		return odr.getOrderDetailsById(storeId,companyId,id);
	}

	@Override
	public List<Order> getOrderDetailsByDeliveryPersonId(Integer deliveryPersonId,int status) {
		// TODO Auto-generated method stub
		return or.getOrderDetailsByDeliveryPersonId(deliveryPersonId,status);
	}

	@Override
	public void setOrderDetailsStatus(int status, Integer id) {
		// TODO Auto-generated method stub
		odr.setOrderDetailsStatus(status,id);
	}

	

}
