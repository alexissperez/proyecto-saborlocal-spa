package com.SaborLocalSpa.saborlocal.repository;

import com.SaborLocalSpa.saborlocal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    List<Producto> findByProductorId(Long productorId);
    
    List<Producto> findByDisponibleTrue();
    
    List<Producto> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
    
    List<Producto> findByCategoriaIgnoreCase(String categoria);
}
