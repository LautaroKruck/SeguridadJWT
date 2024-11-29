package com.es.jwtsecurity.service;

import com.es.jwtsecurity.dto.ProductoDTO;
import com.es.jwtsecurity.model.Producto;
import com.es.jwtsecurity.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        return convertirADTO(producto);
    }

    public ProductoDTO obtenerProductoPorNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
        return convertirADTO(producto);
    }

    public List<ProductoDTO> listarProductosAscendente() {
        return productoRepository.findAllByOrderByNombreAsc()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<ProductoDTO> listarProductosDescendente() {
        return productoRepository.findAllByOrderByNombreDesc()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        Producto producto = convertirAEntidad(productoDTO);
        return convertirADTO(productoRepository.save(producto));
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        return new ProductoDTO(producto.getNombre(), producto.getStock(), producto.getPrecio());
    }

    private Producto convertirAEntidad(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setStock(productoDTO.getStock());
        producto.setPrecio(productoDTO.getPrecio());
        return producto;
    }
}

