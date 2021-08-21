package com.retail.ecom.model;

public class ItemBasicDTO {
	private Integer id;
	private String name;
	private String group;
	private int groupId;//14-01-2020
	private String category;
	private int categoryId;//22-01-2020
	private Double price; //17/3/2020
	private Integer conversion;//17/3/2020
	private String meta_tag;//Added by Sayantan Mahanty 9/17/20
	
	public ItemBasicDTO(Integer id, String name) {//ConstructorNo:1
		super();
		this.id = id;
		this.name = name;
	}

	public ItemBasicDTO(Integer id, String name, String group) {//ConstructorNo:3
		super();
		this.id = id;
		this.name = name;
		this.group = group;
	}//14-01-2020 this constructor is being used instead of constructorNo 3
	public ItemBasicDTO(Integer id, String name, String group, int groupId, String category, int categoryId) {//ConstructorNo:4
		super();
		this.id = id;
		this.name = name;
		this.group = group;
		this.groupId = groupId;
		this.category = category;
		this.categoryId = categoryId;
	}
	//17/3/2020 add two fields
	public ItemBasicDTO(Integer id, String name, String group, int groupId, String category, int categoryId,Double price,Integer conversion,String meta_tag) {//ConstructorNo:5
		super();
		this.id = id;
		this.name = name;
		this.group = group;
		this.groupId = groupId;
		this.category = category;
		this.categoryId = categoryId;
		this.price=price;
		this.conversion=conversion;
		this.meta_tag=meta_tag;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ItemBasicDTO() {//ConstructorNo:5
		super();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getConversion() {
		return conversion;
	}

	public void setConversion(Integer conversion) {
		this.conversion = conversion;
	}

	public String getMeta_tag() {
		return meta_tag;
	}

	public void setMeta_tag(String meta_tag) {
		this.meta_tag = meta_tag;
	}
	
	
}
