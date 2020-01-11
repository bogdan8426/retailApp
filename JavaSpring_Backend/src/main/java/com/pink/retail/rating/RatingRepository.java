package com.pink.retail.rating;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer>{
	List<Rating> findByProductProductId(int productId);
}
