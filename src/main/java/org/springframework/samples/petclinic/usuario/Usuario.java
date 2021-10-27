package org.springframework.samples.petclinic.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Getter
@Setter
@Entity
@Table(name="usuarios")
public class Usuario extends NamedEntity{
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
	@Column(name = "contraseña")
	private String contraseña;
}
