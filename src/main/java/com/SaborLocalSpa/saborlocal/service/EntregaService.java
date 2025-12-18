package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Entrega;
import com.SaborLocalSpa.saborlocal.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega obtenerPorId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entrega no encontrada"));
    }

    public List<Entrega> obtenerPorPedidoId(Long pedidoId) {
        return entregaRepository.findByPedidoId(pedidoId);
    }

    public List<Entrega> obtenerPorEstado(String estado) {
        return entregaRepository.findByEstado(estado);
    }

    @Transactional
    public Entrega crear(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Transactional
    public Entrega actualizar(Long id, Entrega entregaActualizada) {
        Entrega existente = obtenerPorId(id);
        
        existente.setDireccion(entregaActualizada.getDireccion());
        existente.setFechaEntrega(entregaActualizada.getFechaEntrega());
        existente.setHoraEstimada(entregaActualizada.getHoraEstimada());
        existente.setNotas(entregaActualizada.getNotas());
        existente.setEstado(entregaActualizada.getEstado());
        
        return entregaRepository.save(existente);
    }

    @Transactional
    public Entrega actualizarEstado(Long id, String nuevoEstado) {
        Entrega entrega = obtenerPorId(id);
        entrega.setEstado(nuevoEstado);
        return entregaRepository.save(entrega);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!entregaRepository.existsById(id)) {
            throw new NotFoundException("Entrega no encontrada");
        }
        entregaRepository.deleteById(id);
    }
}
