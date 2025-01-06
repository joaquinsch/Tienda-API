/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.VentaClienteDTO;
import com.example.proyectofinal.dto.VentaProductoDTO;
import com.example.proyectofinal.model.Venta;
import com.example.proyectofinal.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joaco
 */

@RestController
public class VentaController {

	@Autowired
	private IVentaService ventaServ;

	@GetMapping("/ventas/traertodas")
	public List<Venta> traerVentas() {
		return ventaServ.getVentas();
		/*
		 * try { return ResponseEntity.ok(ventaServ.getVentas());
		 * 
		 * } catch (Exception e) { return
		 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo salió mal"
		 * ); }
		 */
	}

	@GetMapping("/ventas/traer/{codigo_venta}")
	public Venta traerVenta(@PathVariable Long codigo_venta) {
		return this.ventaServ.getVenta(codigo_venta);
	}

	/**
	 * @throws error si el cliente o algun producto no existe
	 * 
	 * 
	 *               Ejemplo del body de una venta: { "fecha_venta": "21-12-2024",
	 *               "total": 500, "listaProductos": [ { "codigo_producto": 102 } ],
	 *               "unCliente": { "id_cliente": 52 } }
	 * 
	 */
	@PostMapping("/ventas/crear")
	public ResponseEntity<String> crearVenta(@RequestBody VentaProductoDTO ventaProductoDTO) {
		try {
			this.ventaServ.saveVenta(ventaProductoDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Error e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error");
		}
	}

	@DeleteMapping("/ventas/eliminar/{codigo_venta}")
	public String eliminarVenta(@PathVariable Long codigo_venta) {
		this.ventaServ.deleteVenta(codigo_venta);
		return "Se ha eliminado";
	}

	@PutMapping("/ventas/editar")
	public Venta editarVenta(@RequestBody Venta venta) {
		this.ventaServ.editVenta(venta);
		return this.ventaServ.getVenta(venta.getCodigo_venta());
	}

	@GetMapping("/ventas/{fecha_venta}")
	public List<Double> ventasDelDia(@PathVariable LocalDate fecha_venta) {
		try {
			List<Double> ventas = this.ventaServ.ventasDelDia(fecha_venta);
			return ventas;
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	@GetMapping("/ventas/mayor_venta")
	@ResponseBody
	public VentaClienteDTO mayorVenta() {
		return this.ventaServ.mayorVenta();
	}
}
