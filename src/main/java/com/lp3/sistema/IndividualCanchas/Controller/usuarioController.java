package com.lp3.sistema.IndividualCanchas.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp3.sistema.IndividualCanchas.Request.usuarioRequest;
import com.lp3.sistema.IndividualCanchas.model.Usuario;
import com.lp3.sistema.IndividualCanchas.services.UsuarioServices;

@RestController
@RequestMapping("/usuario")
public class usuarioController {
	
	@Autowired
	UsuarioServices usuarioservices;
	
	

	@PostMapping("/nuevousuario")
	public void nuevoRol(@RequestBody usuarioRequest usuario) {
		usuarioservices.registrarUsuario(usuario);

	}
	
    @GetMapping("/verusuarios")
    public List<Usuario> getusuario() {
        return usuarioservices.getusuario();
        
    }
    
    
    @GetMapping("/verusuario/{idusuario}")
    public usuarioRequest getUsuarioid(@PathVariable int idusuario)
    {
    	return usuarioservices.getUsuarioid(idusuario);
    
    }
	
	 @PutMapping("/actualizarusuario/{idusuario}")
	    public void actualizarUsuario(@PathVariable int idusuario, @RequestBody usuarioRequest usuario) {
		 usuarioservices.actualizarUsuario(idusuario, usuario);
	    }

	    @DeleteMapping("/eliminarusuario/{idusuario}")
	    public void eliminarUsuario(@PathVariable Integer idusuario) {
	    	usuarioservices.eliminarUsuario(idusuario);
	    }
	
	
}