package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Pedido;
import com.SaborLocalSpa.saborlocal.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido obtener(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
    }

    public List<Pedido> obtenerPorClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> obtenerPorClienteEmail(String email) {
        return pedidoRepository.findByClienteEmail(email);
    }

    public List<Pedido> obtenerPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> obtenerPorProductoId(Long productoId) {
        return pedidoRepository.findByProductoId(productoId);
    }

    @Transactional
    public Pedido crear(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido actualizar(Long id, Pedido cambios) {
        Pedido existente = obtener(id);
        
        if (cambios.getCantidad() != null) {
            existente.setCantidad(cambios.getCantidad());
        }
        if (cambios.getTotal() != null) {
            existente.setTotal(cambios.getTotal());
        }
        if (cambios.getEstado() != null) {
            existente.setEstado(cambios.getEstado());
        }
        
        return pedidoRepository.save(existente);
    }

    @Transactional
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = obtener(id);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new NotFoundException("Pedido no encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
