package com.retail.ecom.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Store;
import com.retail.ecom.repository.StoreRepositoryCustom;

@Repository
public class StoreRepositoryImpl implements StoreRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Store> getAllBasic() {
		//CriteriaBu
		return null;
	}

}
