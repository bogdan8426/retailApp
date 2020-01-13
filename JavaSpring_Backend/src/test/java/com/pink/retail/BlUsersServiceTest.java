package com.pink.retail;

import com.pink.retail.address.Address;
import com.pink.retail.product.ProductRepository;
import com.pink.retail.role.Role;
import com.pink.retail.role.RoleRepository;
import com.pink.retail.users.BlUsersService;
import com.pink.retail.users.Users;
import com.pink.retail.users.UsersRepository;
import com.pink.retail.util.BlUtilityService;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BlUsersServiceTest {

    private BlUsersService service;

    private UsersRepository usersRepository;
    private RoleRepository roleRepository;
    private ProductRepository productRepository;
    private BlUtilityService utilityService;

    private Users users;

    @Before
    public void initialisation() throws NoSuchFieldException {

        service = new BlUsersService();

        usersRepository = Mockito.mock(UsersRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        utilityService = Mockito.mock(BlUtilityService.class);

        FieldSetter.setField(service, service.getClass().getDeclaredField("usersRepository"),usersRepository);
        FieldSetter.setField(service, service.getClass().getDeclaredField("roleRepository"),roleRepository);
        FieldSetter.setField(service, service.getClass().getDeclaredField("productRepository"),productRepository);
        FieldSetter.setField(service, service.getClass().getDeclaredField("utilityService"),utilityService);

        List<Users> list = new LinkedList<>();
        users = new Users("firstName","lastName","email@mail.com","1233","password");
        users.setUsersId(1);
        list.add(users);

        users = new Users("firstName2","lastNam2e","ema2il@mail.com","121133","pas44sword");
        users.setUsersId(2);
        list.add(users);

        users = new Users("firstName3","lastNam3e","emai3l@mail.com","122233","pass11word");
        users.setUsersId(10);
        list.add(users);

        Mockito.when(service.getAllUsers()).thenReturn(list);

        Mockito.when(service.getUserById(1)).thenReturn(list.get(0));
        Mockito.when(service.getUserById(2)).thenReturn(list.get(1));
        Mockito.when(service.getUserById(10)).thenReturn(list.get(2));

        Mockito.when(service.getUserByEmail("email@mail.com")).thenReturn(list.get(0));
        Mockito.when(service.getUserByEmail("ema2il@mail.com")).thenReturn(list.get(1));
        Mockito.when(service.getUserByEmail("emai3l@mail.com")).thenReturn(list.get(2));

        Mockito.when(service.getUserByPasswordAndEmail("password","email@mail.com")).thenReturn(list.get(0));
        Mockito.when(service.getUserByPasswordAndEmail("pas44sword","ema2il@mail.com")).thenReturn(list.get(1));
        Mockito.when(service.getUserByPasswordAndEmail("pass11word","emai3l@mail.com")).thenReturn(list.get(2));

        Mockito.when(usersRepository.findByemail(new String(Base64.decodeBase64(users.getEmail())))).thenReturn(new Users());
        Mockito.when(usersRepository.findByusersId(20)).thenReturn(new Users());

    }

    @Test
    public void insertUpdateUser(){

        //insertUser
        service.insertUser(users);
        Role auxRole = roleRepository.getByRoleDescription("user");
        Mockito.verify(roleRepository, Mockito.times(2))
                .getByRoleDescription("user");

        assertEquals(auxRole, users.getRole());
        String pass = utilityService.encryptThisString(users.getPassword());
        Mockito.verify(utilityService)
                .encryptThisString(users.getPassword());

        assertEquals(pass, users.getPassword());

        Mockito.verify(usersRepository).save(users);

        //updateUser
        service.updateUser(users);
        Mockito.verify(usersRepository).existsById(users.getUsersId());

    }

    @Test
    public void removeUsers(){

        //removeAllUsers
        service.removeAllUsers();
        Mockito.verify(usersRepository).deleteAll();

        //removeById
        service.removeUserById(users.getUsersId());
        Mockito.verify(usersRepository).existsById(users.getUsersId());

    }

    @Test
    public void userGetters(){
        //getAllUsers
        assertNotNull(service.getAllUsers());
        assertEquals(3, service.getAllUsers().size());

        //getUserById
        assertEquals(users, service.getUserById(10));
        assertNull(service.getUserById(10000));

        //getByEmail
        assertEquals(users, service.getUserByEmail("emai3l@mail.com"));
        assertNull(service.getUserByEmail("N/A"));
        assertNull(service.getUserByEmail(""));

        //getByPassAndEmail
        assertEquals(users, service.getUserByPasswordAndEmail("pass11word","emai3l@mail.com"));
        assertNull(service.getUserByPasswordAndEmail("","emai31@mail.com"));
        assertNull(service.getUserByPasswordAndEmail("pass11word",""));
        assertNull(service.getUserByPasswordAndEmail("",""));
        assertNull(service.getUserByPasswordAndEmail("N/A","N/A"));

    }

    @Test
    public void updateAddress(){
        service.updateAddressList(new Address(), 1);
        Mockito.verify(usersRepository).findByusersId(1);

        service.updateAddressList(new Address(), 10);
        Mockito.verify(usersRepository).findByusersId(10);
    }

    @Test
    public void changePassword(){
        service.changePasswordAccount(users);
        Users newUser=usersRepository.findByemail(new String(Base64.decodeBase64(users.getEmail())));
        Mockito.verify(usersRepository, Mockito.times(2))
                .findByemail(new String(Base64.decodeBase64(users.getEmail())));

        assertEquals(users.getPassword(), newUser.getPassword());

        Mockito.verify(usersRepository).save(newUser);
    }

    @Test
    public void favourites(){
        //checkFavourite
        String result = service.checkFavorite(10, 55);
        assertEquals("false", result);
        Mockito.verify(usersRepository)
                .findByusersIdAndProductsProductId(10,55);

        //addToFavourites
        result = service.addToFavorite(1,1);
        assertEquals("true",result);
        Mockito.verify(productRepository).getByproductId(1);
        Mockito.verify(usersRepository).findByusersId(1);


        //deleteFromFavourites
        result = service.deleteFromFavorite(20,20);
        assertEquals("false",result);
        Mockito.verify(usersRepository).findByusersId(20);
        Mockito.verify(productRepository).getByproductId(20);
    }

}
