
package com.example.proyectofinal.service;

import com.example.proyectofinal.model.Venta;
import com.example.proyectofinal.repository.IVentaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VentaService implements IVentaService {
    
    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public void saveVenta(Venta venta) {
        this.ventaRepo.save(venta);
        
        
    }

    @Override
    public List<Venta> getVentas() {
       return this.ventaRepo.findAll();
    }

    @Override
    public Venta getVenta(Long codigo_venta) {
        return this.ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        this.ventaRepo.deleteById(codigo_venta);
        
    }

    @Override
    public void editVenta(Venta venta) {
        this.saveVenta(venta);
        
    }
    
}
