package com.tandt.coffee.manage.api.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	public enum RoleName {
	    ADMIN, USER, GUEST
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false)
	private RoleName name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}