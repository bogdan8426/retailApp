package com.pink.retail;

import com.pink.retail.mail.BlMailService;
import com.pink.retail.order.BLIOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

public class MailSuggestionTest {

    private BlMailService service;
    private BLIOrderService orderService;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlMailService();
        orderService = Mockito.mock(BLIOrderService.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("orderService"),
                orderService);

    }

    @Test
    public void sendSuggestionMail() {
        service.sendSuggestionsMail();
        Mockito.verify(orderService).findAll();
    }

}
