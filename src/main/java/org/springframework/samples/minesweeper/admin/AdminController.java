package org.springframework.samples.minesweeper.admin;

//package org.springframework.samples.minesweeper.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	private static final String VIEWS_ADMIN_CREATE_OR_UPDATE_FORM = "admin/createOrUpdateAdminForm";

	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/admin")
	public ModelAndView findAdmin() {
		ModelAndView mav = new ModelAndView("admin/adminDetails");
		mav.addObject(this.adminService.findAdmin());
		return mav;
	}
	
	@GetMapping(value = "/admin/{adminId}/edit")
	public String initUpdateAdminForm(Model model) {
		Admin admin = this.adminService.findAdmin();
		model.addAttribute(admin);
		return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/admin/{adminId}/edit")
	public String processUpdateAdminForm(@Valid Admin admin, BindingResult result,
			@PathVariable("adminId") int adminId) {
		if (result.hasErrors()) {
			return VIEWS_ADMIN_CREATE_OR_UPDATE_FORM;
		} else {
			admin.setId(adminId);
			this.adminService.saveAdmin(admin);
	
			return "redirect:/admin";
		}
	}
}