package com.pink.retail.users;

import java.util.List;

import com.pink.retail.address.Address;

public interface BliUsersService {

	List<Users> getAllUsers();
	
	Users getUserById(int idUser);
	
	void insertUser(Users addUser);
	
	void updateUser(Users updateUser);
	
	void removeAllUsers();
	
	void removeUserById(int idUser);
	
	Users getUserByEmail(String email);
	
	Users getUserByPasswordAndEmail(String password, String email);
	
	String getDescription(String user);
	
	Users getUserUpdatedRole(String userEmail);
	
	void updateAddressList(Address newAddress, int userId);
	
	String checkFavorite(int userId, int productId);
	
	String addToFavorite(int userId, int productId);
	
	String deleteFromFavorite(int userId, int productId);
}
