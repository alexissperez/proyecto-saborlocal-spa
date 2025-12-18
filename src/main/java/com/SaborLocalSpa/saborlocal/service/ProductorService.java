package com.SaborLocalSpa.saborlocal.service;

import com.SaborLocalSpa.saborlocal.exception.NotFoundException;
import com.SaborLocalSpa.saborlocal.model.Productor;
import com.SaborLocalSpa.saborlocal.repository.ProductorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductorService {

    private final ProductorRepository productorRepository;

    public ProductorService(ProductorRepository productorRepository) {
        this.productorRepository = productorRepository;
    }

    public List<Productor> listar() {
        return productorRepository.findAll();
    }

    public Productor obtenerPorId(Long id) {
        return productorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Productor no encontrado"));
    }

    public Productor obtenerPorEmail(String email) {
        return productorRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Productor no encontrado"));
    }

    public List<Productor> buscarPorNombre(String nombre) {
        return productorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Productor> obtenerActivos() {
        return productorRepository.findByActivoTrue();
    }

    @Transactional
    public Productor crear(Productor productor) {
        return productorRepository.save(productor);
    }

    @Transactional
    public Productor actualizar(Long id, Productor productorActualizado) {
        Productor existente = obtenerPorId(id);
        
        existente.setNombre(productorActualizado.getNombre());
        existente.setEmail(productorActualizado.getEmail());
        existente.setTelefono(productorActualizado.getTelefono());
        existente.setEmpresa(productorActualizado.getEmpresa());
        existente.setRegistroSanitario(productorActualizado.getRegistroSanitario());
        existente.setActivo(productorActualizado.getActivo());
        
        return productorRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productorRepository.existsById(id)) {
            throw new NotFoundException("Productor no encontrado");
        }
        productorRepository.deleteById(id);
    }
}
