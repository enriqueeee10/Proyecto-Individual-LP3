package com.lp3.sistema.IndividualCanchas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp3.sistema.IndividualCanchas.Request.CanchasRequest;
import com.lp3.sistema.IndividualCanchas.model.CanchasFutbol;
import com.lp3.sistema.IndividualCanchas.repository.CanchasRepository;

@Service
public class CanchasServices {
	@Autowired
	CanchasRepository canchaRepo;
	
    
    public void registrarCancha (CanchasRequest cancha) {
        
        CanchasFutbol nuevacancha = new CanchasFutbol();
        
        nuevacancha.setIdcanchafutbol(cancha.getIdcanchafutbol());
        nuevacancha.setCodigo(cancha.getCodigo());
        nuevacancha.setImagen(cancha.getImagen());
        nuevacancha.setNombre(cancha.getNombre());
        nuevacancha.setDireccion(cancha.getDireccion());
        nuevacancha.setPrecio(cancha.getPrecio());
        canchaRepo.save(nuevacancha);
    }
    public List<CanchasFutbol> getCanchasFutbols() {
        return canchaRepo.findAll();
    }
    

    public void actualizarCanchas(int idcanchafutbol, CanchasRequest canchas) {
        Optional<CanchasFutbol> optionalCanchas = canchaRepo.findById(idcanchafutbol);
        if (optionalCanchas.isPresent()) {
            CanchasFutbol canchas1 = optionalCanchas.get();
            canchas1.setCodigo(canchas1.getCodigo());
            canchas1.setImagen(canchas1.getImagen());
            canchas1.setNombre(canchas1.getNombre());
            canchas1.setDireccion(canchas1.getDireccion());
            canchas1.setPrecio(canchas1.getPrecio());
            canchaRepo.save(canchas1);
        } else {
            // Manejar el caso en que el cliente no exista
        }
    }

    public void eliminarCanchas(int idcanchafutbol) {
        Optional<CanchasFutbol> canchaOptional = canchaRepo.findById(idcanchafutbol);
        if (canchaOptional.isPresent()) {
        	canchaRepo.deleteById(idcanchafutbol);
        } else {
            // Manejar el caso en que el cliente no exista
        }
    }
    
    

}
