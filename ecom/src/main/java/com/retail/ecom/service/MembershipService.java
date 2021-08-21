package com.retail.ecom.service;

import java.util.List;

import com.retail.ecom.model.Membership;
import com.retail.ecom.utils.ResponseDetails;

public interface MembershipService {

	public List<Membership> getAll();

	public ResponseDetails addMembership(Membership membership);

	public List<Membership> getAllActive();

	public ResponseDetails deactivateMembership(int mid);

	public ResponseDetails activateMembership(int mid);

	public ResponseDetails deleteMembership(int mid);

	public ResponseDetails updateMembership(Membership m);

	Membership getMembershipByUid(int uid);
}
