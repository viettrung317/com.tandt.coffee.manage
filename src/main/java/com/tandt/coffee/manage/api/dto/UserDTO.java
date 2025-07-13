package com.tandt.coffee.manage.api.dto;

import java.util.Set;

import com.tandt.coffee.manage.api.model.Role.RoleName;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private Set<RoleName> roles;
}
