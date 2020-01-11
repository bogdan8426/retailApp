package com.pink.retail.role;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private BlRoleService roleService;
	
	@RequestMapping(value = "/addRole", method = RequestMethod.PUT)
	public Role addData(@RequestBody Role role) {
		return roleService.save(role);
	}

	@RequestMapping(value = "/allRoles", method = RequestMethod.GET)
	public List<Role> getData() {
		return roleService.findAll();
	}
	
	@RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role findById(@PathVariable @NotNull int roleId) {
        return roleService.findById(roleId);
	}
	
	@RequestMapping(value = "/remove/allRoles", method = RequestMethod.DELETE)
	public void remove() {
		roleService.remove();
	}
	
	@RequestMapping(value = "/remove/{roleId}", method = RequestMethod.DELETE)
	public void removeOne(@PathVariable @NotNull int roleId) {
		roleService.removeOne(roleId);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Role updateData(@RequestBody Role role) {
		return roleService.update(role);
	}
}
