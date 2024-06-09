package com.lp3.sistema.IndividualCanchas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp3.sistema.IndividualCanchas.model.Usuario;
import com.lp3.sistema.IndividualCanchas.repository.UsuarioRepository;


@Service
public class LoginServices {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean inicio(String correo, String pass) {
        List<Usuario> usuarios = usuarioRepository.findByCorreo(correo);
        
        if (usuarios.isEmpty()) {
            return false;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getPass().equals(pass)) {
                return true;
            }
        }

        return false;
    }
}

