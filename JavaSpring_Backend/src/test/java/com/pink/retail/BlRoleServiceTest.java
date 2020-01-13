package com.pink.retail;

import com.pink.retail.role.BlRoleService;
import com.pink.retail.role.Role;
import com.pink.retail.role.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BlRoleServiceTest {

    private BlRoleService service;
    private RoleRepository roleRepository;
    private Role role;

    @Before
    public void initialize() throws NoSuchFieldException {

        service = new BlRoleService();
        roleRepository = Mockito.mock(RoleRepository.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("roleRepository"),
                roleRepository);

        List<Role> list = new LinkedList<>();
        role = new Role(1,"description1");
        list.add(role);

        role = new Role(2,"description223");
        list.add(role);

        role = new Role(30,"description223");
        list.add(role);

        Mockito.when(service.findAll()).thenReturn(list);
        Mockito.when(service.save(role)).thenReturn(role);
        Mockito.when(service.findById(1)).thenReturn(list.get(0));
        Mockito.when(service.findById(2)).thenReturn(list.get(1));
        Mockito.when(service.findById(30)).thenReturn(list.get(2));
    }

    @Test
    public void finders(){
        //findAll
        assertEquals(3, service.findAll().size());
        assertEquals(role, service.findAll().get(2));

        //findById
        assertEquals(role, service.findById(30));
        assertNull(service.findById(140));
    }

    @Test
    public void saveRole(){
        assertEquals(role, service.save(role));
        Mockito.verify(roleRepository).save(role);
    }

    @Test
    public void removal(){
        service.removeOne(1);
        Mockito.verify(roleRepository).existsById(1);

        service.removeOne(30);
        Mockito.verify(roleRepository).existsById(30);

        service.remove();
        Mockito.verify(roleRepository).deleteAll();

    }

}
