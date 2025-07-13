package com.tandt.coffee.manage.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tandt.coffee.manage.api.dto.UserDTO;
import com.tandt.coffee.manage.api.payload.ApiResponse;
import com.tandt.coffee.manage.api.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<UserDTO>> registerNewUser(@RequestBody UserDTO userDTO) {
		log.info("Received request to register new user.");
		UserDTO newUser = userService.addUser(userDTO).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", newUser));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
		UserDTO userDTO = userService.findUserById(id).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "User fetching successfuly", userDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		UserDTO newUserDTO = userService.updateUser(id, userDTO).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", newUserDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(new ApiResponse<>(true, "User delete successfully", null));
	}

}