package com.tandt.coffee.manage.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tandt.coffee.manage.api.dto.RoleDTO;
import com.tandt.coffee.manage.api.exceptions.EntityNotFoundException;
import com.tandt.coffee.manage.api.mappers.RoleMapper;
import com.tandt.coffee.manage.api.model.Role;
import com.tandt.coffee.manage.api.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleService {

	@Autowired
	RoleRepository RoleRepository;

	@Autowired
	RoleMapper RoleMapper;

	@Transactional(readOnly = true)
	public Optional<List<RoleDTO>> getAllOrder() {
		log.info("Fetching all Roles");
		List<RoleDTO> listRoleDTOs = RoleMapper.toListRoleDTO(RoleRepository.findAll());
		return Optional.ofNullable(listRoleDTOs);
	}

	@Transactional(readOnly = true)
	public Optional<RoleDTO> getRoleById(Long id) {
		RoleDTO RoleDTO = RoleMapper.toDTO(RoleRepository.findById(id).orElse(null));
		return Optional.ofNullable(RoleDTO);
	}

	public Optional<RoleDTO> addRole(RoleDTO RoleDTO) {
		log.info("Adding new role.");

		RoleDTO newRoleDTO = RoleRepository.save(RoleDTO);
		return Optional.ofNullable(newRoleDTO);
	}

	public Optional<RoleDTO> updateRole(Long id, RoleDTO updateRoleDTO) {
		log.info("Updating role with id: {}", id);

		Role updateRole = RoleRepository.findById(id).map(existingRole -> {
			Role mergedRole = RoleMapper.toEntity(updateRoleDTO);
			return RoleRepository.save(mergedRole);
		}).orElseThrow(() -> new EntityNotFoundException(id));
		RoleDTO RoleDTO = RoleMapper.toDTO(updateRole);
		return Optional.ofNullable(RoleDTO);
	}

	public void deleteRole(Long id) {
		log.info("Deleting role with ID: {}", id);

		RoleRepository.findById(id).ifPresentOrElse(Role -> {
			RoleRepository.delete(Role);
			log.info("Successfully deleted role with ID: {}", id);
		}, () -> {
			log.warn("Failed to delete - role not found with ID: {}", id);
			throw new EntityNotFoundException(id);
		});
	}
}
