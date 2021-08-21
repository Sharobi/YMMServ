package com.retail.ecom.repository;

import java.util.List;

import com.retail.ecom.model.Category;

public interface CategoryRepositoryCustom {
	public List<Category> getTree();
}
