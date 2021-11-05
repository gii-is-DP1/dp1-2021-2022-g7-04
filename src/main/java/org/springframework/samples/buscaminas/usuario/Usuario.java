package org.springframework.samples.buscaminas.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.buscaminas.model.BaseEntity;
import org.springframework.samples.buscaminas.model.NamedEntity;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "password")
	private String password;
}
