package com.retail.ecom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Contacts;
import com.retail.ecom.model.Subscriber;
import com.retail.ecom.model.User;

@Repository("contactsRepository")
public interface ContactsRepository extends CrudRepository<Contacts, Long> {
	
//	 User findByEmail(String email);
	 List<Contacts> findAll();
	//Contacts findUserByUserName(String userName);

		//@Query("select f from Contacts f where f.isDeleted=0")
		@Query("select c from Contacts c where c.isDeleted=0 order by c.createdDate desc")
		public List<Contacts> getAllContacts();
		
		@Transactional
		@Modifying
		//@Query("delete from Contacts f where f.id=?1")
		@Query("update Contacts c set c.isDeleted=1 where c.id=?1")
		public void deleteContacts(int cid);

		@Query("select s from Subscriber s where s.email=?1")
		public Subscriber findSubscriberByEmail(String email);

		//@Query("insert into Subscriber s where s?1")
		//public Subscriber saveSubscriber(Subscriber subscriber);


		//@Modifying
	   // @Query(value = "insert into Subscriber (id,email,isDeleted,createdDate) VALUES (:id,:email,:isDeleted,:createdDate)", nativeQuery = true)
	   // @Transactional
	   // @Modifying
	   // @Query("insert into Subscriber (id,email,isDeleted,createdDate) VALUES (:id,:email,:isDeleted,:createdDate)")
	    //public int Subscriber(@Param("id")Long id, @Param("email")String email, @Param("isDeleted")Integer isDeleted, @Param("createdDate")String createdDate);
        
}
