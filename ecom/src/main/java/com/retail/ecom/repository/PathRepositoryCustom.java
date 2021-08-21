package com.retail.ecom.repository;

import java.util.List;

import com.retail.ecom.model.Path;

public interface PathRepositoryCustom {
	
	List<Path> getAllByPathGroup();
}
