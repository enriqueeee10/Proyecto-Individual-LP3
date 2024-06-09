package com.lp3.sistema.IndividualCanchas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp3.sistema.IndividualCanchas.Request.CanchasRequest;
import com.lp3.sistema.IndividualCanchas.model.CanchasFutbol;
import com.lp3.sistema.IndividualCanchas.services.CanchasServices;


@RestController
@RequestMapping("/cancha")
public class canchasController {

    @Autowired
    CanchasServices canchasServices;

    @PostMapping("/nuevacancha")
    public void nuevaCancha(@RequestBody CanchasRequest canchas) {
    	canchasServices.registrarCancha(canchas);
    }
    
    @GetMapping("/vercanchas")
    public List<CanchasFutbol> getCanchasFutbols() {
        return canchasServices.getCanchasFutbols();
    }
}

