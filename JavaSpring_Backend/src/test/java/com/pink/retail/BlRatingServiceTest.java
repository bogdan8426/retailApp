package com.pink.retail;

import com.pink.retail.product.Product;
import com.pink.retail.rating.BlRatingService;
import com.pink.retail.rating.Rating;
import com.pink.retail.rating.RatingRepository;
import com.pink.retail.users.Users;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BlRatingServiceTest {

    private BlRatingService service;
    private RatingRepository ratingRepository;
    private Rating rating;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlRatingService();
        ratingRepository = Mockito.mock(RatingRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("ratingRepository"),
                ratingRepository);

        List<Rating> list = new LinkedList<>();
        rating = new Rating();
        rating.setRatingId(1);
        rating.setComment("comment1");
        rating.setRatingStars("4");
        Users user = new Users();
        user.setUsersId(100);
        rating.setUser(user);
        Product product = new Product();
        product.setProductId(1);
        rating.setProduct(product);
        list.add(rating);

        rating = new Rating();
        rating.setRatingId(23);
        rating.setComment("comment1432?");
        rating.setRatingStars("1");
        rating.setUser(user);
        rating.setProduct(product);
        list.add(rating);

        Mockito.when(service.getAllRatings()).thenReturn(list);

    }

    @Test
    public void getRatings(){
        assertEquals(2, service.getAllRatings().size());
        assertEquals(rating, service.getAllRatings().get(1));
    }

    @Test
    public void getRatingByProductId(){
        assertNull(service.getRatingValueByProuctId(100));
        Mockito.verify(ratingRepository).findByProductProductId(100);
    }

    @Test
    public void insertRating(){
        String result = service.insertRating(rating);
        assertEquals("fail", result);


        rating = new Rating();
        rating.setRatingId(1);
        rating.setComment("some comment");
        rating.setRatingStars("3");
        Users user = new Users();
        user.setUsersId(10);
        rating.setUser(user);
        Product product = new Product();
        product.setProductId(12);
        rating.setProduct(product);

        result = service.insertRating(rating);
        Mockito.verify(ratingRepository).save(rating);
        assertEquals("success", result);
    }

}
