package com.pink.retail.rating;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	BliRatingService ratingService;
	
	@RequestMapping(value = "/addRating", method = RequestMethod.PUT)
	public String insertRating(@RequestBody Rating rating) {
		return ratingService.insertRating(rating);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public String getRatingValueByProductId(@PathVariable @NotNull int productId) {
        return ratingService.getRatingValueByProuctId(productId);
	}
	
	@RequestMapping(value = "/comm/{productId}", method = RequestMethod.GET)
    public List<Rating> getRatingByProductId(@PathVariable @NotNull int productId) {
        return ratingService.getRatingByProductId(productId);
	}
}
