package com.lp3.sistema.IndividualCanchas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp3.sistema.IndividualCanchas.model.Roles;
import com.lp3.sistema.IndividualCanchas.repository.RolRepository;

@RestController
@RequestMapping("/rol")
public class rolesController {
    
    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/nuevorol")
    public void nuevoRol(@RequestBody Roles rol) {
        rolRepository.save(rol);
    }
}
