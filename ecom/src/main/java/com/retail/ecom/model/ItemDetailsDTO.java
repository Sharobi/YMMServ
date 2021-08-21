package com.retail.ecom.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDetailsDTO {
	private Integer id;
	private String name;
	private String code;
	private String hsnCode;
	private String sku;
	@JsonProperty("price")
	private Double price;
	private Integer groupId;
	private Integer categoryId;
	private Integer subCategoryId;
	private Integer scheduleId;
	private Integer contentId;
	private Integer brandId;
	private Integer manufacturerId;
	private String group;
	private String category;
	private String subCategory;
	private String schedule;
	private String content;
	private String brand;
	private String manufacturer;
	
	private Integer saleTaxId;
	private Double saleTaxPercentage;
	private Integer isDiscount;
	private Double discount;
	private Double maxDiscountLimit;
	private Integer taxTypeId;
	private String strength;//30-10-2019, 'strength' is added
	
	private Long totalCurrentPackQty;

	private Integer conversion;//17_10_2019
	
	private String meta_tag;///Sayantan Mahanty 9/4/2020
	
	public ItemDetailsDTO() {
		super();
	}
	public ItemDetailsDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

	public ItemDetailsDTO(Integer id, String name, String group) {
		super();
		this.id = id;
		this.name = name;
		this.group = group;
	}
	
	public ItemDetailsDTO(Integer groupId,String group, Integer categoryId, String category) {
		super();
		this.groupId = groupId;
		this.group = group;
		this.categoryId = categoryId;
		this.category = category;
	}

	//11-11-2019; This constructor is used till now in 'ItemRepository', but will not be used, if return type is Item instead of ItemDetailsDTO.
	public ItemDetailsDTO(Integer id, String name, Integer groupId, Integer conversion, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.groupId = groupId;
		this.conversion = conversion;
		this.price = price;
	}
	
	public ItemDetailsDTO(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	//just for fetching itwm quantity 
	public ItemDetailsDTO(Integer id, Long totalCurrentPackQty) {
		super();
		this.id = id;
		this.totalCurrentPackQty = totalCurrentPackQty;
	}
	
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price, Integer groupId,
			Integer categoryId, Integer subCategoryId, Integer scheduleId, Integer contentId, Integer brandId,
			Integer manufacturerId, String group, String category, String subCategory, String schedule, String content,
			String brand, String manufacturer,String meta_tag) {
		super();
		///System.err.println(price);
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.groupId = groupId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.scheduleId = scheduleId;
		this.contentId = contentId;
		this.brandId = brandId;
		this.manufacturerId = manufacturerId;
		this.group = group;
		this.category = category;
		this.subCategory = subCategory;
		this.schedule = schedule;
		this.content = content;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.meta_tag=meta_tag;
//		System.out.println(this);
	}
	
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price, Integer conversion, Integer groupId,
			Integer categoryId, Integer subCategoryId, Integer scheduleId, Integer contentId, Integer brandId,
			Integer manufacturerId,String meta_tag ,String group, String category, String subCategory, String schedule, String content,
			String brand, String manufacturer) {
		super();
//		System.out.println(price);
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.conversion = conversion;//17_10_2019 another cunstructor created due to conversion add.
		this.groupId = groupId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.scheduleId = scheduleId;
		this.contentId = contentId;
		this.brandId = brandId;
		this.manufacturerId = manufacturerId;
		this.group = group;
		this.category = category;
		this.subCategory = subCategory;
		this.schedule = schedule;
		this.content = content;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.meta_tag=meta_tag;
		
//		System.out.println(this);
	}
	
	
	//basic info for the product details page
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer groupId, Integer categoryId, Integer subCategoryId, Integer scheduleId, Integer contentId,
			Integer brandId, Integer manufacturerId, String group, String category, String subCategory, String schedule,
			String content, String brand, String manufacturer, Integer saleTaxId, Double saleTaxPercentage,
			Integer isDiscount, Double discount, Double maxDiscountLimit, Integer taxTypeId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.groupId = groupId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.scheduleId = scheduleId;
		this.contentId = contentId;
		this.brandId = brandId;
		this.manufacturerId = manufacturerId;
		this.group = group;
		this.category = category;
		this.subCategory = subCategory;
		this.schedule = schedule;
		this.content = content;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
	}

	
	//This constructor is created due to add the 'strength'
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer groupId, Integer categoryId, Integer subCategoryId, Integer scheduleId, Integer contentId,
			Integer brandId, Integer manufacturerId, String group, String category, String subCategory, String schedule,
			String content, String brand, String manufacturer, Integer saleTaxId, Double saleTaxPercentage,
			Integer isDiscount, Double discount, Double maxDiscountLimit, Integer taxTypeId, String strength,String meta_tag) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.groupId = groupId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.scheduleId = scheduleId;
		this.contentId = contentId;
		this.brandId = brandId;
		this.manufacturerId = manufacturerId;
		this.group = group;
		this.category = category;
		this.subCategory = subCategory;
		this.schedule = schedule;
		this.content = content;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
		this.strength = strength;
		this.meta_tag=meta_tag;
	}
	//with total current quantity HEAVY DUE TO THE JOIN WITH ITEM MAPPING TABLE
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer groupId, Integer categoryId, Integer subCategoryId, Integer scheduleId, Integer contentId,
			Integer brandId, Integer manufacturerId, String group, String category, String subCategory, String schedule,
			String content, String brand, String manufacturer, Integer saleTaxId, Double saleTaxPercentage,
			Integer isDiscount, Double discount, Double maxDiscountLimit, Integer taxTypeId, Long totalCurrentPackQty) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.groupId = groupId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.scheduleId = scheduleId;
		this.contentId = contentId;
		this.brandId = brandId;
		this.manufacturerId = manufacturerId;
		this.group = group;
		this.category = category;
		this.subCategory = subCategory;
		this.schedule = schedule;
		this.content = content;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
		this.totalCurrentPackQty = totalCurrentPackQty;
	}
	
	//for cart calculation
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer scheduleId, String schedule, Integer saleTaxId, Double saleTaxPercentage, Integer isDiscount,
			Double discount, Double maxDiscountLimit, Integer taxTypeId, Long totalCurrentPackQty) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.scheduleId = scheduleId;
		this.schedule = schedule;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
		this.totalCurrentPackQty = totalCurrentPackQty;
	}
	//for cart calculation
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer scheduleId, String schedule, Integer saleTaxId, Double saleTaxPercentage, Integer isDiscount,
			Double discount, Double maxDiscountLimit, Integer taxTypeId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.scheduleId = scheduleId;
		this.schedule = schedule;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
	}
	//for cart calculation
	public ItemDetailsDTO(Integer id, String name, String code, String hsnCode, String sku, Double price,
			Integer scheduleId, String schedule, Integer saleTaxId, Double saleTaxPercentage, Integer isDiscount,
			Double discount, Double maxDiscountLimit, Integer taxTypeId, Integer groupId, String group, String category,Integer categoryId ) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.hsnCode = hsnCode;
		this.sku = sku;
		this.price = price;
		this.scheduleId = scheduleId;
		this.schedule = schedule;
		this.saleTaxId = saleTaxId;
		this.saleTaxPercentage = saleTaxPercentage;
		this.isDiscount = isDiscount;
		this.discount = discount;
		this.maxDiscountLimit = maxDiscountLimit;
		this.taxTypeId = taxTypeId;
		this.groupId = groupId;//17_10_2019 another cunstructor created due to groupId add.
		this.group = group;  //07_01_2020 these two variables are added for "YOU ARE HERE" bar.....
		this.category = category;
		this.categoryId = categoryId;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getSaleTaxId() {
		return saleTaxId;
	}
	public void setSaleTaxId(Integer saleTaxId) {
		this.saleTaxId = saleTaxId;
	}
	public Double getSaleTaxPercentage() {
		return saleTaxPercentage;
	}
	public void setSaleTaxPercentage(Double saleTaxPercentage) {
		this.saleTaxPercentage = saleTaxPercentage;
	}
	public Integer getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}
	public void setMaxDiscountLimit(Double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}
	public Integer getTaxTypeId() {
		return taxTypeId;
	}
	public void setTaxTypeId(Integer taxTypeId) {
		this.taxTypeId = taxTypeId;
	}
	
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public Long getTotalCurrentPackQty() {
		return totalCurrentPackQty;
	}
	public void setTotalCurrentPackQty(Long totalCurrentPackQty) {
		this.totalCurrentPackQty = totalCurrentPackQty;
	}
	
	public Integer getConversion() {
		return conversion;
	}
	public void setConversion(Integer conversion) {
		this.conversion = conversion;
	}
	
	public String getMetaTag() {
		return meta_tag;
	}
	public void setMetaTag(String meta_tag){
		this.meta_tag=meta_tag;
	}
	@Override
	public String toString() {
		return "ItemDetailsDTO [id=" + id + ", name=" + name + ", code=" + code + ", hsnCode=" + hsnCode + ", sku="
				+ sku + ", price=" + price + ", groupId=" + groupId + ", categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", scheduleId=" + scheduleId + ", contentId=" + contentId + ", brandId=" + brandId
				+ ", manufacturerId=" + manufacturerId + ", group=" + group + ", category=" + category
				+ ", subCategory=" + subCategory + ", schedule=" + schedule + ", content=" + content + ", brand="
				+ brand + ", manufacturer=" + manufacturer + ", saleTaxId=" + saleTaxId + ", saleTaxPercentage="
				+ saleTaxPercentage + ", isDiscount=" + isDiscount + ", discount=" + discount + ", maxDiscountLimit="
				+ maxDiscountLimit + ", taxTypeId=" + taxTypeId + ", strength=" + strength + ", totalCurrentPackQty="
				+ totalCurrentPackQty + ", conversion=" + conversion + ", metaTag=" + meta_tag+ "]";
	}
	

}
