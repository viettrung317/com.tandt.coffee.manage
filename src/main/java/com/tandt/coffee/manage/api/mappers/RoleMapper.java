package com.tandt.coffee.manage.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.tandt.coffee.manage.api.dto.RoleDTO;
import com.tandt.coffee.manage.api.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleDTO toDTO(Role role);
	Role toEntity(RoleDTO role);

	List<RoleDTO> toListRoleDTO(List<Role> roles);
}
