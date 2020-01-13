package com.pink.retail;

import com.pink.retail.address.Address;
import com.pink.retail.address.AddressRepository;
import com.pink.retail.address.BlAddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BlAddressServiceTest {

    private BlAddressService service;
    private AddressRepository repositoryMock;
    private Address address;

    @Before
    public void init() throws NoSuchFieldException {
        //setup
        service = new BlAddressService();
        repositoryMock = Mockito.mock(AddressRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("addressRepository"),
                repositoryMock);

        List<Address> addressList = new LinkedList<>();
        address = new Address("street", "12343", "city","country");
        address.setAddressId(1);
        addressList.add(address);
        address = new Address("street", "12343", "city","country");
        address.setAddressId(2);
        addressList.add(address);

        Mockito.when(service.getAllAddress()).thenReturn(addressList);

        Mockito.when(service.getAddressById(1)).thenReturn(addressList.get(0));
        Mockito.when(service.getAddressById(2)).thenReturn(addressList.get(1));

    }

    @Test
    public void basic(){
        //getAllAddress
        assertNotNull(service.getAllAddress());
        assertEquals(2, service.getAllAddress().size());

        //getAddressById
        assertEquals(service.getAddressById(0), repositoryMock.findByaddressId(0));
        assertNull(service.getAddressById(-1));
        assertEquals(address, service.getAddressById(2));

        //removeAllAddress
        service.removeAllAddress();
        Mockito.verify(repositoryMock).deleteAll();

        //insertAddress
        Address temp = new Address("st2reet", "132343", "c4ity","1country");
        service.insertAddress(temp);
        Mockito.verify(repositoryMock).save(temp);

    }

    @Test
    public void removeById(){
        List<Address> addressList = service.getAllAddress();
        service.removeAddressById(-1);

        assertEquals(addressList, service.getAllAddress());
    }

    @Test
    public void updateById(){
        service.updateAddressById(null, -343);
        assertEquals(2, service.getAllAddress().size());

        service.updateAddressById(null, 1);
        assertEquals(2, service.getAllAddress().size());

        address.setPostalCode("002211");
        service.updateAddressById(address, 2);
        assertEquals("002211", service.getAllAddress().get(1).getPostalCode());

    }

}
