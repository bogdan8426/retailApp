package com.pink.retail.users;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	Users findByusersId(int usersId);
	Users findByemail(String email);
	Users findByPasswordAndEmail(String password, String email);
	Users findByEmailAndRole(String email, String roleDescription);
	Users findByusersIdAndProductsProductId(int userId, int productId);

}
