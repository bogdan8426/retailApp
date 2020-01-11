package com.pink.retail.role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Integer>{
	Role getByRoleId(int roleId);
	Role getByRoleDescription(String roleDescript);
}
