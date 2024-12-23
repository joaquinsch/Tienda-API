
package com.example.proyectofinal.service;

import com.example.proyectofinal.dto.VentaClienteDTO;
import com.example.proyectofinal.dto.VentaProductoDTO;
import com.example.proyectofinal.model.Venta;

import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author Joaco
 */


public interface IVentaService {
    public void saveVenta(VentaProductoDTO ventaProductoDTO);
    public List<Venta> getVentas();
    public Venta getVenta(Long codigo_venta);
    public void deleteVenta(Long codigo_venta);
    public void editVenta(Venta venta);
    public List<Double> ventasDelDia(LocalDate fecha_venta);
    public VentaClienteDTO mayorVenta();
    
}
