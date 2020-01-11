package com.pink.retail.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	BliAddressService addressService;
	
	@RequestMapping(value="/allAddress",method=RequestMethod.GET)
	public List<Address> getAllAddress()
	{
		return addressService.getAllAddress();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Address getAddressById(@PathVariable("id") int idAddress)
	{
		return addressService.getAddressById(idAddress);
	}
	
	@RequestMapping(value="/removeAddress/{id}", method=RequestMethod.DELETE)
	public Address deleteAddressById(@PathVariable("id") int idAddress)
	{
		Address auxAddress=addressService.getAddressById(idAddress);
		addressService.removeAddressById(idAddress);
		return auxAddress;
	}
	
	@RequestMapping(value="/removeAddress/all",method=RequestMethod.DELETE)
	public void deleteAllAddress()
	{
		addressService.removeAllAddress();
	}
	
	@RequestMapping(value="/addAddress",method=RequestMethod.PUT)
	public void addAddress(@RequestBody Address address)
	{
		addressService.insertAddress(address);
	}
	
	@RequestMapping(value="/updateAddress/{id}",method=RequestMethod.POST)
	public Address updateAddress(@RequestBody Address address, @PathVariable("id") int idAddress)
	{
		addressService.updateAddressById(address, idAddress);
		return addressService.getAddressById(idAddress);
	}
}
