package com.retail.ecom.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.ecom.model.Category;
import com.retail.ecom.model.Group;
import com.retail.ecom.model.SubCategory;
import com.retail.ecom.repository.CategoryRepository;
import com.retail.ecom.repository.GroupRepository;
import com.retail.ecom.repository.SubCategoryRepository;
import com.retail.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cr;
	
	@Autowired
	private SubCategoryRepository scr;
	
	@Autowired
	private GroupRepository gr;
	
	@Override
	public Set<Category> getTree() {
		//return cr.getTree();
		Set<Category> cat = cr.getAllForTree();
		Set<SubCategory> subcat = scr.getAllForTree();
		Set<Group> groups = gr.getAllForTree();
		for (Category category : cat) {
			/*Iterator<SubCategory> subitr = subcat.iterator();
			while (subitr.hasNext()) {
				SubCategory subCategory = (SubCategory) subitr.next();
				if(subCategory.getCategoryId()==category.getId()) {
					category.getSubCategories().add(subCategory);
					subcat.remove(subCategory);
				}
			}*/
			for (SubCategory subCategory : subcat) {
				if(subCategory.getCategoryId()==category.getId()) {
					category.getSubCategories().add(subCategory);
					//subcat.remove(subCategory);
				}
			}
			for (Group group : groups) {
				if(group.getCategoryId()==category.getId()) {
					category.getGroups().add(group);
					//subcat.remove(subCategory);
				}
			}
		}
		return cat;
	}

}
