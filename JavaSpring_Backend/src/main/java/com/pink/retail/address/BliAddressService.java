package com.pink.retail.address;

import java.util.List;

public interface BliAddressService {
	List<Address> getAllAddress();
	
	Address getAddressById(int addressId);
	
	void removeAddressById(int addressId);
	
	void removeAllAddress();
	
	void insertAddress(Address addAddress);
	
	void updateAddressById(Address updateAddress, int addressId);
	
	
}
