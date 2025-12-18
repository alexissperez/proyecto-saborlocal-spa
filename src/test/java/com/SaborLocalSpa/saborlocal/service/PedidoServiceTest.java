package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Pedido;
import com.SaborLocalSpa.saborlocal.model.Cliente;
import com.SaborLocalSpa.saborlocal.model.Producto;
import com.SaborLocalSpa.saborlocal.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("PedidoService Tests")
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private Cliente cliente;
    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setEmail("juan@example.com");

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Tomate");

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setProducto(producto);
        pedido.setCantidad(5);
        pedido.setTotal(BigDecimal.valueOf(250.00));
        pedido.setEstado("PENDIENTE");
        pedido.setFecha(LocalDateTime.now());
    }

    @Test
    @DisplayName("Debe listar todos los pedidos")
    void testListar() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener pedido por ID")
    void testObtener() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.obtener(1L);

        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals(5, resultado.getCantidad());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al obtener pedido inexistente")
    void testObtenerNoEncontrado() {
        when(pedidoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            pedidoService.obtener(999L);
        });
        verify(pedidoRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe obtener pedidos por cliente ID")
    void testObtenerPorClienteId() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(pedidoRepository.findByClienteId(1L)).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.obtenerPorClienteId(1L);

        assertEquals(1, resultado.size());
        verify(pedidoRepository, times(1)).findByClienteId(1L);
    }

    @Test
    @DisplayName("Debe obtener pedidos por estado")
    void testObtenerPorEstado() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(pedidoRepository.findByEstado("PENDIENTE")).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.obtenerPorEstado("PENDIENTE");

        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
        verify(pedidoRepository, times(1)).findByEstado("PENDIENTE");
    }

    @Test
    @DisplayName("Debe obtener pedidos por email de cliente")
    void testObtenerPorClienteEmail() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);
        when(pedidoRepository.findByClienteEmail("juan@example.com")).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.obtenerPorClienteEmail("juan@example.com");

        assertEquals(1, resultado.size());
        verify(pedidoRepository, times(1)).findByClienteEmail("juan@example.com");
    }

    @Test
    @DisplayName("Debe crear un pedido")
    void testCrear() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.crear(pedido);

        assertNotNull(resultado);
        assertEquals(5, resultado.getCantidad());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("Debe actualizar un pedido")
    void testActualizar() {
        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setCantidad(10);
        pedidoActualizado.setTotal(BigDecimal.valueOf(500.00));
        pedidoActualizado.setEstado("CONFIRMADO");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.actualizar(1L, pedidoActualizado);

        assertNotNull(resultado);
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("Debe actualizar estado de un pedido")
    void testActualizarEstado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.actualizarEstado(1L, "ENVIADO");

        assertNotNull(resultado);
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    @DisplayName("Debe eliminar un pedido")
    void testEliminar() {
        when(pedidoRepository.existsById(1L)).thenReturn(true);

        pedidoService.eliminar(1L);

        verify(pedidoRepository, times(1)).existsById(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al eliminar pedido inexistente")
    void testEliminarNoEncontrado() {
        when(pedidoRepository.existsById(999L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            pedidoService.eliminar(999L);
        });
        verify(pedidoRepository, times(1)).existsById(999L);
    }
}
