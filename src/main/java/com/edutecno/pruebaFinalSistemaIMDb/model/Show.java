package com.edutecno.pruebaFinalSistemaIMDb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="show_id", nullable = false, unique = true)
	private Long id;
	
	@Size(min=1, message = "Title must be present")
	private String showTitle;
	
	@Size(min=1, message = "Network must be present")
	private String showNetwork;
	
	private float average;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "users_shows", joinColumns =  @JoinColumn(name = "show_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<User>();
	
	
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings;
    
    public Show(String showTitle, String showNetwork) {
        this.showTitle = showTitle;
        this.showNetwork = showNetwork;
    }
}
