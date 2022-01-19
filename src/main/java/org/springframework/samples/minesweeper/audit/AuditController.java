package org.springframework.samples.minesweeper.audit;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditController {

	@Autowired
	private AuditService auditService;

	@GetMapping(value = "/audits")
	public String selectGame(Map<String, Object> model, HttpServletRequest request) {
		Collection<Audit> audits = this.auditService.findAll();
		model.put("audits", audits);
		return "admin/viewAudits";
	}

}