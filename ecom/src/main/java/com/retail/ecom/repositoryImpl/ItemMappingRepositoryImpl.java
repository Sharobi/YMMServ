package com.retail.ecom.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.retail.ecom.repository.ItemMappingRepositoryCustom;

@Repository
public class ItemMappingRepositoryImpl implements ItemMappingRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	@Override
	public void synccsv() {
		Query q = em.createNativeQuery("LOAD DATA LOCAL INFILE 'E:/1_2.csv' INTO TABLE csv_items_stock FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n' (@company_id, @store_id, @item_id,@pack_qty)" + 
				" SET" + 
				" company_id = @company_id," + 
				" store_id = @store_id," + 
				" item_id = @item_id," + 
				" pack_qty = @pack_qty" + 
				" ;");
		q.executeUpdate();
		
		q = em.createNativeQuery("UPDATE in_t_item_mapping a " + 
				"JOIN csv_items_stock b ON a.local_item_id=b.item_id  and a.company_id = b.company_id and a.store_id=b.store_id  " + 
				"LEFT OUTER JOIN " + 
				"(select company_id,store_id,item_id,sum(pack_qty) pack_qty " + 
				"from sal_t_sale_order_details " + 
				"where status=1 " + 
				"group by company_id,store_id,item_id" + 
				") so ON so.company_id = b.company_id and so.store_id = b.store_id and so.item_id = b.item_id " +
				"SET a.current_pack_qty = b.pack_qty - ifnull(so.pack_qty,0) " + 
				";" ); 
		
		q.executeUpdate();
		
		q = em.createNativeQuery("truncate table csv_items_stock");
		
		q.executeUpdate();
	}
}
