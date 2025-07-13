package com.tandt.coffee.manage.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.dto.UserDTO;
import com.tandt.coffee.manage.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User>  findByName(String name);

	UserDTO save(UserDTO userDTO);
}