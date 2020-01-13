package com.pink.retail;

import com.pink.retail.product.BLProductService;
import com.pink.retail.product.Product;
import com.pink.retail.product.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BLProductServiceTest {

    private BLProductService service;
    private ProductRepository productRepository;
    private Product product;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BLProductService();
        productRepository = Mockito.mock(ProductRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("productRepository"),
                productRepository);

        List<Product> productList = new LinkedList<>();
        product = new Product("name","brand",20,30.00);
        product.setProductId(1);
        productList.add(product);
        product = new Product("name2","brand2",202,302.00);
        product.setProductId(2);
        productList.add(product);

        Mockito.when(service.findAll()).thenReturn(productList);

        Mockito.when(service.findById(1)).thenReturn(productList.get(0));
        Mockito.when(service.findById(2)).thenReturn(productList.get(1));

        Mockito.when(service.save(product)).thenReturn(product);
    }

    @Test
    public void finders(){
        //findAll
        assertEquals(2,service.findAll().size());

        //findById
        assertEquals(product, service.findById(2));
        assertNull(service.findById(100));
    }

    @Test
    public void save(){
        assertEquals(product, service.save(product));
        Mockito.verify(productRepository).save(product);
    }

    @Test
    public void removal(){
        service.removeOne(1);
        Mockito.verify(productRepository).existsById(1);

        service.removeOne(100);
        Mockito.verify(productRepository).existsById(100);

        service.remove();
        Mockito.verify(productRepository).deleteAll();
    }


}
