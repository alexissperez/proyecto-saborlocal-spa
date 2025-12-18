package com.SaborLocalSpa.saborlocal.repository;

import com.SaborLocalSpa.saborlocal.model.Productor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductorRepository extends JpaRepository<Productor, Long> {
    Optional<Productor> findByEmail(String email);
    
    List<Productor> findByActivoTrue();
    
    List<Productor> findByNombreContainingIgnoreCase(String nombre);
}
