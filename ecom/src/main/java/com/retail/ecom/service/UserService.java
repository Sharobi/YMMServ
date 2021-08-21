package com.retail.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.retail.ecom.model.Path;
import com.retail.ecom.model.User;

@Service
public interface UserService {
//	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<Path> findPaths();
	public List<User> getAll();
	public List<Path> getAllPermits();
	public User updateUser(User user);
	List<Path> findPathsByGroup();
	User findUserByUserName(String userName);


	public User findUserByEmail(String email);//11-10-2019
	public User findUserByResetToken(String resetToken);
    //public Optional<User> findUserByResetToken(String resetToken);
   // public void save(User user);
}
