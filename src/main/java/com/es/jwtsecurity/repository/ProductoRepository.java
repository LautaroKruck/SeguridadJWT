package com.es.jwtsecurity.repository;

import com.es.jwtsecurity.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findAllByOrderByNombreAsc();
    List<Producto> findAllByOrderByNombreDesc();
}

