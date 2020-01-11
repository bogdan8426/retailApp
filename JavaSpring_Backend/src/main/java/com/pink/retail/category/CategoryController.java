package com.pink.retail.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
public class CategoryController  {

	@Autowired
	BliCategoryService categoryService;
	
	@RequestMapping(value="/{categoryId}",method=RequestMethod.GET)
	public Category getCategoryById(@PathVariable("categoryId") int categoryId)
	{
		return categoryService.getByCategoryId(categoryId);
	}
	
	@RequestMapping(value="/name/{categoryName}",method=RequestMethod.GET)
	public Category getCategoryByName(@PathVariable("categoryName") String categoryName)
	{
		return categoryService.getByCategoryName(categoryName);
	
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.PUT)
	public void addAddress(@RequestBody Category category)
	{
		categoryService.insertCategory(category);
	}
	
	@RequestMapping(value="/allCategory", method=RequestMethod.GET)
	public List<Category> getAllCategory()
	{
		return categoryService.getAllCategory();
	}
	
	
}
