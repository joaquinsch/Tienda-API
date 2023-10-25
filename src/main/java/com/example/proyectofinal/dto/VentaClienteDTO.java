
package com.example.proyectofinal.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Joaco
 */

@Getter @Setter
public class VentaClienteDTO {
    
    private Long codigo_venta;
    private double total;
    private int cantidadProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaClienteDTO() {
    }

    public VentaClienteDTO(Long codigo_venta, double total, int cantidadProductos, String nombreCliente, String apellidoCliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidadProductos = cantidadProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
    
    
    
    
    
}
