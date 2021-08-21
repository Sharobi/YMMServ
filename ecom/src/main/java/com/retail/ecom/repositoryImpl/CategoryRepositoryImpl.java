package com.retail.ecom.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Category;
import com.retail.ecom.model.SubCategory;
import com.retail.ecom.repository.CategoryRepositoryCustom;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Category> getTree() {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> category = cq.from(Category.class);
		Join<Category, SubCategory> subcategory = category.join("subCategories",JoinType.LEFT);
		//cq.multiselect(category.get("id"),category.get("name"));
		//List<Category> categories = cb.get
		cq.where(cb.notEqual(category.get("isDeleted"), 1),cb.notEqual(category.get("id"), 0),cb.notEqual(subcategory.get("isDeleted"), 1));
		Query q = em.createQuery(cq);
		return q.getResultList();
	}

}
