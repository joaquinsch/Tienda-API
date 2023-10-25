
package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Cliente;
import java.util.List;

/**
 *
 * @author Joaco
 */
public interface IClienteService {
    public void crearCliente(Cliente cliente);
    public List<Cliente> traerClientes();
    public Cliente traerCliente(Long id);
    public void eliminarCliente(Long id);
    //public  void editarCliente(Long id_cliente, Long nuevoId, String nuevoNombre, String nuevoApellido, String nuevoDni);
    public void editarCliente(Cliente cliente);
    
    
}
