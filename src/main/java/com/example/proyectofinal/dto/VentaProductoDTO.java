package com.example.proyectofinal.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Producto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/*
 * Esto es porque no se debe pasar 'total' a una venta al crearla.
 */
public class VentaProductoDTO {
	private Long codigo_venta;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fecha_venta;

	private List<Producto> listaProductos;

	private Cliente unCliente;
}
