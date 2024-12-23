package com.example.proyectofinal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_cliente;
	private String nombre;
	private String apellido;
	private String dni;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "unCliente")
	private List<Venta> ventasCliente;

	public Cliente() {
	}

	public Cliente(Long id_cliente, String nombre, String apellido, String dni) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

}
