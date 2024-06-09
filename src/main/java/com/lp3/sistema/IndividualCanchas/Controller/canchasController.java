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

import com.lp3.sistema.IndividualCanchas.Request.canchasRequest;
import com.lp3.sistema.IndividualCanchas.model.CanchasFutbol;
import com.lp3.sistema.IndividualCanchas.services.CanchasServices;


@RestController
@RequestMapping("/cancha")
public class canchasController {
    
    @Autowired
    
    CanchasServices canchasservices;

    @PostMapping("/nuevacancha")
    public void nuevaCancha(@RequestBody canchasRequest cancha) {
    	canchasservices.registrarCancha(cancha);
    }
    
    @GetMapping("/vercanchas")
    public List<CanchasFutbol> getcanchas() {
        return canchasservices.getcanchasfutbol();
        
    }
    
    @GetMapping("/vercancha/{idcanchafutbol}")
    public CanchasFutbol getCanchaid(@PathVariable int idcanchafutbol)
    {
    	return canchasservices.getCanchaid(idcanchafutbol);
    
    }
    
    @PutMapping("/actualizarcancha/{idcanchafutbol}")
    public void actualizarCancha(@PathVariable int idcanchafutbol, @RequestBody canchasRequest cancha) {
    	canchasservices.actualizarCanchas(idcanchafutbol, cancha);
    }
    
    @DeleteMapping("/eliminarcancha/{idcanchafutbol}")
    public void eliminarCancha(@PathVariable Integer idcanchafutbol) {
    	canchasservices.eliminarCanchas(idcanchafutbol);
    }

  }


