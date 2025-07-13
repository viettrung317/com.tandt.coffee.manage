package com.tandt.coffee.manage.api.mappers;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Set;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tandt.coffee.manage.api.dto.UserDTO;
import com.tandt.coffee.manage.api.model.Role.RoleName;
import com.tandt.coffee.manage.api.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRoleNames(user))")
    UserDTO toDTO(User user);

    @InheritInverseConfiguration
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDTO userDTO);

    List<UserDTO> toListUserDTO(List<User> listUser);

    default Set<RoleName> mapRoleNames(User user) {
        return user.getRoles().stream()
                   .map(role -> role.getName())
                   .collect(toSet());
    }
}

