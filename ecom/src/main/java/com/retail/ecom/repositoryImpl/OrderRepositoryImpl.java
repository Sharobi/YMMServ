package com.retail.ecom.repositoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.AddressShipping;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.OrderBenefit;
import com.retail.ecom.model.OrderDetails;
import com.retail.ecom.model.Prescription;
import com.retail.ecom.model.Store;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.OrderRepositoryCustom;
import com.retail.ecom.repository.StoreRepository;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	StoreRepository  storeRepository;
	
	@Override
	public List<Order> getAllByUser(int uid,int page,int limit) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		ListJoin<Order, OrderBenefit> ob = order.joinList("orderBenefits",JoinType.LEFT);
		
		cq.select(order).where(cb.equal(order.get("customerId"),uid)).orderBy(cb.desc(order.get("createdDate"))).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true); 
		
		/*Subquery<OrderDetails> odq = cq.subquery(OrderDetails.class);
		Root od = odq.from(OrderDetails.class);
		odq.select(od).where(cb.equal(order.get("id"), od.get("saleOrderId")));
		cq.multiselect(order.get("customerId"),odq.getSelection());*/
		
		Query query = em.createQuery(cq).setFirstResult((page -1)*limit).setMaxResults(limit);
		
		List<Order> orders = query.getResultList();
		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}/*
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			Address add = addressRepository.findAddressById(order2.getCustomerAddressId());
			order2.setAddress(add);
		}*/
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		return orders;
	}
	
	@Override
	public List<Order> getAllforStoreAdmin(List<Integer> oids, int page, int limit,String startDate,String endDate,int itemId,int status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		/*if(itemId>0) {
			//od = order.joinList("orderDetails",JoinType.INNER);
		}*/
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		ListJoin<Order, OrderBenefit> ob = order.joinList("orderBenefits",JoinType.LEFT);
		Date startDt = null;
		Date endDt = null;
		
		List<Predicate> conditions = new ArrayList<>();
		conditions.add(order.get("id").in(oids));
		conditions.add(cb.equal(order.get("isCanceled"),0));
		conditions.add(cb.equal(order.get("isDeleted"),0));
		
		if(startDate.equals("0000-00-00")) {
			if(status!=0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					startDt = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//System.out.println("start date = "+startDt);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			}
		} else {
			try {
				startDt = new SimpleDateFormat("yyy-MM-dd").parse(startDate);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(endDate.equals("0000-00-00")) {
			if(status!=0) {
				Date today=new Date();
				long ltime=today.getTime()+1*24*60*60*1000;
				endDt = new Date(ltime);
				//System.out.println("end date = "+endDt);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			}
		} else {
			try {
				endDt = new SimpleDateFormat("yyy-MM-dd").parse(endDate);
				endDt = new Date(endDt.getTime()+1*24*60*60*1000);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(itemId>0) {
			conditions.add(cb.equal(od.get("itemId"),itemId));
		}
		
		if(status>=0) {
			conditions.add(cb.equal(od.get("status"),status));
		}
		
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		//cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		
		/*Subquery<OrderDetails> odq = cq.subquery(OrderDetails.class);
		Root od = odq.from(OrderDetails.class);
		
		odq.select(od).where(cb.equal(order.get("id"), od.get("saleOrderId")));
		cq.multiselect(order.get("customerId"),odq.getSelection());*/
		
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		
		List<Order> orders = query.getResultList();
		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);
		
		
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		/*for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			Address add = addressRepository.findAddressById(order2.getCustomerAddressId());
			order2.setAddress(add);
		}*/
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		return orders;
	}
	
	@Override
	public List<Order> getAllforStoreAdmin(int sid, int page, int limit,String startDate,String endDate,int itemId,int status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		/*if(itemId>0) {
			//od = order.joinList("orderDetails",JoinType.INNER);
		}*/
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		Date startDt = null;
		Date endDt = null;
		
		List<Predicate> conditions = new ArrayList<>();
//		conditions.add(order.get("id").in(oids));
//		conditions.add(cb.equal(order.get("storeId"),sid));
		conditions.add(cb.equal(order.get("isCanceled"),0));
		conditions.add(cb.equal(order.get("isDeleted"),0));
		
		if(startDate.equals("0000-00-00")) {
			if(status!=0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					startDt = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//System.out.println("start date = "+startDt);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			}
		} else {
			try {
				startDt = new SimpleDateFormat("yyy-MM-dd").parse(startDate);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(endDate.equals("0000-00-00")) {
			if(status!=0) {
				Date today=new Date();
				long ltime=today.getTime()+1*24*60*60*1000;
				endDt = new Date(ltime);
				//System.out.println("end date = "+endDt);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			}
		} else {
			try {
				endDt = new SimpleDateFormat("yyy-MM-dd").parse(endDate);
				endDt = new Date(endDt.getTime()+1*24*60*60*1000);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(itemId>0) {
			conditions.add(cb.equal(od.get("itemId"),itemId));
		}
		
		if(status>=0) {
			conditions.add(cb.equal(od.get("status"),status));
		}
		
		if(status==1 || status==2) {
			conditions.add(cb.equal(od.get("storeId"),sid));
		}
		
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		//cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		
		/*Subquery<OrderDetails> odq = cq.subquery(OrderDetails.class);
		Root od = odq.from(OrderDetails.class);
		
		odq.select(od).where(cb.equal(order.get("id"), od.get("saleOrderId")));
		cq.multiselect(order.get("customerId"),odq.getSelection());*/
		
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		
		List<Order> orders = query.getResultList();
		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);
		
		
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		/*for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			Address add = addressRepository.findAddressById(order2.getCustomerAddressId());
			order2.setAddress(add);
		}*/
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		return orders;
	}
	
	@Override
	public List<Order> getAllforAdmin(int page, int limit,String startDate,String endDate,int itemId,int status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		/*if(itemId>0) {
			//od = order.joinList("orderDetails",JoinType.INNER);
		}*/
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		Date startDt = null;
		Date endDt = null;
		
		List<Predicate> conditions = new ArrayList<>();
//		conditions.add(order.get("id").in(oids));
		conditions.add(cb.equal(order.get("isCanceled"),0));
		conditions.add(cb.equal(order.get("isDeleted"),0));
		
		if(startDate.equals("0000-00-00")) {
			if(status!=0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					startDt = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//System.out.println("start date = "+startDt);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			}
		} else {
			try {
				startDt = new SimpleDateFormat("yyy-MM-dd").parse(startDate);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(endDate.equals("0000-00-00")) {
			if(status!=0) {
				Date today=new Date();
				long ltime=today.getTime()+1*24*60*60*1000;
				endDt = new Date(ltime);
				//System.out.println("end date = "+endDt);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			}
		} else {
			try {
				endDt = new SimpleDateFormat("yyy-MM-dd").parse(endDate);
				endDt = new Date(endDt.getTime()+1*24*60*60*1000);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(itemId>0) {
			conditions.add(cb.equal(od.get("itemId"),itemId));
		}
		
		if(status>=0) {
			conditions.add(cb.equal(od.get("status"),status));
		}
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		//cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		
		/*Subquery<OrderDetails> odq = cq.subquery(OrderDetails.class);
		Root od = odq.from(OrderDetails.class);
		
		odq.select(od).where(cb.equal(order.get("id"), od.get("saleOrderId")));
		cq.multiselect(order.get("customerId"),odq.getSelection());*/
		
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		
		List<Order> orders = query.getResultList();
		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);
		
		
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		return orders;
	}
	
	
	
	
	
	
	public List<Order> getAllforAgentAdmin(int page, int limit,String startDate,String endDate,int itemId,int status,List<Integer> pinCodes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		/*if(itemId>0) {
			//od = order.joinList("orderDetails",JoinType.INNER);
		}*/
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		Date startDt = null;
		Date endDt = null;
		
		List<Predicate> conditions = new ArrayList<>();
//		conditions.add(order.get("id").in(oids));
		conditions.add(cb.equal(order.get("isCanceled"),0));
		conditions.add(cb.equal(order.get("isDeleted"),0));
		
		if(startDate.equals("0000-00-00")) {
			if(status!=0) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					startDt = sdf.parse(sdf.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//System.out.println("start date = "+startDt);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			}
		} else {
			try {
				startDt = new SimpleDateFormat("yyy-MM-dd").parse(startDate);
				conditions.add(cb.greaterThanOrEqualTo(order.get("updatedDate"), startDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(endDate.equals("0000-00-00")) {
			if(status!=0) {
				Date today=new Date();
				long ltime=today.getTime()+1*24*60*60*1000;
				endDt = new Date(ltime);
				//System.out.println("end date = "+endDt);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			}
		} else {
			try {
				endDt = new SimpleDateFormat("yyy-MM-dd").parse(endDate);
				endDt = new Date(endDt.getTime()+1*24*60*60*1000);
				conditions.add(cb.lessThanOrEqualTo(order.get("updatedDate"), endDt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(itemId>0) {
			conditions.add(cb.equal(od.get("itemId"),itemId));
		}
		
		if(status>=0) {
			conditions.add(cb.equal(od.get("status"),status));
		}
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		//cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).distinct(true);//.where( cb.equal(order.get("id"),od.get("saleOrderId")));//.distinct(true);
		
		/*Subquery<OrderDetails> odq = cq.subquery(OrderDetails.class);
		Root od = odq.from(OrderDetails.class);
		
		odq.select(od).where(cb.equal(order.get("id"), od.get("saleOrderId")));
		cq.multiselect(order.get("customerId"),odq.getSelection());*/
		
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		
		List<Order> orders = query.getResultList();
		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);
		
		
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {//09-01-2020, Here "findAddressById" is called for Shipping address.....
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		
			for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
				Order order2 = (Order) iterator.next();
				for(int i=0;i<pinCodes.size();i++)
				{
					Integer pin=pinCodes.get(i);
				if(pin.equals(order2.getAddressShipping().getPincode()))
				{
					finalorders.add(order2);
				}
			}
			
		}
		return finalorders;
	}

	@Override
	public List<Order> getOrderDetailsByDeliveryTypeAndStatus(String deliveryType, int status) {//added by Sayantan Mahanty 23-12-20
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		
		List<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.equal(od.get("deliveryType"),deliveryType));
		conditions.add(cb.equal(od.get("status"),status));
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);
		Query query = em.createQuery(cq);		
		List<Order> orders = query.getResultList();		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);	
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		return orders;
	}

	@Override
	public List<Order> getOrderDetailsByDeliveryPersonId(Integer deliveryPersonId, int status) { ///Added in 9/1/2021 Sayantan Mahanty
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> order = cq.from(Order.class);
		ListJoin<Order, OrderDetails> od = order.joinList("orderDetails",JoinType.LEFT);
		ListJoin<Order, Prescription> pr = order.joinList("prescriptions",JoinType.LEFT);
		
		List<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.equal(od.get("deliveryAgentId"),deliveryPersonId));
		conditions.add(cb.equal(od.get("status"),status));
		//cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);
		cq.select(order).where(conditions.toArray(new Predicate[conditions.size()])).orderBy(cb.desc(order.get("createdDate"))).distinct(true);
		Query query = em.createQuery(cq);
		///System.err.println();
		List<Order> orders = query.getResultList();		
		CriteriaQuery<Item> cqI = cb.createQuery(Item.class);
		Root<Item> item = cqI.from(Item.class);	
		List<Order> finalorders = new ArrayList<>();
		
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				cqI.multiselect(item.get("name")).where(cb.equal( item.get("id") , orderDetails.getItemId()));
				Query queryI = em.createQuery(cqI);
				orderDetails.setItemName(((Item)queryI.getSingleResult()).getName());
			}
		}
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			for (Iterator iterator2 = order2.getOrderDetails().iterator(); iterator2.hasNext();) {
				OrderDetails orderDetails = (OrderDetails) iterator2.next();
				 Store store=storeRepository.findBySoreId(orderDetails.getStoreId());
				orderDetails.setStore(store);
			}
		}
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order2 = (Order) iterator.next();
			AddressShipping add = addressRepository.findShippingAddressByOrderId(order2.getId());
			order2.setAddressShipping(add);
		}
		
		return orders;
	}
	
}
