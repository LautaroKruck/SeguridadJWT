package com.es.jwtsecurity.controller;

import com.es.jwtsecurity.dto.ProductoDTO;
import com.es.jwtsecurity.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private  ProductoService productoService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/byNombre/{nombre}")
    public ProductoDTO obtenerProductoPorNombre(@PathVariable String nombre) {
        return productoService.obtenerProductoPorNombre(nombre);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductoDTO guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.guardarProducto(productoDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/asc")
    public List<ProductoDTO> listarProductosAscendente() {
        return productoService.listarProductosAscendente();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/desc")
    public List<ProductoDTO> listarProductosDescendente() {
        return productoService.listarProductosDescendente();
    }
}

