package com.lp3.sistema.IndividualCanchas.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp3.sistema.IndividualCanchas.DTO.usuarioDTO;
import com.lp3.sistema.IndividualCanchas.Request.usuarioRequest;
import com.lp3.sistema.IndividualCanchas.model.Roles;
import com.lp3.sistema.IndividualCanchas.model.Usuario;
import com.lp3.sistema.IndividualCanchas.repository.PerfilRepository;
import com.lp3.sistema.IndividualCanchas.repository.RolRepository;
import com.lp3.sistema.IndividualCanchas.repository.UsuarioNuevoRepository;
import com.lp3.sistema.IndividualCanchas.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	
	@Autowired
	UsuarioNuevoRepository usuarionuevoRepo;
	
	@Autowired
	UsuarioRepository usuariorepo;
	
	@Autowired
	PerfilRepository perfilrepo;
	
	@Autowired
	RolRepository rolrepo;

	public void registrarUsuario (usuarioRequest usuario) {
		
		System.out.println(usuario.getRoles());
		
		Usuario nuevousuario = new Usuario();
		nuevousuario.setDni(usuario.getDni());		
		nuevousuario.setApellido(usuario.getApellido());
		nuevousuario.setCorreo(usuario.getCorreo());
		nuevousuario.setDireccion(usuario.getDireccion());
		Set<Roles> roles = new HashSet<>();
		Roles rol = rolrepo.findById(usuario.getRoles())
		.orElseThrow(()->new RuntimeException("No se encontró el rol"));
		roles.add(rol);
		nuevousuario.setRol(roles);
		nuevousuario.setNombre(usuario.getNombre());
		nuevousuario.setPass(usuario.getPass());
		nuevousuario.setTelefono(usuario.getTelefono());
		usuariorepo.save(nuevousuario);
		
	
	}
    public List<Usuario> getusuario() {
        return usuariorepo.findAll();
    }
		 
	public List<usuarioDTO> listarUsuarios ()
	{
		
		List<Usuario> usuario = usuariorepo.findAll();
		List<usuarioDTO> usuariodto =  new ArrayList<>();
		
		for(Usuario usuarioo : usuario) {
			
			usuarioDTO DTO = new usuarioDTO();
			DTO.setIdusuario(usuarioo.getIdusuario());
			DTO.setNombre(usuarioo.getNombre());
			DTO.setTelefono(usuarioo.getTelefono());
			DTO.setEstado(usuarioo.getEstado());
			
			usuariodto.add(DTO);
		}
		return usuariodto;
	}

	public void actualizarUsuario(int idusuario, usuarioRequest usuario) {
        Optional<Usuario> optionalUsuario = usuariorepo.findById(idusuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setDni(usuario.getDni());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setDireccion(usuario.getDireccion());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setPass(usuario.getPass());
            usuariorepo.save(usuarioExistente);
        } else {
            // Manejar el caso en que el cliente no exista
        }
	}
	

    public void eliminarUsuario(int idusuario) {
        Optional<Usuario> usuarioOptional = usuariorepo.findById(idusuario);
        if (usuarioOptional.isPresent()) {
        	usuariorepo.deleteById(idusuario);
        } else {
            // Manejar el caso en que el cliente no exista
        }
    }
    
	public usuarioRequest getUsuarioid(Integer idusuario) {
		Usuario user = usuariorepo.findById(idusuario).orElseThrow(()-> new RuntimeException("Error"));
		usuarioRequest dto = new usuarioRequest();
		dto.setDni(user.getDni());
		dto.setApellido(user.getApellido());
		dto.setCorreo(user.getCorreo());
		dto.setDireccion(user.getDireccion());
		dto.setNombre(user.getNombre());
		dto.setPass(user.getPass());
		dto.setRoles(1);
		dto.setTelefono(user.getTelefono());
		return dto;
	}
	
	
	
	
	
}
