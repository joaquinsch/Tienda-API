package com.example.proyectofinal.controller;

import com.example.proyectofinal.model.Producto;
import com.example.proyectofinal.service.IProductoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@PostMapping("/productos/crear")
	public String crearProducto(@RequestBody Producto producto) {
		this.productoService.crearProducto(producto);
		return "Se ha añadido el producto";
	}

	@GetMapping("/productos")
	public List<Producto> traerProductos() {
		return this.productoService.traerProductos();
	}

	@DeleteMapping("/productos/eliminar/{codigo_producto}")
	public String deleteProducto(@PathVariable Long codigo_producto) {
		this.productoService.deleteProducto(codigo_producto);
		return "Se ha eliminó el producto";
	}

	@PutMapping("/productos/editar")
	public Producto editarProducto(@RequestBody Producto producto) {
		this.productoService.editarProducto(producto);
		return this.productoService.traerProducto(producto.getCodigo_producto());
	}

	// punto 4 Obtener todos los productos cuya cantidad_disponible sea menor a 5
	@GetMapping("/productos/falta_stock")
	public List<Producto> faltaStock() {
		List<Producto> res = new ArrayList<>();
		for (Producto producto : this.traerProductos()) {
			if (producto.getCantidad_disponible() < 5) {
				res.add(producto);
			}
		}
		return res;
	}

	@PatchMapping("/productos/agregarstock/{id}/{cantidad}")
	public ResponseEntity<String> agregarStock(@PathVariable Long id, @PathVariable Double cantidad) {
		try {
			this.productoService.agregarStock(id, cantidad);
			return ResponseEntity.status(HttpStatus.OK).body("Se ha agregado la cantidad");
		}catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha ocurrido un error");
		}
	}

}
