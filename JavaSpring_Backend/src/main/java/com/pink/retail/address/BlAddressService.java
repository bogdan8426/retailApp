package com.pink.retail.address;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.util.Utility;


@Service
public class BlAddressService implements BliAddressService{
	public static final Logger LOGGER=Logger.getLogger(BlAddressService.class);
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAddress() {
		return (List<Address>)addressRepository.findAll();
	}

	@Override
	public Address getAddressById(int addressId) {
		return addressRepository.findByaddressId(addressId);
	}

	@Override
	public void removeAddressById(int addressId) {
		if(addressRepository.existsById(addressId)==true)
		{
			
			addressRepository.deleteById(addressId);
		}
		else
		{
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
		
	}

	@Override
	public void removeAllAddress() {
		addressRepository.deleteAll();
		
	}

	@Override
	public void insertAddress(Address addAddress) {
			addressRepository.save(addAddress);	
	}

	@Override
	public void updateAddressById(Address updateAddress, int addressId) {
		if(addressRepository.existsById(addressId)==true)
		{
			updateAddress.setAddressId(addressId);
			addressRepository.save(updateAddress);
		}
		else
		{
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
	}
}
