
package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Producto;
import com.example.proyectofinal.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joaco
 */

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public void crearProducto(Producto producto) {
        this.productoRepo.save(producto);
    }

    @Override
    public List<Producto> traerProductos() {
        return this.productoRepo.findAll();
    }

    @Override
    public Producto traerProducto(Long id) {
        return this.productoRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProducto(Long id) {
        this.productoRepo.deleteById(id);
    }

    @Override
    public void editarProducto(Producto producto) {
        this.crearProducto(producto);
    }
    

    
    
}
