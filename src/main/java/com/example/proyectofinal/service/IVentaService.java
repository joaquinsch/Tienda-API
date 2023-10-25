
package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Venta;
import java.util.List;


/**
 *
 * @author Joaco
 */


public interface IVentaService {
    public void saveVenta(Venta venta);
    public List<Venta> getVentas();
    public Venta getVenta(Long codigo_venta);
    public void deleteVenta(Long codigo_venta);
    public void editVenta(Venta venta);
    
}
