package com.pink.retail.category;

import java.util.List;

public interface BliCategoryService {
	
	Category getByCategoryId(int categoryId);
	
	Category getByCategoryName(String categoryName);
	
	void insertCategory(Category category);
	
	List<Category> getAllCategory();
}
