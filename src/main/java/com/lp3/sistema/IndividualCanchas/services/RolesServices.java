package com.lp3.sistema.IndividualCanchas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp3.sistema.IndividualCanchas.model.Roles;
import com.lp3.sistema.IndividualCanchas.repository.RolRepository;


@Service
public class RolesServices {
	
	@Autowired
	RolRepository repoRol;
	
	public void nuevoRol (Roles rol) {
		repoRol.save(rol);
	}

}
