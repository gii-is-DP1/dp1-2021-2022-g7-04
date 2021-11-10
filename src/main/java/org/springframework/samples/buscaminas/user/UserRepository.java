package org.springframework.samples.buscaminas.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
	
}
