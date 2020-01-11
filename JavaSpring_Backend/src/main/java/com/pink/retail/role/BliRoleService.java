package com.pink.retail.role;

import java.util.List;

public interface BliRoleService {

	List<Role> findAll();
	
	Role findById(int RoleId);
	
	Role save(Role data);
	
	Role update(Role data);
	
	void remove();
	
	void removeOne(int roleId);
}
	

