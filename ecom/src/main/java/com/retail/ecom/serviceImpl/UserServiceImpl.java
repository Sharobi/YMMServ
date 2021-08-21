package com.retail.ecom.serviceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Address;
import com.retail.ecom.model.Path;
import com.retail.ecom.model.Role;
import com.retail.ecom.model.User;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.PathRepository;
import com.retail.ecom.repository.RoleRepository;
import com.retail.ecom.repository.UserRepository;
import com.retail.ecom.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private PathRepository pathRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private AddressRepository ar;
	
	/*@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}*/
	
	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
//		System.out.println("Service user = "+user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        
        userRepository.save(user);
       // System.err.println(user.getId());
        if(user.getAddresses()!=null && user.getAddresses().size()>0) {
        	for (Iterator iterator = user.getAddresses().iterator(); iterator.hasNext();) {
				Address address = (Address) iterator.next();
//				address.setUser(null);
				address.setUserId(user.getId());
				ar.save(address);
			}
        }
	}
	
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<Path> findPaths() {
		// TODO Auto-generated method stub
		return pathRepository.findAll();
	}
	
	@Override
	public List<Path> findPathsByGroup() {
//		return pathRepository.findPathsByGroup();
		return pathRepository.getAllByPathGroup();
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	@Override
	public List<Path> getAllPermits() {
		// TODO Auto-generated method stub
		return pathRepository.findAllPermit();
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByResetToken(String resetToken) {
		// TODO Auto-generated method stub
		return userRepository.findByResetToken(resetToken);
	}

}
