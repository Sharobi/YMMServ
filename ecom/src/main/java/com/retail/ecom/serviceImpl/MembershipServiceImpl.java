package com.retail.ecom.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Membership;
import com.retail.ecom.model.MembershipFeature;
import com.retail.ecom.repository.FeatureRepository;
import com.retail.ecom.repository.MembershipFeatureRepository;
import com.retail.ecom.repository.MembershipRepository;
import com.retail.ecom.service.MembershipService;
import com.retail.ecom.service.PointTrackingService;
import com.retail.ecom.utils.ResponseDetails;

@Service
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	MembershipRepository mr;
	
	@Autowired
	FeatureRepository fr;
	
	@Autowired
	MembershipFeatureRepository mfr;
	
	@Autowired
	PointTrackingService pts;
	
	@Override
	public List<Membership> getAll() {
//		return (List<Membership>) mr.findAll();
		return mr.getAllMembers();
	}

	@Override
	@Transactional
	public ResponseDetails addMembership(Membership membership) {
		//System.out.println("membership = "+membership);
		mr.save(membership);
		if(!membership.getMembershipFeatures().isEmpty()) {
			for (Iterator iterator = membership.getMembershipFeatures().iterator(); iterator.hasNext();) {
				MembershipFeature mf = (MembershipFeature) iterator.next();
				mf.setMembershipId(membership.getId());
				mfr.save(mf);
			}
		}
		return new ResponseDetails(new Date(), "Membership plan created successfully", null,1);
	}
	
	@Override
	@Transactional
	public ResponseDetails updateMembership(Membership membership) {
		//System.out.println("membership = "+membership);
		mr.save(membership);
		if(!membership.getMembershipFeatures().isEmpty()) {
			for (Iterator iterator = membership.getMembershipFeatures().iterator(); iterator.hasNext();) {
				MembershipFeature mf = (MembershipFeature) iterator.next();
				//System.out.println("MembershipFeature = "+mf);
				if(mf.getDelete()==1) {
					mfr.removeMF(mf.getId());
				}
			}
		}
		return new ResponseDetails(new Date(), "Membership plan updated successfully", null,1);
	}
	
	/*@Override
	@Transactional
	public ResponseDetails updateMembership(Membership membership) {
		Optional<Membership> um = mr.findById(membership.getId());
		System.out.println("membership = "+membership);
		if(!um.isPresent()) {
			return new ResponseDetails(new Date(), "Membership plan not found", null,0);
		}
		
		um.get().setIsActive(membership.getIsActive());
		um.get().setMembershipType(membership.getMembershipType());
		mr.save(um.get());
		mfr.deleteByMid(membership.getId());
		if(!membership.getMembershipFeatureDTOs().isEmpty()) {
			for (Iterator iterator = membership.getMembershipFeatureDTOs().iterator(); iterator.hasNext();) {
				MembershipFeatureDTO mfd = (MembershipFeatureDTO) iterator.next();
				
				Optional<Feature> f = fr.findById(mfd.getFeatureId());
				
				MembershipFeature mf = new MembershipFeature(membership, f.get());
				mf.setFactor(mfd.getFactor());
				mf.setConditionLimitAmount(mfd.getConditionLimitAmount());
				mf.setPlanAmount(mfd.getPlanAmount());
				
				mfr.save(mf);
			}
		}
		return new ResponseDetails(new Date(), "Membership plan created successfully", null,1);
	}*/

	@Override
	public List<Membership> getAllActive() {
		return mr.getAllActive();
	}

	@Override
	public ResponseDetails deactivateMembership(int mid) {
		mr.deactivateMembership(mid);
		return new ResponseDetails(new Date(), "Membership plan deactivated successfully", null,1);
	}

	@Override
	public ResponseDetails activateMembership(int mid) {
		mr.activateMembership(mid);
		return new ResponseDetails(new Date(), "Membership plan activated successfully", null,1);
	}

	@Override
	public ResponseDetails deleteMembership(int mid) {
		mr.deleteMembership(mid);
		return new ResponseDetails(new Date(), "Membership plan deleted successfully", null,1);
	}

	@Override
	public Membership getMembershipByUid(int uid) {
		Double point = pts.getPointsByUser(uid);
		if(point==null) {
			return null;
		} else {
			return mr.getMembershipByUid(point).get(0);
		}
	}
	
}
