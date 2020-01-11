package com.pink.retail.category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	Category findByCategoryId(int categoryId);
	Category findByCategoryName(String categoryName);
}
