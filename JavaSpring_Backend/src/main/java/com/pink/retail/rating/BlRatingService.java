package com.pink.retail.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlRatingService implements BliRatingService{

	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public String getRatingValueByProuctId(int productId) {
		int ratingSum=0;
		String ratingAvg;
		List<Rating> ratingsForProduct= ratingRepository.findByProductProductId(productId);
		if(ratingsForProduct.size()>0) {
			for (Rating rating : ratingsForProduct) {
				ratingSum+=Integer.parseInt(rating.getRatingStars());
			}
			ratingAvg=Double.toString(Math.round(ratingSum*10/ratingsForProduct.size())/(double) 10);
			return ratingAvg;
		}
		return null;
	}

	@Override
	public String insertRating(Rating rating) {
		boolean ratingGiven=false;
		List<Rating> allRatings=getAllRatings();
		for (Rating ratingData : allRatings) {
			if(ratingData.getUser().getUsersId()==rating.getUser().getUsersId() && ratingData.getProduct().getProductId()==rating.getProduct().getProductId()) {
				ratingGiven=true;
			}
		}
		if(ratingGiven==true) {
			return "fail";
		}else {
			ratingRepository.save(rating);
			return "success";
		}				
	}

	@Override
	public List<Rating> getAllRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByProductId(int productId) {
		List<Rating> ratings=ratingRepository.findByProductProductId(productId);
		
		for (int i=0;i<ratings.size();i++) {
			if(ratings.get(i).getComment().isEmpty()) {
				Rating rating=ratings.get(i);
				rating.setComment("No comment");
				ratings.set(i,rating);
			}
		}
		return ratings;
	}
	

}
