
package com.example.proyectofinal.service;

import com.example.proyectofinal.dto.VentaClienteDTO;
import com.example.proyectofinal.dto.VentaProductoDTO;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Producto;
import com.example.proyectofinal.model.Venta;
import com.example.proyectofinal.repository.IClienteRepository;
import com.example.proyectofinal.repository.IProductoRepository;
import com.example.proyectofinal.repository.IVentaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

	@Autowired
	private IVentaRepository ventaRepo;
	@Autowired
	private IProductoRepository productoRepo;
	@Autowired
	private IClienteRepository clienteRepo;

	@Override
	public Venta saveVenta(VentaProductoDTO ventaProductoDTO) {
		Venta venta = new Venta();
		if (ventaProductoDTO.getListaProductos().size() == 0) {
			throw new IllegalArgumentException("La venta no tiene productos asociados");
		}
		Cliente cliente = clienteRepo.findById(ventaProductoDTO.getUnCliente().getId_cliente()).orElse(null);
		if (cliente == null) {
			throw new Error("El cliente no existe");
		}
		List<Producto> productos = ventaProductoDTO.getListaProductos();
		Double precioTotalProductos = 0.0;

		for (Producto producto : productos) {
			Producto buscado = productoRepo.findById(producto.getCodigo_producto()).orElse(null);
			if (buscado == null) {
				throw new Error("El producto no existe");
			} else if (buscado.getCantidad_disponible() == 0) {
				throw new Error("No hay stock para el producto " + producto.getCodigo_producto());
			}
			precioTotalProductos += buscado.getCosto();
			Double cantidadDisponible = buscado.getCantidad_disponible();
			Double cantidadDisponibleActualizada = cantidadDisponible - 1;
			buscado.setCantidad_disponible(cantidadDisponibleActualizada);
			productoRepo.save(buscado);
		}
		venta.setCodigo_venta(ventaProductoDTO.getCodigo_venta());
		venta.setFecha_venta(ventaProductoDTO.getFecha_venta());
		venta.setTotal(precioTotalProductos);
		venta.setListaProductos(ventaProductoDTO.getListaProductos());
		venta.setUnCliente(ventaProductoDTO.getUnCliente());

		this.ventaRepo.save(venta);
		return venta;
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
		this.ventaRepo.save(venta);

	}

	@Override
	public List<Double> ventasDelDia(LocalDate fecha_venta) {
		List<Double> res = new ArrayList<>();
		double monto = 0;
		double ventasEseDia = 0;
		for (Venta venta : this.getVentas()) {
			if (venta.getFecha_venta().compareTo(fecha_venta) == 0) {
				monto += venta.getTotal();
				ventasEseDia++;
			}
		}
		if (ventasEseDia == 0) {
			throw new Error("No hubo ventas en la fecha ingresada");
		}
		res.add(monto);
		res.add(ventasEseDia);

		return res;
	}

	@Override
	public VentaClienteDTO mayorVenta() {
		double max = 0;
		Venta ventaMax = null;
		for (Venta venta : getVentas()) {
			if (venta.getTotal() > max) {
				max = venta.getTotal();
				ventaMax = venta;
			}
		}
		VentaClienteDTO res = new VentaClienteDTO();

		res.setCodigo_venta(ventaMax.getCodigo_venta());
		res.setTotal(ventaMax.getTotal());
		res.setCantidadProductos(ventaMax.getListaProductos().size());
		res.setNombreCliente(ventaMax.getUnCliente().getNombre());
		res.setApellidoCliente(ventaMax.getUnCliente().getApellido());

		return res;
	}

}
