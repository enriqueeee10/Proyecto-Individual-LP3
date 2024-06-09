package com.lp3.sistema.IndividualCanchas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp3.sistema.IndividualCanchas.Request.clienteRequest;
import com.lp3.sistema.IndividualCanchas.model.Clientes;
import com.lp3.sistema.IndividualCanchas.repository.ClienteRepository;

@Service
public class ClientesServices {
    
    @Autowired
    ClienteRepository clienteRepo;
    
    public void registrarCliente (clienteRequest cliente) {
        
        Clientes nuevocliente = new Clientes();
        
        nuevocliente.setIdcliente(cliente.getIdcliente());
        nuevocliente.setDni(cliente.getDni());
        nuevocliente.setNombre(cliente.getNombre());
        nuevocliente.setApellido(cliente.getApellido());
        nuevocliente.setCorreo(cliente.getCorreo());
        nuevocliente.setDireccion(cliente.getDireccion());
        nuevocliente.setTelefono(cliente.getTelefono());
        clienteRepo.save(nuevocliente);
    }
    
    public List<Clientes> getclientes() {
        return clienteRepo.findAll();
    }

    public void actualizarCliente(int idCliente, clienteRequest cliente) {
        Optional<Clientes> optionalCliente = clienteRepo.findById(idCliente);
        if (optionalCliente.isPresent()) {
            Clientes clienteExistente = optionalCliente.get();
            clienteExistente.setDni(cliente.getDni());
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setCorreo(cliente.getCorreo());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setTelefono(cliente.getTelefono());
            clienteRepo.save(clienteExistente);
        } else {
            // Manejar el caso en que el cliente no exista
        }
    }

    public void eliminarCliente(int idCliente) {
        Optional<Clientes> clienteOptional = clienteRepo.findById(idCliente);
        if (clienteOptional.isPresent()) {
            clienteRepo.deleteById(idCliente);
        } else {
            // Manejar el caso en que el cliente no exista
        }
    }

	public Clientes getClienteid(Integer idcliente) {
		return clienteRepo.findById(idcliente).orElseThrow(()-> new RuntimeException("Error"));
	}

}

