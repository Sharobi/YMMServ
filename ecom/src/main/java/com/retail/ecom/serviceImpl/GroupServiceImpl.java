package com.retail.ecom.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Group;
import com.retail.ecom.repository.GroupRepository;
import com.retail.ecom.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	GroupRepository gr;
	
	@Override
	public List<Group> getAll() {
		return gr.getAll();
	}
	//01-10-2019
	public Set<Group> getAllForTree(){
		return gr.getAllForTree();
	}
}
