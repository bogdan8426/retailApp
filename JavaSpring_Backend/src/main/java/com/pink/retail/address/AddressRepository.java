package com.pink.retail.address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>{
	Address findByaddressId(int id);
}
