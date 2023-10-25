
package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Producto;
import java.util.List;



public interface IProductoService{
        public void crearProducto(Producto producto);
        public List<Producto> traerProductos();
        public Producto traerProducto(Long id);
        public void deleteProducto(Long id);
        public void editarProducto(Producto producto);
        
        
        
    
    
    
}
