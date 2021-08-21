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

import com.retail.ecom.model.Feature;
import com.retail.ecom.model.Membership;
import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.repository.MembershipRepositoryCustom;

@Repository
public class MembershipRepositoryImpl implements MembershipRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Membership> getAllMembers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Membership> cq = cb.createQuery(Membership.class);
		Root<Membership> mem = cq.from(Membership.class);
		Join<Membership, MembershipFeature> mmf = mem.join("membershipFeatures",JoinType.LEFT);
		Join<MembershipFeature, Feature> mff = mmf.join("feature",JoinType.INNER);
		cq.select(mem).distinct(true);
		
		Query q = em.createQuery(cq);
		return q.getResultList();
	}

}
