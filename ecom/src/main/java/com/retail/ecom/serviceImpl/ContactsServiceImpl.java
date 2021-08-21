package com.retail.ecom.serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Contacts;
import com.retail.ecom.model.Subscriber;
import com.retail.ecom.repository.ContactsRepository;
import com.retail.ecom.service.ContactsService;
import com.retail.ecom.utils.ResponseDetails;

@Service("ContactsService")
public class ContactsServiceImpl implements ContactsService{

	@Autowired
	private ContactsRepository contactsRepository;
	
	@PersistenceContext
	EntityManager em;
	
	/*@Override
	public Contacts findUserByUserName(String userName) {
		return contactsRepository.findUserByUserName(userName);
	}*/

	@Override
	@Transactional
	public ResponseDetails saveContacts(Contacts contacts) {
		contactsRepository.save(contacts);
		return new ResponseDetails(new Date(), "Contacts save successfully", null,1);
	}

	@Override
	public List<Contacts> getAllContacts() {
		//return (List<Contacts>) contactsRepository.findAll();
		return (List<Contacts>) contactsRepository.getAllContacts();
	}

	@Override
	public ResponseDetails deleteContacts(int cid) {
		contactsRepository.deleteContacts(cid);
		return new ResponseDetails(new Date(), "Contacts deleted successfully", null, 1);
	}

	@Override
	public ResponseDetails deleteAllContacts(List<Contacts> contacts) {
			for (Iterator<Contacts> itr = contacts.iterator(); itr.hasNext();) {
				Contacts contactsForDelete = (Contacts) itr.next();
				if (contactsForDelete.getId()!=null) {

					contactsRepository.deleteContacts(contactsForDelete.getId());
					
				} else {
					return new ResponseDetails(new Date(), "Something worng with this '"+contactsForDelete.getEmail()+"' contacts", "provided id doesn't ", 1);
				}
			}
			return new ResponseDetails(new Date(), "Contacts deleted successfully", null, 1);
		}

	@Override
	public Subscriber findSubscriberByEmail(String email) {
		
		return contactsRepository.findSubscriberByEmail(email);
	}
	
	
/*
	@Override
	public Subscriber saveSubscriber(Subscriber subscriber) {
		//Subscriber subscriber = contactsRepository.saveSubscriber(subscriber);
		return contactsRepository.saveSubscriber(subscriber);
	}*/
	@Override
	public Subscriber saveSubscriber(Subscriber subscriber) {
		//Subscriber subscriber = contactsRepository.saveSubscriber(subscriber);
		Session session = (Session) em.getDelegate();
		//Session session = sessionFactory.openSession();
		try {
			//session.beginTransaction().begin();
			session.save(subscriber);
			//session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally {
			session.close();
		}
		return subscriber;//contactsRepository.saveSubscriber(subscriber);
	}
}
