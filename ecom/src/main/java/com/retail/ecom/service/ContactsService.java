package com.retail.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Contacts;
import com.retail.ecom.model.Subscriber;
import com.retail.ecom.model.User;
import com.retail.ecom.utils.ResponseDetails;

@Service
public interface ContactsService {
//	public User findUserByEmail(String email);
	public ResponseDetails saveContacts(Contacts contacts);

	public List<Contacts> getAllContacts();

	public ResponseDetails deleteContacts(int cid);
	public ResponseDetails deleteAllContacts(List<Contacts> contacts);

	public Subscriber findSubscriberByEmail(String email);

	public Subscriber saveSubscriber(Subscriber subscriber);
}
