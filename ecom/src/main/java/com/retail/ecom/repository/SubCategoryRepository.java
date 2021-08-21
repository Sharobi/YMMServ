package com.retail.ecom.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.SubCategory;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {
	
	@Query("select new SubCategory(sc.id,sc.name,sc.categoryId) from SubCategory sc where sc.isDeleted!=1 and sc.id!=0")
	public Set<SubCategory> getAllForTree();
}
