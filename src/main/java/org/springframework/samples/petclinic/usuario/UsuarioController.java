package org.springframework.samples.petclinic.usuario;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public String listadoUsuarios(ModelMap modelMap) {
		String vista="usuarios/listadoUsuarios";
		Iterable<Usuario> usuarios = usuarioService.findAll();
		modelMap.addAttribute("usuarios",usuarios);
		return vista;
	}
	
	@GetMapping("/usuarios/{usuarioId}")
	public ModelAndView showUsuario(@PathVariable("usuarioId") int usuarioId) {
		ModelAndView mav = new ModelAndView("usuarios/usuarioDetails");
		mav.addObject(this.usuarioService.findUsuarioById(usuarioId));
		return mav;
	}
}
