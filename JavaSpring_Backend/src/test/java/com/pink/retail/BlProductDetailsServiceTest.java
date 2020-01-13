package com.pink.retail;

import com.pink.retail.productDetails.BlProductDetailsService;
import com.pink.retail.productDetails.ProductDetails;
import com.pink.retail.productDetails.ProductDetailsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BlProductDetailsServiceTest {

    private BlProductDetailsService service;
    private ProductDetailsRepository productDetailsRepository;
    private ProductDetails productDetails;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlProductDetailsService();
        productDetailsRepository = Mockito.mock(ProductDetailsRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("productDetailsRepository"),
                productDetailsRepository);

        List<ProductDetails> list = new LinkedList<>();
        productDetails = new ProductDetails();
        productDetails.setProductDetailsId(1);
        productDetails.setDescription("desc");
        productDetails.setMemorieInterna("mem");
        productDetails.setMemorieRam("ram");
        list.add(productDetails);

        productDetails = new ProductDetails();
        productDetails.setProductDetailsId(23);
        productDetails.setDescription("desc23");
        productDetails.setMemorieInterna("m23em");
        productDetails.setMemorieRam("ra23m");
        list.add(productDetails);

        Mockito.when(service.findAll()).thenReturn(list);
        Mockito.when(service.findById(1)).thenReturn(list.get(0));
        Mockito.when(service.findById(23)).thenReturn(list.get(1));
        Mockito.when(service.save(productDetails)).thenReturn(productDetails);
    }

    @Test
    public void getDetails(){
        //findAll
        assertEquals(2, service.findAll().size());

        //findById
        assertEquals(productDetails, service.findById(23));
        assertNull(service.findById(140));
    }

    @Test
    public void saveDetails(){
        assertEquals(productDetails, service.save(productDetails));
        Mockito.verify(productDetailsRepository).save(productDetails);
    }

    @Test
    public void removeDetails(){
        service.removeOne(1);
        Mockito.verify(productDetailsRepository).existsById(1);

        service.removeOne(100);
        Mockito.verify(productDetailsRepository).existsById(100);

        service.remove();
        Mockito.verify(productDetailsRepository).deleteAll();
    }

}
