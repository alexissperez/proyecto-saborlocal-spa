package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Cliente;
import com.SaborLocalSpa.saborlocal.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ClienteService Tests")
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Pérez");
        cliente.setEmail("juan@example.com");
        cliente.setPassword("password123");
        cliente.setTelefono("123456789");
        cliente.setDireccion("Calle 123");
        cliente.setRol("USER");
    }

    @Test
    @DisplayName("Debe listar todos los clientes")
    void testListar() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultado = clienteService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener cliente por ID")
    void testObtenerPorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.obtenerPorId(1L);

        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals("juan@example.com", resultado.getEmail());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al obtener cliente inexistente")
    void testObtenerPorIdNoEncontrado() {
        when(clienteRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            clienteService.obtenerPorId(999L);
        });
        verify(clienteRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe obtener cliente por email")
    void testObtenerPorEmail() {
        when(clienteRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.obtenerPorEmail("juan@example.com");

        assertEquals("Juan Pérez", resultado.getNombre());
        verify(clienteRepository, times(1)).findByEmail("juan@example.com");
    }

    @Test
    @DisplayName("Debe buscar clientes por nombre")
    void testBuscarPorNombre() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findByNombreContainingIgnoreCase("Juan")).thenReturn(clientes);

        List<Cliente> resultado = clienteService.buscarPorNombre("Juan");

        assertEquals(1, resultado.size());
        verify(clienteRepository, times(1)).findByNombreContainingIgnoreCase("Juan");
    }

    @Test
    @DisplayName("Debe crear un cliente encriptando la contraseña")
    void testCrear() {
        when(passwordEncoder.encode("password123")).thenReturn("$2a$10$encrypted");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.crear(cliente);

        assertNotNull(resultado);
        verify(passwordEncoder, times(1)).encode("password123");
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe actualizar un cliente")
    void testActualizar() {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setNombre("Juan Carlos");
        clienteActualizado.setTelefono("987654321");
        clienteActualizado.setDireccion("Calle 456");
        clienteActualizado.setRol("ADMIN");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.actualizar(1L, clienteActualizado);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe eliminar un cliente")
    void testEliminar() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        clienteService.eliminar(1L);

        verify(clienteRepository, times(1)).existsById(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar NotFoundException al eliminar cliente inexistente")
    void testEliminarNoEncontrado() {
        when(clienteRepository.existsById(999L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            clienteService.eliminar(999L);
        });
        verify(clienteRepository, times(1)).existsById(999L);
    }
}
