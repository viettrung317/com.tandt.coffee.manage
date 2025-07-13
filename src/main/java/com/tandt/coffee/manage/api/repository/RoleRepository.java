package com.tandt.coffee.manage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.dto.RoleDTO;
import com.tandt.coffee.manage.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	RoleDTO save(RoleDTO roleDTO);

}
