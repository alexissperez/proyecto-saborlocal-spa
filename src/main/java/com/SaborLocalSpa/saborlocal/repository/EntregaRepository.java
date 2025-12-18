package com.SaborLocalSpa.saborlocal.repository;

import com.SaborLocalSpa.saborlocal.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByPedidoId(Long pedidoId);
    
    List<Entrega> findByEstado(String estado);
}
