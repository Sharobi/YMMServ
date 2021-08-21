package com.retail.ecom.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Category;
import com.retail.ecom.model.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {
	
	@Query("select new com.retail.ecom.model.Group(g.id,g.name) from Group g where g.isDeleted!=1 and g.id>0")
	public List<Group> getAll();
	
	@Query("select new com.retail.ecom.model.Group(g.id,g.name) from Group g where g.isDeleted!=1 and g.id!=0")
	//public Set<Group> getAllForTree();
	public Set<Group> getAllForTree1();//By Jishnu
	@Query("select new com.retail.ecom.model.Group(g.id,g.name,g.categoryId) from Group g where g.isDeleted!=1 and g.id!=0")
	public Set<Group> getAllForTree();//By Mubarak
}
