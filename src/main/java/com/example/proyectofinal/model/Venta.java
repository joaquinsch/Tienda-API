package com.example.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codigo_venta;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate fecha_venta;
	private Double total;

	@ManyToMany
	@JoinTable(name = "rel_productos_ventas", joinColumns = @JoinColumn(name = "id_venta", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_producto", nullable = false))
	private List<Producto> listaProductos;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente unCliente;

	public Venta() {
	}

	public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos,
			Cliente unCliente) {
		/*if (!Pattern.matches("\\d{2}-\\d{2}-\\d{4}", fecha_venta.toString())) {
			throw new IllegalArgumentException("La fecha debe tener el formato 'dd-MM-yyyy'");
		}*/
		this.codigo_venta = codigo_venta;

		this.fecha_venta = fecha_venta;
		this.total = total;
		this.listaProductos = listaProductos;
		this.unCliente = unCliente;
	}

}
