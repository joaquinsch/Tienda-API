package tests;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.example.proyectofinal.controller.VentaController;
import com.example.proyectofinal.dto.VentaProductoDTO;
import com.example.proyectofinal.model.Cliente;
import com.example.proyectofinal.model.Producto;
import com.example.proyectofinal.model.Venta;
import com.example.proyectofinal.service.IClienteService;
import com.example.proyectofinal.service.IProductoService;
import com.example.proyectofinal.service.IVentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)

public class VentaControllerTests {
	@InjectMocks
	private VentaController ventaController;

	@Mock
	private IProductoService iProductoService;

	@Mock
	private IClienteService iClienteService;

	@Mock
	private IVentaService iVentaService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ventaController).build();
		// MockitoAnnotations.openMocks(this);
		this.objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void traerTodasLasVentas() throws JsonProcessingException, Exception {
		Venta venta = new Venta(1L, LocalDate.of(2024, 12, 20), Double.valueOf(2000), new ArrayList<>(), null);
		List<Venta> ventas = new ArrayList<>();
		ventas.add(venta);
		Mockito.when(this.iVentaService.getVentas()).thenReturn(ventas);
		Assertions.assertEquals(1, ventas.size());
		mockMvc.perform(MockMvcRequestBuilders.get("/ventas/traertodas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(ventas))
				)
				.andExpect(status().isOk());
	}
	@Test
	public void crearUnaVenta() throws Exception {

		// Crea el cliente
		Cliente cliente = new Cliente(1L, "José", "Pérez", "42147129", new ArrayList<>());

		// Crea la única instancia de VentaProductoDTO
		VentaProductoDTO ventaDTO = new VentaProductoDTO(1L, LocalDate.of(2024, 12, 20), new ArrayList<>(), cliente);

		// Crea el producto y lo añades a la ventaDTO
		Producto prod = new Producto(1L, "coca", "coca cola", Double.valueOf(2000), Double.valueOf(100),
				new ArrayList<>());
		ventaDTO.getListaProductos().add(prod); // Aquí agregas el producto a la lista de productos de ventaDTO

		// Crea la venta que se espera que se retorne
		Venta venta = new Venta(1L, LocalDate.of(2024, 12, 20), Double.valueOf(2000), new ArrayList<>(), cliente);

		// Asegúrate de usar la misma instancia en el stubbing
		Mockito.when(iVentaService.saveVenta(Mockito.any(VentaProductoDTO.class))).thenReturn(venta);

		// Convierte la ventaDTO a JSON para la solicitud
		String ventaJson = objectMapper.writeValueAsString(ventaDTO);

		// Realiza la petición y verifica la respuesta usando la misma instancia de
		// ventaDTO
		mockMvc.perform(
				MockMvcRequestBuilders.post("/ventas/crear").contentType(MediaType.APPLICATION_JSON)
				.content(ventaJson))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string("Se ha creado"));
	}


	@Test
	public void deberiaDarErrorSiCreaVentaSinProductos() throws Exception {
		Cliente cliente = new Cliente(1L, "José", "Pérez", "42147129", new ArrayList<>());
		VentaProductoDTO ventaDTO = new VentaProductoDTO(1L, LocalDate.of(2024, 12, 20), new ArrayList<>(), cliente);
		// ventaDTO.getListaProductos().add(new Producto());
		// Venta venta = new Venta(1L, LocalDate.of(2024, 12, 20), 0.0, new
		// ArrayList<>(), cliente);
		// Mockito.when(iVentaService.saveVenta(ventaDTO)).thenThrow(new
		// IllegalArgumentException("La venta no tiene productos asociados"));
		Mockito.doThrow(new IllegalArgumentException("La venta no tiene productos asociados")).when(iVentaService)
				.saveVenta(Mockito.any(VentaProductoDTO.class));
		// Assertions.assertThrows(IllegalArgumentException.class, () ->
		// iVentaService.saveVenta(ventaDTO));
		String ventaJSON = objectMapper.writeValueAsString(ventaDTO);

		Assertions.assertEquals(0, ventaDTO.getListaProductos().size());

		mockMvc.perform(
				MockMvcRequestBuilders.post("/ventas/crear").contentType(MediaType.APPLICATION_JSON).content(ventaJSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string("La venta no tiene productos asociados"));
	}
}
