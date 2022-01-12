package com.edutecno.pruebaFinalSistemaIMDb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Size(min=3, message = "Username must be present")
    private String username;
   
	@Size(min=5, message = "email must be greater than 5 characters")
    private String email;
    
	@Size(min=8, message = "password must be greater than 8 characters")
    private String password;

	@Transient
	private String passwordConfirmation;
    
    @ManyToMany(mappedBy = "users")
    private List<Show> favoritesShows = new ArrayList<Show>();
	
	@Enumerated(EnumType.STRING)
    private Role roles;
 
}
