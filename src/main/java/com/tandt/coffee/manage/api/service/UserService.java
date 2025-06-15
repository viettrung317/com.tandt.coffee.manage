package com.tandt.coffee.manage.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tandt.coffee.manage.api.model.User;
import com.tandt.coffee.manage.api.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findUserWithId(Long id){
		return userRepository.findById(id);
	}
}
