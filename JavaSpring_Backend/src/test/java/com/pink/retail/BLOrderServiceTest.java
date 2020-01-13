package com.pink.retail;

import com.pink.retail.order.BLOrderService;
import com.pink.retail.order.Order;
import com.pink.retail.order.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BLOrderServiceTest {

    private BLOrderService service;
    private OrderRepository orderRepository;
    private Order order;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BLOrderService();
        orderRepository = Mockito.mock(OrderRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("orderRepository"),
                orderRepository);

        List<Order> orderList = new LinkedList<>();
        order = new Order(1, "date","status","payment");
        orderList.add(order);
        order = new Order(321, "da32te","stat42us","pa11yment");
        orderList.add(order);

        Mockito.when(service.findAll()).thenReturn(orderList);
        Mockito.when(service.findById(1)).thenReturn(orderList.get(0));
        Mockito.when(service.findById(321)).thenReturn(orderList.get(1));

        Mockito.when(service.save(order)).thenReturn(order);
    }

    @Test
    public void basic(){
        //FindAll
        assertNotNull(service.findAll());
        assertEquals(order, service.findAll().get(1));
        Mockito.verify(orderRepository,
                Mockito.times(2))
                .findAll();

        //findById
        assertEquals(order, service.findById(321));
        Mockito.verify(orderRepository)
                .getByorderId(321);

        assertNull(service.findById(300));
        Mockito.verify(orderRepository)
                .getByorderId(300);

        //save
        assertEquals(order, service.save(order));
        assertNull(service.save(null));

    }

    @Test
    public void rm(){
        service.removeOne(0);
        Mockito.verify(orderRepository).existsById(0);

        service.removeOne(321);
        Mockito.verify(orderRepository).existsById(321);

        service.remove();
        Mockito.verify(orderRepository).deleteAll();
    }

}
