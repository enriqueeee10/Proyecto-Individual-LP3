package com.lp3.sistema.IndividualCanchas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lp3.sistema.IndividualCanchas.Request.clienteRequest;
import com.lp3.sistema.IndividualCanchas.model.Clientes;


@RestController
@RequestMapping("/cliente")
public class clientesController {

    @Autowired
    ClientesRepository clientesRepository;

    @PostMapping("/nuevocliente")
    public void nuevoCliente(@RequestBody clienteRequest cliente) {
        Clientes nuevoCliente = new Clientes();
        nuevoCliente.setNombre(cliente.getNombre());
        nuevoCliente.setDireccion(cliente.getDireccion());
        nuevoCliente.setTelefono(cliente.getTelefono());
        // Asigna otros campos necesarios
        clientesRepository.save(nuevoCliente);
    }

    @GetMapping("/verclientes")
    public List<Clientes> getclientes() {
        return clientesRepository.findAll();
    }

    @GetMapping("/vercliente/{idcliente}")
    public Clientes getClienteid(@PathVariable int idcliente) {
        Optional<Clientes> clienteOpt = clientesRepository.findById(idcliente);
        return clienteOpt.orElse(null);
    }

    @PutMapping("/actualizarcliente/{idcliente}")
    public void actualizarCliente(@PathVariable int idcliente, @RequestBody clienteRequest cliente) {
        Optional<Clientes> clienteOpt = clientesRepository.findById(idcliente);
        if (clienteOpt.isPresent()) {
            Clientes clienteExistente = clienteOpt.get();
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setTelefono(cliente.getTelefono());
            // Actualiza otros campos necesarios
            clientesRepository.save(clienteExistente);
        }
    }

    @DeleteMapping("/eliminarcliente/{idcliente}")
    public void eliminarCliente(@PathVariable Integer idcliente) {
        clientesRepository.deleteById(idcliente);
    }
}
