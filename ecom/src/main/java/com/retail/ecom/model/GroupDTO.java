package com.retail.ecom.model;

import java.util.List;

public class GroupDTO {
	private int id;
	private String name;
	private int categoryId;
	private String categoryName;
	private List<ItemDetailsDTO> items;
	
	
	
	public GroupDTO() {
		super();
	}
	
	public GroupDTO(int id, String name, int categoryId, String categoryName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ItemDetailsDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDetailsDTO> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", items=" + items + "]";
	}
	
	

}
