package com.pink.retail.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlCategoryService implements BliCategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category getByCategoryId(int categoryId) {	
		return categoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public Category getByCategoryName(String categoryName) {

		return categoryRepository.findByCategoryName(categoryName);
	}

	@Override
	public void insertCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public List<Category> getAllCategory() {

		return (List<Category>)categoryRepository.findAll();
	}

}
