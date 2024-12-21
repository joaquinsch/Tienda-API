/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinal.controller;

import com.example.proyectofinal.dto.VentaClienteDTO;
import com.example.proyectofinal.model.Venta;
import com.example.proyectofinal.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	}

	@GetMapping("/ventas/traer/{codigo_venta}")
	public Venta traerVenta(@PathVariable Long codigo_venta) {
		return this.ventaServ.getVenta(codigo_venta);
	}

	@PostMapping("/ventas/crear")
	public String crearVenta(@RequestBody Venta venta) {
		try {
			this.ventaServ.saveVenta(venta);
			return "Se ha creado";
		} catch (Exception e) {
			return e.getMessage();
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
