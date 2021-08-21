package com.retail.ecom.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>,CategoryRepositoryCustom {
	
//	@Query("select c.id,c.name,sc.id,sc.name from Category c left outer join c.subCategories sc where c.id!=0")
	@Query("select c from Category c left outer join c.subCategories sc where c.id!=0")
	public List<Category> getTree1();
	
	@Query("select new Category(c.id,c.name) from Category c where c.isDeleted!=1 and c.id!=0")
	public Set<Category> getAllForTree();
}
