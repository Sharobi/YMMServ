package com.retail.ecom.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Path;
import com.retail.ecom.repository.PathRepositoryCustom;

@Repository
public class PathRepositoryImpl implements PathRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Path> getAllByPathGroup() {
		List<Path> pths = (List<Path>) em.createNativeQuery("SELECT p.path,group_concat(r.role) as rolestr FROM gen_m_path p , gen_m_role r where p.role_id=r.id group by p.path", "pathgroupmapping").getResultList();
		/*for(Path m : pths) {
			System.out.println("Path = "+m.getPath()+" role = "+m.getRolestr());
		}*/
		return pths;
	}

}
