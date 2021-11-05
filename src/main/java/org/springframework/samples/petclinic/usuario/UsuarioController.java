package org.springframework.samples.petclinic.usuario;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	private static final String VIEWS_USUARIO_CREATE_OR_UPDATE_FORM = "usuarios/createOrUpdateUsuarioForm";
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
	
	@GetMapping(value = "/usuarios/{usuarioId}/edit")
	public String initUpdateUsuarioForm(@PathVariable("usuarioId") int usuarioId, Model model) {
		Usuario usuario = this.usuarioService.findUsuarioById(usuarioId);
		model.addAttribute(usuario);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/usuarios/{usuarioId}/edit")
	public String processUpdateUsuarioForm(@Valid Usuario usuario, BindingResult result,
			@PathVariable("usuarioId") int usuarioId) {
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		else {
			usuario.setId(usuarioId);
			this.usuarioService.saveUsuario(usuario);
			return "redirect:/usuarios/{usuarioId}";
		}
	}
	
	@GetMapping(value = "/usuarios/new")
	public String crearUsuario(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USUARIO_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.usuarioService.saveUsuario(usuario);
			return "redirect:/";
		}
	}

}