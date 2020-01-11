package com.pink.retail.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.users.Users;

@Entity
public class Role {

	@Column(name = "ROLE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;
	
	@OneToMany(mappedBy="role",cascade=CascadeType.MERGE)
    private List<Users> user=new ArrayList<Users>();

	public Role() {
		super();
	}
	
	public Role(int roleId, String role_description) {
		super();
		this.roleId = roleId;
		this.roleDescription = role_description;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole_description() {
		return roleDescription;
	}

	public void setRole_description(String role_description) {
		this.roleDescription = role_description;
	}
	@JsonIgnore
	public List<Users> getUser() {
		return user;
	}

	public void setUser(List<Users> user) {
		this.user = user;
	}

	
}
