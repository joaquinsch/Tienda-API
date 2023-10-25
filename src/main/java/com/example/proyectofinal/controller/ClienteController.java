package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joaco
 */
@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "Se ha creado el cliente";
    }

    @GetMapping("/clientes/clientes")
    public List<Cliente> traerClientes() {
        return clienteService.traerClientes();
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente traerCliente(@PathVariable Long id_cliente) {
        return clienteService.traerCliente(id_cliente);
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente) {
        this.clienteService.eliminarCliente(id_cliente);
        return "Se ha eliminado el cliente";
    }

    // el editar siempre toma como parametro un Cliente pero tmb se puede tomar el id y buscarlo
    /*@PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editarCliente(@PathVariable Long id_cliente,
            @RequestParam(required = false, name = "id_cliente") Long nuevo_id,
            @RequestParam(required = false, name = "nombre") String nuevoNombre,
            @RequestParam(required = false, name = "apellido") String nuevoApellido,
            @RequestParam(required = false, name = "dni") String nuevoDni) {

        this.clienteService.editarCliente(id_cliente, nuevo_id, nuevoNombre, nuevoApellido, nuevoDni);
        
        Cliente c = this.traerCliente(nuevo_id);
        return c;

    }*/
    
    @PutMapping("/clientes/editar")
    public Cliente editarCliente(@RequestBody Cliente cliente){
        this.clienteService.editarCliente(cliente);
        return this.clienteService.traerCliente(cliente.getId_cliente());
    }

}
