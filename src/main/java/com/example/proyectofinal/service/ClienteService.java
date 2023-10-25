package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Joaco
 */
@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public void crearCliente(Cliente cliente) {
        this.clienteRepo.save(cliente);

    }

    @Override
    public List<Cliente> traerClientes() {
        return this.clienteRepo.findAll();

    }

    @Override
    public Cliente traerCliente(Long id) {
        return this.clienteRepo.findById(id).orElse(null);

    }

    @Override
    public void eliminarCliente(Long id) {
        this.clienteRepo.deleteById(id);
    }


// no funciona esto

    /*@Override
    public void editarCliente(Long id_cliente, Long nuevoId, String nuevoNombre, String nuevoApellido, String nuevoDni) {
        Cliente buscado = this.traerCliente(id_cliente);
        buscado.setId_cliente(nuevoId);
        buscado.setNombre(nuevoNombre);
        buscado.setApellido(nuevoApellido);
        buscado.setDni(nuevoDni);
        
        crearCliente(buscado);

    }*/
    @Override
    public void editarCliente(Cliente cliente) {
        this.crearCliente(cliente);
    }

}
