package com.pink.retail.role;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.util.Utility;

@Service
public class BlRoleService {

	public static final Logger LOGGER=Logger.getLogger(BlRoleService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}
	
	public Role findById(int RoleId) {
		return roleRepository.getByRoleId(RoleId);		
	}

	public Role save(Role data) {
		return roleRepository.save(data);
	}

	public Role update(Role data){
		if(roleRepository.existsById(data.getRoleId())==true) {
			return roleRepository.save(data);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
			return null;
		}		
	}
	public void removeOne(int RoleId) {
		if(roleRepository.existsById(RoleId)) {
			roleRepository.deleteById(RoleId);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}		
	}
	
	public void remove() {
		roleRepository.deleteAll();
	}
}
