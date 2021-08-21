package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Path;
@Repository
public interface PathRepository extends CrudRepository<Path, Integer>,PathRepositoryCustom {
	@Query(value="select p.path_id,p.path,p.role_id,r.role as role from gen_m_path p, gen_m_role r where p.role_id=r.id",nativeQuery=true)
//	@Query("from Path")
	List<Path> findAll();
	
	@Query(value="select p.path_id,p.path,p.role_id,r.role as role from gen_m_path p, gen_m_role r where p.role_id=r.id and p.role_id"
			+ "=0",nativeQuery=true)
	//@Query("p from Path p where roleId>0")
	List<Path> findAllPermit();

	@Query(value="SELECT new Path(p.path,group_concat(r.role)) FROM ecommerce_db.gen_m_path p , ecommerce_db.gen_m_role r where p.role_id=r.id group by p.path;",nativeQuery=true)
//	@Query(value="SELECT p FROM Path p join fetch p.roles r")
	List<Path> findPathsByGroup();//Not used..........
	
	
}
