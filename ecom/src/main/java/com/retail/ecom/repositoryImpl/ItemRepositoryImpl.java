package com.retail.ecom.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Brand;
import com.retail.ecom.model.Category;
import com.retail.ecom.model.Content;
import com.retail.ecom.model.Group;
import com.retail.ecom.model.GroupDTO;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.ItemMapping;
import com.retail.ecom.model.Manufacturer;
import com.retail.ecom.model.Schedule;
import com.retail.ecom.model.Store;
import com.retail.ecom.model.SubCategory;
import com.retail.ecom.model.Tax;
import com.retail.ecom.repository.ItemRepositoryCustom;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Item getById(int id) {
		return em.find(Item.class, id);
	}
	public Item getById2(int id) {
		
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		
		TypedQuery<Item> query = em.createQuery("select i from Item i where i.id=:id",Item.class);
		
		query.setParameter("id", id);
		return query.getSingleResult();
		//return em.find(Item.class, id);
	}
	
	
	public Item getById1(int id) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> from = cq.from(Item.class);
		Join<Group, Item> group = from.join("group",JoinType.LEFT);
		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return (Item)query.getSingleResult();
		//return em.find(Item.class, id);
	}
	
	@Override
	public ItemDetailsDTO getDetailsById(int id) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		
		Join<Tax, Item> tax = root.join("tax",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						group.get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"),
						root.get("saleTaxId"),
//						root.get("saleTaxPercentage"),
						tax.get("percentage"),
						root.get("isDiscount"),
						root.get("discount"),
						root.get("maxDiscountLimit"),
						root.get("taxTypeId"),
						root.get("strength"),//30-10-2019, 'strength' added
						root.get("meta_tag"))//Added by Sayantan Mahanty 9/4/2020
						
				).where(cb.equal(root.get("id"), id));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return (ItemDetailsDTO)query.getSingleResult();
		//return em.find(Item.class, id);
	}
	@Override
	//public List<ItemDetailsDTO> getDetailsByContentId(int id, Integer page, Integer limit) {
	public List<ItemDetailsDTO> getDetailsByContentId(int id, String strength,Integer groupId, Integer page, Integer limit) {
		Item item;
		//System.out.println("id: "+id+", strength: "+strength+", groupId: "+groupId);
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"),
						root.get("meta_tag"))
				//).where(cb.equal(content.get("id"), id));//30-10-2019 changed by Mubarak
	            ).where(cb.equal(content.get("id"), id),
			            cb.equal(root.get("strength"), strength),
			            //cb.equal(root.get("groupId"), groupId)).groupBy(root.get("price"));
		                cb.equal(root.get("groupId"), groupId),
		                cb.greaterThan(root.get("price"), 1));//minimum price is 1.00 rps
                        cq.orderBy(cb.asc(root.get("price")));
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	@Override
	public List<ItemDetailsDTO> getDetailsByGroupId(int id) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"))
				).where(cb.equal(group.get("id"), id));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);//.setFirstResult(0).setMaxResults(10);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	@Override
	public List<ItemDetailsDTO> getDetailsByGroupIdPaged(int id, Integer page, Integer limit) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("conversion"),//17_10_2019
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("meta_tag"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"))
				).where(cb.equal(group.get("id"), id),
		                cb.greaterThan(root.get("price"), 1));//minimum price is 1.00 rps); 04-11-2019
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	@Override
	public GroupDTO getDetailsByGroupIdPagedNew(int id, Integer page, Integer limit) {///Added By Sayantan Mahanty 24/9/2020
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		CriteriaQuery<GroupDTO> cq2 = cb.createQuery(GroupDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("conversion"),//17_10_2019
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("meta_tag"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"))
				).where(cb.equal(group.get("id"), id),
		                cb.greaterThan(root.get("price"), 1));//minimum price is 1.00 rps); 04-11-2019
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		
		/*
		 * cq2.select( cb.construct(GroupDTO.class, group.get("id"),
		 * category.get("categoryId"), group.get("name"), category.get("name") )
		 * ).where(cb.equal(group.get("id"), id) );
		 */
		//Query query2 = em.createQuery(cq2).setFirstResult((page-1)*limit).setMaxResults(limit);
		//System.err.println(id);
		Query query2=em.createQuery("select new com.retail.ecom.model.GroupDTO(g.id,g.name,g.categoryId,c.name) from Group g , Category c where g.id=:id and g.categoryId=c.id");
		query2.setParameter("id", id);
		GroupDTO grp=(GroupDTO)query2.getSingleResult();
		grp.setItems(query.getResultList());
		return grp;
		
	}
	
	@Override
	public List<ItemDetailsDTO> getDetailsByCategoryIdPaged(int id, Integer page, Integer limit) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"))
				).where(cb.equal(category.get("id"), id));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	@Override
	public List<ItemDetailsDTO> getDetailsBySubCategoryIdPaged(int id, Integer page, Integer limit) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.INNER);
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("groupId"),
						root.get("categoryId"),
						root.get("subCategoryId"),
						root.get("scheduleId"),
						root.get("contentId"),
						root.get("brandId"),
						root.get("manufacturerId"),
						root.get("group").get("name"),
						category.get("name"),
						subCategory.get("name"),
						schedule.get("name"),
						content.get("name"),
						brand.get("name"),
						manufacturer.get("name"))
				).where(cb.equal(subCategory.get("id"), id));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	@Override
	public ItemDetailsDTO getDetailsByIdRadius(int id, List<Integer> storeIds) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		
		Join<Tax, Item> tax = root.join("tax",JoinType.LEFT);
		
		Join<ItemMapping, Item> im = root.join("itemMappings",JoinType.LEFT);
		
//		Join<Store, Item> store = root.join("store");
		
		Expression<String> parentExpression = im.get("storeId");
		Predicate storePredicate = parentExpression.in(storeIds);
		
		if(storeIds.size()>0) {
			cq.select(
					cb.construct(ItemDetailsDTO.class,
							root.get("id"),
							root.get("name"),
							root.get("code"),
							root.get("hsnCode"),
							root.get("sku"),
							root.get("price"),
							root.get("groupId"),
							root.get("categoryId"),
							root.get("subCategoryId"),
							root.get("scheduleId"),
							root.get("contentId"),
							root.get("brandId"),
							root.get("manufacturerId"),
							group.get("name"),
							category.get("name"),
							subCategory.get("name"),
							schedule.get("name"),
							content.get("name"),
							brand.get("name"),
							manufacturer.get("name"),
							root.get("saleTaxId"),
	//						root.get("saleTaxPercentage"),
							tax.get("percentage"),
							root.get("isDiscount"),
							root.get("discount"),
							root.get("maxDiscountLimit"),
							root.get("taxTypeId"),
							cb.sum(im.get("currentPackQty")))
					).where(cb.equal(root.get("id"), id),storePredicate);
		} else {
			cq.select(
					cb.construct(ItemDetailsDTO.class,
							root.get("id"),
							root.get("name"),
							root.get("code"),
							root.get("hsnCode"),
							root.get("sku"),
							root.get("price"),
							root.get("groupId"),
							root.get("categoryId"),
							root.get("subCategoryId"),
							root.get("scheduleId"),
							root.get("contentId"),
							root.get("brandId"),
							root.get("manufacturerId"),
							group.get("name"),
							category.get("name"),
							subCategory.get("name"),
							schedule.get("name"),
							content.get("name"),
							brand.get("name"),
							manufacturer.get("name"),
							root.get("saleTaxId"),
	//						root.get("saleTaxPercentage"),
							tax.get("percentage"),
							root.get("isDiscount"),
							root.get("discount"),
							root.get("maxDiscountLimit"),
							root.get("taxTypeId"))
					).where(cb.equal(root.get("id"), id)).distinct(true);
		}
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return (ItemDetailsDTO)query.getSingleResult();
		//return em.find(Item.class, id);
	}
	
	
	@Override
	public List<ItemDetailsDTO> getAllDetailsByIdRadius(List<Integer> ids, List<Integer> storeIds) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		/*Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Brand, Item> brand = root.join("brand",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		Join<Content, Item> content = root.join("content",JoinType.LEFT);
		Join<Manufacturer, Item> manufacturer = root.join("manufacturer",JoinType.LEFT);*/
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		//Join<SubCategory, Item> subCategory = root.join("subCategory",JoinType.LEFT);
		
		Join<Tax, Item> tax = root.join("tax",JoinType.LEFT);
		
		Join<ItemMapping, Item> im = root.join("itemMappings",JoinType.LEFT);
		
//		Join<Store, Item> store = root.join("store");
		
		Expression<String> storeExpression = im.get("storeId");
		Predicate storePredicate = storeExpression.in(storeIds);
		
		Expression<String> itemExpression = im.get("globalItemId");
		Predicate itemPredicate = itemExpression.in(ids);
		
		if(storeIds.size()>0) {
		cq.select(
				cb.construct(ItemDetailsDTO.class,
						root.get("id"),
						root.get("name"),
						root.get("code"),
						root.get("hsnCode"),
						root.get("sku"),
						root.get("price"),
						root.get("scheduleId"),
						schedule.get("name"),
						root.get("saleTaxId"),
						tax.get("percentage"),
						root.get("isDiscount"),
						root.get("discount"),
						root.get("maxDiscountLimit"),
						root.get("taxTypeId"),
						cb.sum(im.get("currentPackQty")))
				).where(itemPredicate,storePredicate).groupBy(root.get("id"));
		} else {
			cq.select(
					cb.construct(ItemDetailsDTO.class,
							root.get("id"),
							root.get("name"),
							root.get("code"),
							root.get("hsnCode"),
							root.get("sku"),
							root.get("price"),
							root.get("scheduleId"),
							schedule.get("name"),
							root.get("saleTaxId"),
							tax.get("percentage"),
							root.get("isDiscount"),
							root.get("discount"),
							root.get("maxDiscountLimit"),
							root.get("taxTypeId"))
					).where(itemPredicate).groupBy(root.get("id"));
		}
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}

	@Override
	public List<ItemDetailsDTO> getAllDetailsByIds(List<Integer> ids) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		Join<Schedule, Item> schedule = root.join("schedule",JoinType.LEFT);
		Join<Group, Item> group = root.join("group",JoinType.LEFT);
		Join<Category, Item> category = root.join("category",JoinType.LEFT);
		
		Join<Tax, Item> tax = root.join("tax",JoinType.LEFT);
		
		Expression<String> itemExpression = root.get("id");
		Predicate itemPredicate = itemExpression.in(ids);
		
		cq.select(
			cb.construct(ItemDetailsDTO.class,
					root.get("id"),
					root.get("name"),
					root.get("code"),
					root.get("hsnCode"),
					root.get("sku"),
					root.get("price"),
					root.get("scheduleId"),
					schedule.get("name"),
					root.get("saleTaxId"),
					tax.get("percentage"),
					root.get("isDiscount"),
					root.get("discount"),
					root.get("maxDiscountLimit"),
					root.get("taxTypeId"),
					root.get("groupId"), //17_10_2019
					group.get("name"),//07_01_2020
					category.get("name"),//07_01_2020
					category.get("id"))//22_01_2020
			).where(itemPredicate).groupBy(root.get("id"));
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		//Query query = em.createQuery(cq);// By Jishnu
		Query query = em.createQuery(cq).setMaxResults(20);//15-11-2019 By Mubarak
		//Query query = em.createQuery(cq).setFirstResult((page-1)*limit).setMaxResults(limit);//Sending result pages wise......
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	@Override
	public List<ItemDetailsDTO> getItemQtyInRadius(List<Integer> ids, List<Integer> storeIds) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemDetailsDTO> cq = cb.createQuery(ItemDetailsDTO.class);
		Root<Item> root = cq.from(Item.class);
		
		Join<ItemMapping, Item> im = root.join("itemMappings",JoinType.LEFT);
		
		Expression<String> storeExpression = im.get("storeId");
		Predicate storePredicate = storeExpression.in(storeIds);
		
		Expression<String> itemExpression = im.get("globalItemId");
		Predicate itemPredicate = itemExpression.in(ids);
		
		if(storeIds.size()>0) {
			cq.select(
					cb.construct(ItemDetailsDTO.class,
							root.get("id"),
							cb.sum(im.get("currentPackQty")))
					).where(itemPredicate,storePredicate).groupBy(root.get("id"));
		} else {
			cq.select(
					cb.construct(ItemDetailsDTO.class,
							root.get("id"),
							root.get("name"))
					).where(itemPredicate).groupBy(root.get("id"));
		}
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	
	@Override
	public List<Integer> getEligibleStoreIdsByIdRadius(int id, List<Integer> storeIds,double packQty) {
		Item item;
//		Query query = em.createQuery("select new com.retail.ecom.models.Item(i.id,i.name) from Item i where i.id=:id");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<Item> root = cq.from(Item.class);
		Join<ItemMapping, Item> im = root.join("itemMappings",JoinType.LEFT);
		
//		Join<Store, Item> store = root.join("store");
		
		Expression<String> parentExpression = im.get("storeId");
		Predicate storePredicate = parentExpression.in(storeIds);
		
		if(storeIds.size()>0) {
			cq.select(im.get("storeId")).where(cb.equal(root.get("id"), id), cb.greaterThanOrEqualTo(im.get("currentPackQty"),packQty), storePredicate );
			
		} else {
			return null;
		}
		
//		cq.multiselect(from.get("id"),from.get("name"),group.get("name")).where(cb.equal(from.get("id"), id));
		Query query = em.createQuery(cq);
		//TypedQuery<Item> query = em.createQuery(cq,Item.class);
		
		return query.getResultList();
		//return em.find(Item.class, id);
	}
	
	
}
