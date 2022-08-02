package org.springframework.samples.minesweeper.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository  extends CrudRepository<Admin,Integer>{
	@Query("SELECT a FROM Admin a")
	Admin findAdmin();
}