package org.springframework.samples.buscaminas.user;

import org.springframework.data.repository.CrudRepository;


public interface AuthoritiesRepository extends  CrudRepository<Authorities, String> {
	
}
