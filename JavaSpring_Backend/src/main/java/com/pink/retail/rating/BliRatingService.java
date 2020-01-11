package com.pink.retail.rating;

import java.util.List;

public interface BliRatingService {

	String getRatingValueByProuctId(int productId);
	
	List<Rating> getRatingByProductId(int productId);
	
	String insertRating(Rating rating);
	
	List<Rating> getAllRatings();
}
