package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Producto;
import com.SaborLocalSpa.saborlocal.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ProductoService Tests")
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Tomate");
        producto.setDescripcion("Tomate rojo fresco");
        producto.setCategoria("Verduras");
        producto.setPrecio(BigDecimal.valueOf(50.00));
        producto.setStock(100);
        producto.setDisponible(true);
    }

    @Test
    @DisplayName("Debe listar todos los productos")
    void testListar() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Tomate", resultado.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar producto por ID")
    void testBuscarPorId() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = productoService.buscarPorId(1L);

        assertEquals("Tomate", resultado.getNombre());
        assertEquals(BigDecimal.valueOf(50.00), resultado.getPrecio());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al buscar producto inexistente")
    void testBuscarPorIdNoEncontrado() {
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productoService.buscarPorId(999L);
        });
        verify(productoRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe buscar productos por nombre")
    void testBuscarPorNombre() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        when(productoRepository.findByNombreContainingIgnoreCase("Tomate")).thenReturn(productos);

        List<Producto> resultado = productoService.buscarPorNombre("Tomate");

        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findByNombreContainingIgnoreCase("Tomate");
    }

    @Test
    @DisplayName("Debe buscar productos disponibles")
    void testBuscarDisponibles() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        when(productoRepository.findByDisponibleTrue()).thenReturn(productos);

        List<Producto> resultado = productoService.buscarDisponibles();

        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findByDisponibleTrue();
    }

    @Test
    @DisplayName("Debe buscar productos por rango de precios")
    void testBuscarPorRangoPrecios() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        BigDecimal min = BigDecimal.valueOf(40);
        BigDecimal max = BigDecimal.valueOf(60);
        when(productoRepository.findByPrecioBetween(min, max)).thenReturn(productos);

        List<Producto> resultado = productoService.buscarPorRangoPrecios(min, max);

        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findByPrecioBetween(min, max);
    }

    @Test
    @DisplayName("Debe buscar productos por categoría")
    void testBuscarPorCategoria() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        when(productoRepository.findByCategoriaIgnoreCase("Verduras")).thenReturn(productos);

        List<Producto> resultado = productoService.buscarPorCategoria("Verduras");

        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findByCategoriaIgnoreCase("Verduras");
    }

    @Test
    @DisplayName("Debe crear un producto")
    void testCrear() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crear(producto);

        assertNotNull(resultado);
        assertEquals("Tomate", resultado.getNombre());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe actualizar un producto")
    void testActualizar() {
        Producto productoActualizado = new Producto();
        productoActualizado.setNombre("Tomate Orgánico");
        productoActualizado.setPrecio(BigDecimal.valueOf(75.00));
        productoActualizado.setStock(50);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.actualizar(1L, productoActualizado);

        assertNotNull(resultado);
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe eliminar un producto")
    void testEliminar() {
        when(productoRepository.existsById(1L)).thenReturn(true);

        productoService.eliminar(1L);

        verify(productoRepository, times(1)).existsById(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al eliminar producto inexistente")
    void testEliminarNoEncontrado() {
        when(productoRepository.existsById(999L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            productoService.eliminar(999L);
        });
        verify(productoRepository, times(1)).existsById(999L);
    }
}
