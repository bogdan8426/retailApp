package com.pink.retail;

import com.pink.retail.product.Product;
import com.pink.retail.shoppingCart.BlShoppingCartService;
import com.pink.retail.shoppingCart.ShoppingCart;
import com.pink.retail.shoppingCart.ShoppingCartRepository;
import com.pink.retail.users.Users;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BlShoppingCartServiceTest {

    private BlShoppingCartService service;
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCart shoppingCart;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlShoppingCartService();
        shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("shoppingCartRepository"),
                shoppingCartRepository);

        List<ShoppingCart> list = new LinkedList<>();
        shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingId(1);
        shoppingCart.setQuantity(20);
        Users user = new Users();
        user.setUsersId(100);
        shoppingCart.setUser(user);
        Product product = new Product();
        product.setProductId(1);
        product.setQuantity(100);
        shoppingCart.setProduct(product);
        list.add(shoppingCart);

        shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingId(2);
        shoppingCart.setQuantity(5);
        shoppingCart.setUser(user);
        product.setQuantity(3);
        shoppingCart.setProduct(product);
        list.add(shoppingCart);

        Mockito.when(service.getByUser(100)).thenReturn(list);

    }

    @Test
    public void getByUser(){
        assertEquals(2, service.getByUser(100).size());
        assertEquals(shoppingCart, service.getByUser(100).get(1));
    }

    @Test
    public void insertCart(){
        String result = service.insertShoppingCart(shoppingCart);
        Mockito.verify(shoppingCartRepository)
                .getByUserUsersIdAndProductProductId(shoppingCart.getUser().getUsersId(),
                                                    shoppingCart.getProduct().getProductId());
        Mockito.verify(shoppingCartRepository).save(shoppingCart);
        assertEquals("success", result);

        shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingId(1);
        shoppingCart.setQuantity(20);
        Users user = new Users();
        shoppingCart.setUser(user);
        Product product = new Product();
        product.setQuantity(100);
        shoppingCart.setProduct(product);

        result = service.insertShoppingCart(shoppingCart);
        Mockito.verify(shoppingCartRepository)
                .getByUserUsersIdAndProductProductId(shoppingCart.getUser().getUsersId(),
                        shoppingCart.getProduct().getProductId());
        Mockito.verify(shoppingCartRepository).save(shoppingCart);
        assertEquals("success", result);
    }
}
