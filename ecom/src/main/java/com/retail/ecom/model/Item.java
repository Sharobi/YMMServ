package com.retail.ecom.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="in_m_item")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String code;
	@Column(name="HSN_code")
	private String hsnCode;
	@Column(name="SKU")
	private String sku;
	
	@Column(name="group_id")
	private Integer groupId;
	
	@Column(name="meta_tag")
	private String meta_tag;//added by Sayantan Mahanty 9/4/2020

	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="sub_category_id")
	private Integer subCategoryId;

	@Column(name="schedule_id")
	private Integer scheduleId;

	@Column(name="content_id")
	private Integer contentId;

	@Column(name="brand_id")
	private Integer brandId;

	@Column(name="manufacturer_id")
	private Integer manufacturerId;

	private Date entryDate;

	private Double vat;

	private Integer isOnMrp;
	
	private Integer purchaseTaxId;
	private Double purchaseTaxPercentage;
	
	@Column(name="sale_tax_id")
	private Integer saleTaxId;
	
	private Double saleTaxPercentage;

	private Integer packUnitId;

	private Integer conversion;

	private Integer looseUnitId;

	private String storage;

	private String care;

	private Integer reorderLevel;

	private Integer reorderLevelUnitId;

	private Double price;

	private Integer isTaxable;

	private Double markup;
	private Integer isDiscount;
	private Double discount;
	private Double maxDiscountLimit;
	private String strength;
	private String netContent;
	private String note;
	@Transient
	private String description;
	@Transient
	private String printName;
	@Transient
	private String imgUrl;
	@Transient
	private Integer maxLevel;
	@Transient
	private Integer minLevel;
	@Transient
	private Integer isActive;
	@Transient
	private String colour;
	@Transient
	private String sizeType;
	@Transient
	private String size;
	@Transient
	private String weight;
	@Transient
	private String partNumber;
	@Transient
	private String authorName;
	@Transient
	private String edition;
	@Transient
	private Double mrp;
	@Transient
	private Double purchaseRate;
	@Transient
	private Double listedMrp;
	@Transient
	private Double wsp;

	private Integer companyId;

	private Integer isDeleted;

	private Integer createdBy;
	//@Transient
	private Integer taxTypeId;
	
	@Column(name="is_banned")
	private Integer isBanned;
	
	@Column(name="is_discontinue")
	private Integer isDiscontinue;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Transient
	@Column(nullable=false)
	private Date createdDate;
	@Transient
	private Integer updatedBy;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	@Transient
	private Date updatedDate;
	
	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="group_id",insertable=false,updatable=false)
	private Group group;
	
	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="brand_id",insertable=false,updatable=false)
	private Brand brand;
	
	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	private Category category;

	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="content_id",insertable=false,updatable=false)
	private Content content;

	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="manufacturer_id",insertable=false,updatable=false)
	private Manufacturer manufacturer;

	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="schedule_id",insertable=false,updatable=false)
	private Schedule schedule;

	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="sub_category_id",insertable=false,updatable=false)
	private SubCategory subCategory;
	
	@OneToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="sale_tax_id",insertable=false,updatable=false)
	private Tax tax;
	
	@OneToMany(mappedBy="item",fetch=FetchType.LAZY)
	private List<ItemMapping> itemMappings;
	
	public Item() {
		super();
	}
	
	public Item(String name) {
		super();
		this.name = name;
	}

	public Item(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Item(Integer id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
	//11-11-2019; This constructor isn't used till now in 'ItemRepository', but will be used, if return type is Item instead of ItemDetailsDTO.
	public Item(Integer id, String name, Integer groupId, Integer conversion, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.groupId = groupId;
		this.conversion = conversion;
		this.price = price;
	}
	
	
	public Integer getId() {
		return id;
	}
	

	public String getMeta_tag() {
		return meta_tag;
	}

	public void setMeta_tag(String meta_tag) {
		this.meta_tag = meta_tag;
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Integer getIsOnMrp() {
		return isOnMrp;
	}
	public void setIsOnMrp(Integer isOnMrp) {
		this.isOnMrp = isOnMrp;
	}
	public Integer getPurchaseTaxId() {
		return purchaseTaxId;
	}
	public void setPurchaseTaxId(Integer purchaseTaxId) {
		this.purchaseTaxId = purchaseTaxId;
	}
	public Double getPurchaseTaxPercentage() {
		return purchaseTaxPercentage;
	}
	public void setPurchaseTaxPercentage(Double purchaseTaxPercentage) {
		this.purchaseTaxPercentage = purchaseTaxPercentage;
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
	public Integer getPackUnitId() {
		return packUnitId;
	}
	public void setPackUnitId(Integer packUnitId) {
		this.packUnitId = packUnitId;
	}
	public Integer getConversion() {
		return conversion;
	}
	public void setConversion(Integer conversion) {
		this.conversion = conversion;
	}
	public Integer getLooseUnitId() {
		return looseUnitId;
	}
	public void setLooseUnitId(Integer looseUnitId) {
		this.looseUnitId = looseUnitId;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getCare() {
		return care;
	}
	public void setCare(String care) {
		this.care = care;
	}
	public Integer getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public Integer getReorderLevelUnitId() {
		return reorderLevelUnitId;
	}
	public void setReorderLevelUnitId(Integer reorderLevelUnitId) {
		this.reorderLevelUnitId = reorderLevelUnitId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getIsTaxable() {
		return isTaxable;
	}
	public void setIsTaxable(Integer isTaxable) {
		this.isTaxable = isTaxable;
	}
	public Double getMarkup() {
		return markup;
	}
	public void setMarkup(Double markup) {
		this.markup = markup;
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
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getNetContent() {
		return netContent;
	}
	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}
	public Integer getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	public Double getListedMrp() {
		return listedMrp;
	}
	public void setListedMrp(Double listedMrp) {
		this.listedMrp = listedMrp;
	}
	public Double getWsp() {
		return wsp;
	}
	public void setWsp(Double wsp) {
		this.wsp = wsp;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Integer getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Integer isBanned) {
		this.isBanned = isBanned;
	}

	public Integer getIsDiscontinue() {
		return isDiscontinue;
	}

	public void setIsDiscontinue(Integer isDiscontinue) {
		this.isDiscontinue = isDiscontinue;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	public Integer getTaxTypeId() {
		return taxTypeId;
	}
	public void setTaxTypeId(Integer taxTypeId) {
		this.taxTypeId = taxTypeId;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	public List<ItemMapping> getItemMappings() {
		return itemMappings;
	}
	public void setItemMappings(List<ItemMapping> itemMappings) {
		this.itemMappings = itemMappings;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", code=" + code + ", hsnCode=" + hsnCode + ", sku=" + sku
				+ ", groupId=" + groupId + ", meta_tag=" + meta_tag + ", categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", scheduleId=" + scheduleId + ", contentId=" + contentId + ", brandId=" + brandId
				+ ", manufacturerId=" + manufacturerId + ", entryDate=" + entryDate + ", vat=" + vat + ", isOnMrp="
				+ isOnMrp + ", purchaseTaxId=" + purchaseTaxId + ", purchaseTaxPercentage=" + purchaseTaxPercentage
				+ ", saleTaxId=" + saleTaxId + ", saleTaxPercentage=" + saleTaxPercentage + ", packUnitId=" + packUnitId
				+ ", conversion=" + conversion + ", looseUnitId=" + looseUnitId + ", storage=" + storage + ", care="
				+ care + ", reorderLevel=" + reorderLevel + ", reorderLevelUnitId=" + reorderLevelUnitId + ", price="
				+ price + ", isTaxable=" + isTaxable + ", markup=" + markup + ", isDiscount=" + isDiscount
				+ ", discount=" + discount + ", maxDiscountLimit=" + maxDiscountLimit + ", strength=" + strength
				+ ", netContent=" + netContent + ", note=" + note + ", description=" + description + ", printName="
				+ printName + ", imgUrl=" + imgUrl + ", maxLevel=" + maxLevel + ", minLevel=" + minLevel + ", isActive="
				+ isActive + ", colour=" + colour + ", sizeType=" + sizeType + ", size=" + size + ", weight=" + weight
				+ ", partNumber=" + partNumber + ", authorName=" + authorName + ", edition=" + edition + ", mrp=" + mrp
				+ ", purchaseRate=" + purchaseRate + ", listedMrp=" + listedMrp + ", wsp=" + wsp + ", companyId="
				+ companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", taxTypeId=" + taxTypeId
				+ ", isBanned=" + isBanned + ", isDiscontinue=" + isDiscontinue + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", group=" + group + ", brand=" + brand
				+ ", category=" + category + ", content=" + content + ", manufacturer=" + manufacturer + ", schedule="
				+ schedule + ", subCategory=" + subCategory + ", tax=" + tax + ", itemMappings=" + itemMappings + "]";
	}

}
