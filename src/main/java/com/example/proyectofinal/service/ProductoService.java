
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

	@Override
	public void agregarStock(Long id, Double cantidad) {
		Producto prod = this.traerProducto(id);
		if (prod == null) {
			throw new IllegalArgumentException("No existe el producto con el id: " + id);
		}
		prod.setCantidad_disponible(prod.getCantidad_disponible() + cantidad);
		this.productoRepo.save(prod);
	}
}
