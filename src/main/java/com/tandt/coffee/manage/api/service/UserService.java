package com.tandt.coffee.manage.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tandt.coffee.manage.api.dto.UserDTO;
import com.tandt.coffee.manage.api.exceptions.EntityNotFoundException;
import com.tandt.coffee.manage.api.mappers.UserMapper;
import com.tandt.coffee.manage.api.model.User;
import com.tandt.coffee.manage.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly = true)
	public Optional<UserDTO> findUserById(Long id){
		UserDTO userDTO = userMapper.toDTO(userRepository.findById(id).orElse(null));
		return Optional.ofNullable(userDTO);
	}
	
	@Transactional(readOnly = true)
	public Optional<List<UserDTO>> getAllUser(){
		log.info("Fetching all users");
		List<UserDTO> listUserDTOs = userMapper.toListUserDTO(userRepository.findAll());
		return Optional.ofNullable(listUserDTOs);
	}
	
	public Optional<UserDTO> addUser(UserDTO UserDTO) {
		log.info("Adding new user.");

		UserDTO newUserDTO = userRepository.save(UserDTO);
		return Optional.ofNullable(newUserDTO);
	}

	public Optional<UserDTO> updateUser(Long id, UserDTO updateUserDTO) {
		log.info("Updating user with id: {}", id);

		User updateuser = userRepository.findById(id).map(existingUser -> {
			User mergedUser = userMapper.toEntity(updateUserDTO);
			return userRepository.save(mergedUser);
		}).orElseThrow(() -> new EntityNotFoundException(id));
		UserDTO UserDTO = userMapper.toDTO(updateuser);
		return Optional.ofNullable(UserDTO);
	}

	public void deleteUser(Long id) {
		log.info("Deleting user with ID: {}", id);

		userRepository.findById(id).ifPresentOrElse(user -> {
			userRepository.delete(user);
			log.info("Successfully deleted user with ID: {}", id);
		}, () -> {
			log.warn("Failed to delete - user not found with ID: {}", id);
			throw new EntityNotFoundException(id);
		});
	}
}
