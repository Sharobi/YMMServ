package com.retail.ecom.model;

import java.util.List;

public class CategoryDTO {
	
	private int id;
	private String name;
	private List<ItemDetailsDTO> items;
	
	public CategoryDTO() {
		super();
	}

	public CategoryDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		
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

	public List<ItemDetailsDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDetailsDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", items=" + items + "]";
	}
	
	
	

}
