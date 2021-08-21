package com.retail.ecom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retail.ecom.model.CategoryDTO;
import com.retail.ecom.model.GroupDTO;
import com.retail.ecom.model.Item;
import com.retail.ecom.model.ItemBasicDTO;
import com.retail.ecom.model.ItemDetailsDTO;
import com.retail.ecom.model.Order;
import com.retail.ecom.model.SideEffectDetails;
import com.retail.ecom.model.SideEffectDetailsDTO;
import com.retail.ecom.service.ItemMappingService;
import com.retail.ecom.service.ItemService;
import com.retail.ecom.utils.ResponseDetails;

@Controller
@RequestMapping(value="/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemMappingService ims;
	
	@GetMapping
	public @ResponseBody List<Item> getAll() {
		return itemService.getAll();
	}
	
	@GetMapping("/basic")
	public @ResponseBody List<Item> getAllBasic() {
		return itemService.getAllBasic();
	}
	
	@GetMapping("/searchbasic")//This service fetched all matching items.......
	public @ResponseBody List<Item> getSearchBasic(@PathParam(value="name") String name) {
		//System.err.println("name: "+name);
		return itemService.getSearchBasic(name);
	}
	@GetMapping("/searchbasic1")//24-10-2019 For available items only.........
	public @ResponseBody List<Item> getSearchBasic1(@PathParam(value="name") String name) {

		return itemService.findAllAvailAbleItems(name);
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Item getById(@PathVariable(value="id") int id) {
		return itemService.getById(id);
//		return itemService.getDetailsById(id);
	}
	
	@GetMapping("/details/{id}")
	public @ResponseBody ItemDetailsDTO getDetailsById(@PathVariable(value="id") int id) {
		return itemService.getDetailsById(id);
	}
	
	@GetMapping("/details/content/{id}")
	public @ResponseBody List<ItemDetailsDTO> getDetailsByContentId(@PathVariable(value="id") int id, @RequestParam(value="strength") String strength,
			@RequestParam(value="groupId") Integer groupId, @RequestParam(value="page",defaultValue="1",required=false)Integer page,@RequestParam(value="limit",defaultValue="30",required=false)Integer limit) {
		return itemService.getDetailsByContentId(id,strength,groupId,page,limit);//Modify("strength" is added)30-10-2019
	}
	@GetMapping("/sideeffect/content/{id}")
	public @ResponseBody List<SideEffectDetailsDTO> getSideEffectByItemsId(@PathVariable(value="id") int id) {
		return itemService.getSideEffectByItemsId(id);
	}
	
	@GetMapping("/details/group/{id}")
	public @ResponseBody List<ItemDetailsDTO> getDetailsByGroupId(@PathVariable(value="id") int id
			,@RequestParam(value="page"/*,defaultValue="1",required=false*/)Integer page,@RequestParam(value="limit",defaultValue="10",required=false)Integer limit) {
//		return itemService.getDetailsByGroupId(id);
		return itemService.getDetailsByGroupIdPaged(id,page,limit);
		//return itemService.getDetailsByGroupIdPagedNew(id,page,limit);

	}
	@GetMapping("/details/groupNew/{id}")
	public @ResponseBody GroupDTO getDetailsByGroupIdNew(@PathVariable(value="id") int id
			,@RequestParam(value="page"/*,defaultValue="1",required=false*/)Integer page,@RequestParam(value="limit",defaultValue="10",required=false)Integer limit) {
//		return itemService.getDetailsByGroupId(id);
		//return itemService.getDetailsByGroupIdPaged(id,page,limit);
		return itemService.getDetailsByGroupIdPagedNew(id,page,limit);

	}
	
	@GetMapping("/details/category/{id}")
	public @ResponseBody List<ItemDetailsDTO> getDetailsByCategoryId(@PathVariable(value="id") int id
			,@RequestParam(value="page"/*,defaultValue="1",required=false*/)Integer page,@RequestParam(value="limit",defaultValue="10",required=false)Integer limit) {
//		return itemService.getDetailsByGroupId(id);
		return itemService.getDetailsByCategoryIdPaged(id,page,limit);
	}
	
	@GetMapping("/details/subcategory/{id}")
	public @ResponseBody List<ItemDetailsDTO> getDetailsBySubCategoryId(@PathVariable(value="id") int id
			,@RequestParam(value="page"/*,defaultValue="1",required=false*/)Integer page,@RequestParam(value="limit",defaultValue="10",required=false)Integer limit) {
//		return itemService.getDetailsByGroupId(id);
		return itemService.getDetailsBySubCategoryIdPaged(id,page,limit);
	}
	
	@GetMapping("/count/subcategory/{id}")
	public @ResponseBody ResponseDetails totalItemsBySubCategory(@PathVariable(value="id") int id) {
		return new ResponseDetails(new Date(), "Check status for Total Count", "/count/subcategory/", itemService.totalItemsBySubCategory(id));
	}
	
	@GetMapping("/checkdeliverable")
	public @ResponseBody ResponseDetails checkdeliverable(@PathParam(value="id") int id,@PathParam(value="lat") double lat,@PathParam(value="lng") double lng) {
		//System.out.println("id: "+id+", lat: "+lat+", lng: "+lng);
		return ims.checkDeliverable(id, lat, lng);
	}
	
	@GetMapping("/checkdeliverableByPin")
	public @ResponseBody ResponseDetails checkdeliverableByPin(@PathParam(value="id") int id,@PathParam(value="pinCode") int pinCode) {
		//System.out.println("id: "+id+", pinCode: "+pinCode);
		return ims.checkdeliverableByPin(id, pinCode);
	}
	
	@GetMapping("/checkiteminradius")
	public @ResponseBody ItemDetailsDTO checkItemInRadius(@PathParam(value="id") int id,@PathParam(value="lat") double lat,@PathParam(value="lng") double lng) {
		return itemService.getDetailsByIdRadius(id, lat, lng);
	}
	
	/*@GetMapping("/cartitemsdetails1")
	public @ResponseBody List<ItemDetailsDTO> checkItemInRadius(@RequestParam(value="itemIds") List<Integer> ids,@RequestParam(value="lat") double lat,@RequestParam(value="lng") double lng) {
		System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getCartItemsDetails(ids, lat, lng);
	}*/
	@GetMapping("/cartitemsdetails")//03-01-2020, Here we use pin instead of Lat, Lng..
	public @ResponseBody List<ItemDetailsDTO> checkItemInRadius(@RequestParam(value="itemIds") List<Integer> ids,@RequestParam(value="pin") int pin) {
		//System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getCartItemsDetails(ids, pin);
	}
	
	@GetMapping("/itemsdetailsbyids")
	public @ResponseBody List<ItemDetailsDTO> getDetailsByIds(@RequestParam(value="itemIds") List<Integer> ids) {
		//System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getAllDetailsByIds(ids);
	}
	
	@GetMapping("/getitemqtyinradius")
	public @ResponseBody List<ItemDetailsDTO> getItemQtyInRadius(@RequestParam(value="itemIds") List<Integer> ids,@RequestParam(value="lat") double lat,@RequestParam(value="lng") double lng) {
		//System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getItemQtyInRadius(ids, lat, lng);
	}
	@GetMapping("/getitemqtyinradiusbypin")
	public @ResponseBody List<ItemDetailsDTO> getItemQtyInRadiusByPin(@RequestParam(value="itemIds") List<Integer> ids,@RequestParam(value="pin") int pin) {
		//System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getItemQtyInRadiusByPin(ids, pin);
	}
	

	@GetMapping("/getAllItemsDetailsByIds")
	public @ResponseBody List<ItemDetailsDTO> getAllItemsDetailsByIds(@RequestParam(value="itemIds") List<Integer> ids) {
         //  System.out.println("Ids = "+ids);
		
		if(ids.size()==0)
			return null;
		return itemService.getAllItemsDetailsByIds(ids);
	}
	@GetMapping("/getAllTopSaleItems")
	public @ResponseBody List<ItemDetailsDTO> getAllTopSaleItems() {
		   List<ItemDetailsDTO> bestSalesItems = itemService.getAllTopSaleItems();
		return bestSalesItems;
	}
	/*@GetMapping("/details/group/{id}/page/{page}/limit/{limit}")
	public @ResponseBody List<ItemDetailsDTO> getDetailsByGroupIdPaged(@PathVariable(value="id") int id,@PathVariable(value="limit",required=false)) {
		return itemService.getDetailsByGroupId(id);
	}*/

    //@RequestMapping(value = "/getFiveItemsPerCategoryByCatId", method = RequestMethod.GET)
    @GetMapping("/getFiveItemsPerCategoryByCatId")//21-01-2020,First Procedure used in this project..M.M.Hussain
	public ResponseEntity<List<ItemDetailsDTO>> getFiveItemsPerCategoryByCatId(@RequestParam(value="catId") Integer catId) {

        Iterable<ItemDetailsDTO> employees = itemService.getFiveItemsPerCategoryByCatId(catId);        
        List<ItemDetailsDTO> target = new ArrayList<>();
        employees.forEach(target::add);
        return new ResponseEntity<>(target, HttpStatus.OK);
    }
    
    @GetMapping("/getFiveItemsPerCategoryByCatIdNew")//21-01-2020,First Procedure used in this project..M.M.Hussain
   	public ResponseEntity<CategoryDTO> getFiveItemsPerCategoryByCatIdNew(@RequestParam(value="catId") Integer catId) {

    		CategoryDTO employees = (CategoryDTO) itemService.getFiveItemsPerCategoryByCatIdNew(catId);        
           //List<ItemDetailsDTO> target = new ArrayList<>();
          // employees.forEach(target::add);
           return new ResponseEntity<>(employees, HttpStatus.OK);
       }
}
