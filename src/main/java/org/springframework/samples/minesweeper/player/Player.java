package org.springframework.samples.minesweeper.player;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.minesweeper.model.Person;
import org.springframework.samples.minesweeper.user.User;

import lombok.Getter;
import lombok.Setter;

@Audited
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends Person {
	
	private String city;

	private String address;

	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@NotEmpty
	@Email
	private String email;
	
	@NotAudited
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	@Valid
	private User user;
}