package com.pink.retail;

import com.pink.retail.orderProducts.BlOrderProductsService;
import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.orderProducts.OrderProductsRepository;
import com.pink.retail.product.Product;
import com.pink.retail.shoppingCart.ShoppingCart;
import com.pink.retail.shoppingCart.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BlOrderProductsServiceTest {

    private BlOrderProductsService service;
    private OrderProductsRepository orderProductRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private OrderProducts orderProducts;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlOrderProductsService();
        orderProductRepository = Mockito.mock(OrderProductsRepository.class);
        shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);

        FieldSetter.setField(service,
                service.getClass().getDeclaredField("orderProductRepository"),
                orderProductRepository);

        FieldSetter.setField(service,
                service.getClass().getDeclaredField("shoppingCartRepository"),
                shoppingCartRepository);

        List<OrderProducts> list = new LinkedList<>();
        orderProducts = new OrderProducts();
        orderProducts.setIdOrderProduct(1);
        orderProducts.setQuantity(332);
        list.add(orderProducts);

        orderProducts = new OrderProducts();
        orderProducts.setIdOrderProduct(2);
        orderProducts.setQuantity(22);
        Product product = new Product();
        product.setProductId(11);
        orderProducts.setProductOrder(product);
        list.add(orderProducts);

        Mockito.when(service.getAllOrderProducts()).thenReturn(list);

    }

    @Test
    public void getters(){
        assertEquals(2, service.getAllOrderProducts().size());
        assertEquals(orderProducts, service.getAllOrderProducts().get(1));

        service.getByOrderId(1024);
        Mockito.verify(orderProductRepository).getByOrderProductOrderId(1024);
    }

    @Test
    public void additions() throws NullPointerException {
        service.addProductOrder(orderProducts);
        Mockito.verify(orderProductRepository).save(orderProducts);

        service.addProductOrder(null);
        Mockito.verify(orderProductRepository).save(null);
    }

    @Test
    public void deleteFromCart(){
        service.deleteFromShoppingCart(1,11);
        ShoppingCart shop = shoppingCartRepository.getByUserUsersIdAndProductProductId(1, 11);
        Mockito.verify(shoppingCartRepository, Mockito.times(2))
                .getByUserUsersIdAndProductProductId(1,11);

        Mockito.verify(shoppingCartRepository).delete(shop);
    }

}
