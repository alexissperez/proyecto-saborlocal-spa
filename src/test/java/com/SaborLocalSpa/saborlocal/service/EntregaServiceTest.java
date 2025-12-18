package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Entrega;
import com.SaborLocalSpa.saborlocal.model.Pedido;
import com.SaborLocalSpa.saborlocal.repository.EntregaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("EntregaService Tests")
class EntregaServiceTest {

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaService entregaService;

    private Entrega entrega;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        pedido = new Pedido();
        pedido.setId(1L);

        entrega = new Entrega();
        entrega.setId(1L);
        entrega.setPedido(pedido);
        entrega.setDireccion("Calle Principal 123");
        entrega.setFechaEntrega(LocalDateTime.now());
        entrega.setHoraEstimada(LocalTime.of(14, 30));
        entrega.setNotas("Entregar en puerta");
        entrega.setEstado("PENDIENTE");
    }

    @Test
    @DisplayName("Debe listar todas las entregas")
    void testListar() {
        List<Entrega> entregas = new ArrayList<>();
        entregas.add(entrega);
        when(entregaRepository.findAll()).thenReturn(entregas);

        List<Entrega> resultado = entregaService.listar();

        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
        verify(entregaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener entrega por ID")
    void testObtenerPorId() {
        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));

        Entrega resultado = entregaService.obtenerPorId(1L);

        assertEquals("Calle Principal 123", resultado.getDireccion());
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(entregaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al obtener entrega inexistente")
    void testObtenerPorIdNoEncontrado() {
        when(entregaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            entregaService.obtenerPorId(999L);
        });
        verify(entregaRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe obtener entregas por pedido ID")
    void testObtenerPorPedidoId() {
        List<Entrega> entregas = new ArrayList<>();
        entregas.add(entrega);
        when(entregaRepository.findByPedidoId(1L)).thenReturn(entregas);

        List<Entrega> resultado = entregaService.obtenerPorPedidoId(1L);

        assertEquals(1, resultado.size());
        verify(entregaRepository, times(1)).findByPedidoId(1L);
    }

    @Test
    @DisplayName("Debe obtener entregas por estado")
    void testObtenerPorEstado() {
        List<Entrega> entregas = new ArrayList<>();
        entregas.add(entrega);
        when(entregaRepository.findByEstado("PENDIENTE")).thenReturn(entregas);

        List<Entrega> resultado = entregaService.obtenerPorEstado("PENDIENTE");

        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
        verify(entregaRepository, times(1)).findByEstado("PENDIENTE");
    }

    @Test
    @DisplayName("Debe crear una entrega")
    void testCrear() {
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        Entrega resultado = entregaService.crear(entrega);

        assertNotNull(resultado);
        assertEquals("Calle Principal 123", resultado.getDireccion());
        verify(entregaRepository, times(1)).save(any(Entrega.class));
    }

    @Test
    @DisplayName("Debe actualizar una entrega")
    void testActualizar() {
        Entrega entregaActualizada = new Entrega();
        entregaActualizada.setDireccion("Calle Secundaria 456");
        entregaActualizada.setEstado("EN_RUTA");
        entregaActualizada.setNotas("Entregar antes de las 6 PM");

        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        Entrega resultado = entregaService.actualizar(1L, entregaActualizada);

        assertNotNull(resultado);
        verify(entregaRepository, times(1)).findById(1L);
        verify(entregaRepository, times(1)).save(any(Entrega.class));
    }

    @Test
    @DisplayName("Debe actualizar estado de una entrega")
    void testActualizarEstado() {
        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));
        when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);

        Entrega resultado = entregaService.actualizarEstado(1L, "ENTREGADO");

        assertNotNull(resultado);
        verify(entregaRepository, times(1)).findById(1L);
        verify(entregaRepository, times(1)).save(any(Entrega.class));
    }

    @Test
    @DisplayName("Debe eliminar una entrega")
    void testEliminar() {
        when(entregaRepository.existsById(1L)).thenReturn(true);

        entregaService.eliminar(1L);

        verify(entregaRepository, times(1)).existsById(1L);
        verify(entregaRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al eliminar entrega inexistente")
    void testEliminarNoEncontrado() {
        when(entregaRepository.existsById(999L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            entregaService.eliminar(999L);
        });
        verify(entregaRepository, times(1)).existsById(999L);
    }
}
