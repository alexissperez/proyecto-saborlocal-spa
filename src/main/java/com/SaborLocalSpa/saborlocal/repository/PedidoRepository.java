package com.SaborLocalSpa.saborlocal.repository;

import com.SaborLocalSpa.saborlocal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    
    List<Pedido> findByClienteEmail(String email);
    
    List<Pedido> findByEstado(String estado);
    
    List<Pedido> findByProductoId(Long productoId);
}
