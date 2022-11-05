package org.springframework.samples.minesweeper.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.minesweeper.user.AuthoritiesService;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	@Transactional
	public Admin findAdmin() {
		return this.adminRepository.findAll().iterator().next();
	}
	
	@Transactional
	public void saveAdmin(Admin admin) throws DataAccessException {
		adminRepository.save(admin);
		userService.saveUser(admin.getUser());
		authoritiesService.saveAuthorities(admin.getUser().getUsername(), "admin");
	}
}