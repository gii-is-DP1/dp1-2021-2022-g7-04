package org.springframework.samples.minesweeper.player;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.buscaminas.model.BaseEntity;
import org.springframework.samples.buscaminas.model.NamedEntity;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity {
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "password")
	private String password;
	@Column(name = "username")
	private String username;
	@Column(name = "city")
	private String city;
	@Column(name = "address")
	private String address;
	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
}
