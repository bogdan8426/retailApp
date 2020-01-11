package com.pink.retail.users;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.address.Address;
import com.pink.retail.product.Product;
import com.pink.retail.product.ProductRepository;
import com.pink.retail.role.Role;
import com.pink.retail.role.RoleRepository;
import com.pink.retail.util.BlUtilityService;
import com.pink.retail.util.Utility;

@Service
public class BlUsersService implements BliUsersService {

	public static final Logger LOGGER = Logger.getLogger(BlUsersService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BlUtilityService utilityService;

	@Override
	public List<Users> getAllUsers() {

		return (List<Users>) usersRepository.findAll();
	}

	@Override
	public Users getUserById(int idUser) {

		return usersRepository.findByusersId(idUser);
	}

	@Override
	public void insertUser(Users addUser) {
		Role auxRole = roleRepository.getByRoleDescription("user");
		addUser.setRole(auxRole);
		addUser.setPassword(utilityService.encryptThisString(addUser.getPassword()));
		usersRepository.save(addUser);
	}

	@Override
	public void updateUser(Users updateUser) {
		if (usersRepository.existsById(updateUser.getUsersId()) == true) {
			usersRepository.save(updateUser);
		} else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
	}

	@Override
	public void removeAllUsers() {
		usersRepository.deleteAll();
	}

	@Override
	public void removeUserById(int idUser) {
		if (usersRepository.existsById(idUser) == true) {
			usersRepository.deleteById(idUser);
		} else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
	}

	@Override
	public Users getUserByEmail(String email) {
		return usersRepository.findByemail(email);
	}

	@Override
	public Users getUserByPasswordAndEmail(String password, String email) {
		return usersRepository.findByPasswordAndEmail(utilityService.encryptThisString(password), email);
	}

	@Override
	public String getDescription(String user) {
		Users userAux = usersRepository.findByemail(user);
		return userAux.getRole().getRole_description();
	}

	@Override
	public Users getUserUpdatedRole(String userEmail) {
		Users myUser = usersRepository.findByemail(userEmail);
		Role newRole = new Role();

		if (myUser.getRole().getRole_description().equals("admin")) {
			newRole = roleRepository.getByRoleDescription("user");
		} else {
			newRole = roleRepository.getByRoleDescription("admin");
		}
		myUser.setRole(newRole);
		updateUser(myUser);
		return usersRepository.findByemail(userEmail);
	}

	@Override
	public void updateAddressList(Address newAddress, int userId) {
		Users myUser = usersRepository.findByusersId(userId);
		List<Address> userListAddress = myUser.getAddresses();
		userListAddress.add(newAddress);
		myUser.setAddresses(userListAddress);
		updateUser(myUser);
	}

	@Override
	public String checkFavorite(int userId, int productId) {
		if(usersRepository.findByusersIdAndProductsProductId(userId,productId)==null)
		{
			return "false";
		}
		else
		{
			return "true";
		}
	}

	@Override
	public String addToFavorite(int userId, int productId) {
		Product myProductToFavorite=productRepository.getByproductId(productId);
		Users myUserForFavorite=usersRepository.findByusersId(userId);
		List<Product> listProductFavorite=myUserForFavorite.getProducts();
		listProductFavorite.add(myProductToFavorite);
		myUserForFavorite.setProducts(listProductFavorite);
		updateUser(myUserForFavorite);
		return "true";
	}

	@Override
	public String deleteFromFavorite(int userId, int productId) {
		Users myUserFromFavorite=usersRepository.findByusersId(userId);
		Product myProductToFavorite=productRepository.getByproductId(productId);
		List<Product> listPorductFavorite=myUserFromFavorite.getProducts();
		listPorductFavorite.remove(myProductToFavorite);
		myUserFromFavorite.setProducts(listPorductFavorite);
		updateUser(myUserFromFavorite);
		return "false";
		
	}
}
