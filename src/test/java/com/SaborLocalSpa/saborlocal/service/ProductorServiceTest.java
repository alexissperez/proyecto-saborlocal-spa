package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Productor;
import com.SaborLocalSpa.saborlocal.repository.ProductorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ProductorService Tests")
class ProductorServiceTest {

    @Mock
    private ProductorRepository productorRepository;

    @InjectMocks
    private ProductorService productorService;

    private Productor productor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productor = new Productor();
        productor.setId(1L);
        productor.setNombre("Granja La Esperanza");
        productor.setEmail("granja@example.com");
        productor.setTelefono("555-1234");
        productor.setEmpresa("Agricultura Sostenible");
        productor.setRegistroSanitario("RS-2024-001");
        productor.setActivo(true);
    }

    @Test
    @DisplayName("Debe listar todos los productores")
    void testListar() {
        List<Productor> productores = new ArrayList<>();
        productores.add(productor);
        when(productorRepository.findAll()).thenReturn(productores);

        List<Productor> resultado = productorService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Granja La Esperanza", resultado.get(0).getNombre());
        verify(productorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener productor por ID")
    void testObtenerPorId() {
        when(productorRepository.findById(1L)).thenReturn(Optional.of(productor));

        Productor resultado = productorService.obtenerPorId(1L);

        assertEquals("Granja La Esperanza", resultado.getNombre());
        assertTrue(resultado.getActivo());
        verify(productorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al obtener productor inexistente")
    void testObtenerPorIdNoEncontrado() {
        when(productorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productorService.obtenerPorId(999L);
        });
        verify(productorRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe obtener productor por email")
    void testObtenerPorEmail() {
        when(productorRepository.findByEmail("granja@example.com")).thenReturn(Optional.of(productor));

        Productor resultado = productorService.obtenerPorEmail("granja@example.com");

        assertEquals("Granja La Esperanza", resultado.getNombre());
        verify(productorRepository, times(1)).findByEmail("granja@example.com");
    }

    @Test
    @DisplayName("Debe buscar productores por nombre")
    void testBuscarPorNombre() {
        List<Productor> productores = new ArrayList<>();
        productores.add(productor);
        when(productorRepository.findByNombreContainingIgnoreCase("Granja")).thenReturn(productores);

        List<Productor> resultado = productorService.buscarPorNombre("Granja");

        assertEquals(1, resultado.size());
        verify(productorRepository, times(1)).findByNombreContainingIgnoreCase("Granja");
    }

    @Test
    @DisplayName("Debe obtener productores activos")
    void testObtenerActivos() {
        List<Productor> productores = new ArrayList<>();
        productores.add(productor);
        when(productorRepository.findByActivoTrue()).thenReturn(productores);

        List<Productor> resultado = productorService.obtenerActivos();

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getActivo());
        verify(productorRepository, times(1)).findByActivoTrue();
    }

    @Test
    @DisplayName("Debe crear un productor")
    void testCrear() {
        when(productorRepository.save(any(Productor.class))).thenReturn(productor);

        Productor resultado = productorService.crear(productor);

        assertNotNull(resultado);
        assertEquals("Granja La Esperanza", resultado.getNombre());
        verify(productorRepository, times(1)).save(any(Productor.class));
    }

    @Test
    @DisplayName("Debe actualizar un productor")
    void testActualizar() {
        Productor productorActualizado = new Productor();
        productorActualizado.setNombre("Granja La Esperanza 2.0");
        productorActualizado.setEmpresa("Agricultura Sostenible Mejorada");
        productorActualizado.setActivo(true);

        when(productorRepository.findById(1L)).thenReturn(Optional.of(productor));
        when(productorRepository.save(any(Productor.class))).thenReturn(productor);

        Productor resultado = productorService.actualizar(1L, productorActualizado);

        assertNotNull(resultado);
        verify(productorRepository, times(1)).findById(1L);
        verify(productorRepository, times(1)).save(any(Productor.class));
    }

    @Test
    @DisplayName("Debe eliminar un productor")
    void testEliminar() {
        when(productorRepository.existsById(1L)).thenReturn(true);

        productorService.eliminar(1L);

        verify(productorRepository, times(1)).existsById(1L);
        verify(productorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al eliminar productor inexistente")
    void testEliminarNoEncontrado() {
        when(productorRepository.existsById(999L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            productorService.eliminar(999L);
        });
        verify(productorRepository, times(1)).existsById(999L);
    }
}
