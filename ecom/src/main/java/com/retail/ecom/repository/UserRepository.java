package com.retail.ecom.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	
//	 User findByEmail(String email);
	 List<User> findAll();
	User findUserByUserName(String userName);
	
	
	User findByEmail(String email);//11-10-2019
	User findByResetToken(String resetToken);
	//Optional<User> findByEmail(String email);
	//Optional<User> findByResetToken(String resetToken);
}
