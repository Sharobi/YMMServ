package com.retail.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}
