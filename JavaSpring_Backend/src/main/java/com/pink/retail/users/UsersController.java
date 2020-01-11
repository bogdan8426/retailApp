package com.pink.retail.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pink.retail.address.Address;
import com.pink.retail.product.Product;


@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	BliUsersService userService;
	
	@RequestMapping(value="/allUsers",method=RequestMethod.GET)
	public List<Users> getAllUser()
	{
		return userService.getAllUsers();
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Users getAUserById(@PathVariable("id") int idUser)
	{
		return userService.getUserById(idUser);
	}
	
	@RequestMapping(value="/removeUser/{id}", method=RequestMethod.DELETE)
	public Users deleteUserById(@PathVariable("id") int idUser)
	{
		Users auxUser=userService.getUserById(idUser);
		userService.removeUserById(idUser);
		return auxUser;
	}
	
	@RequestMapping(value="/removeUser/all",method=RequestMethod.DELETE)
	public void deleteAllUser()
	{
		userService.removeAllUsers();
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.PUT)
	public void addUser(@RequestBody Users Users)
	{
		userService.insertUser(Users);
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public Users updateUser(@RequestBody Users Users)
	{
		userService.updateUser(Users);
		return userService.getUserById(Users.getUsersId());
	}
	
	@RequestMapping(value="/getUserByEmail",method=RequestMethod.POST)
	public Users getAUserByEmail(@RequestBody Users users)
	{
		return userService.getUserByEmail(users.getEmail());
	}
	
	@RequestMapping(value="/getUserByPasswordAndEmail",method=RequestMethod.POST)
	public Users getAUserByPasswordAndEmail(@RequestBody Users users)
	{
		return userService.getUserByPasswordAndEmail(users.getPassword(), users.getEmail());
	}
	@RequestMapping(value="/getDescription",method=RequestMethod.POST)
	public String getDescriptionByEmail(@RequestBody String user)
	{
		return userService.getDescription(user);
	}
	
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	public Users updateUserRole(@RequestBody String userEmail)
	{
		return userService.getUserUpdatedRole(userEmail);
	}
	
	@RequestMapping(value="/updateAddress/{idUser}", method=RequestMethod.POST)
	public void updateAddressList(@RequestBody Address newAddress, @PathVariable int idUser)
	{
		userService.updateAddressList(newAddress,idUser);
	}
	
	@RequestMapping(value="/addFavorite/{idUser}",method=RequestMethod.POST)
	public String addProductToFavorite(@PathVariable int idUser, @RequestBody String productId)
	{
		return userService.addToFavorite(idUser, Integer.parseInt(productId));
	}
	
	@RequestMapping(value="/checkFavorite/{idUser}",method=RequestMethod.POST)
	public String checkFromFavorite(@PathVariable int idUser, @RequestBody String productId)
	{
		return userService.checkFavorite(idUser, Integer.parseInt(productId));
	}
	
	@RequestMapping(value="/remove/{productId}/{idUser}",method=RequestMethod.DELETE)
	public String deleteProductFromFavorite(@PathVariable int idUser, @PathVariable int productId)
	{
		return userService.deleteFromFavorite(idUser,productId);
	}
	
	@RequestMapping(value="/{idUser}/allProductFav", method=RequestMethod.GET)
	public List<Product> getAllFavoriteProducts(@PathVariable int idUser)
	{
		return userService.getUserById(idUser).getProducts();
	}
	
}
