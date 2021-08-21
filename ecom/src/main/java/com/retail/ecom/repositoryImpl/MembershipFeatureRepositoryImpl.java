package com.retail.ecom.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.repository.MembershipFeatureRepositoryCustom;

public class MembershipFeatureRepositoryImpl implements MembershipFeatureRepositoryCustom {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean checkValidFeatures(Integer mid, Integer[] membershipFeatureIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MembershipFeature> cq = cb.createQuery(MembershipFeature.class);
		
		Root<MembershipFeature> from = cq.from(MembershipFeature.class);
		TypedQuery<MembershipFeature> q = em.createQuery(cq);
		cb.equal(from.get("membershipId"),mid);//from.get("id").in(membershipFeatureIds);
		
		cq.select(from).where(cb.equal(from.get("membershipId"),mid),from.get("featureId").in(membershipFeatureIds));
		
		List<MembershipFeature> membershipFeatures = q.getResultList();
		
		if(membershipFeatures!=null) {
			//System.out.println("membershipFeatures = "+membershipFeatures);
			if(membershipFeatures.size() == membershipFeatureIds.length) {
				return true;
			}
		}
		return false;
	}

}
