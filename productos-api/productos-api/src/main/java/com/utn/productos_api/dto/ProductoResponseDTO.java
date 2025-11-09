package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;

// Este DTO incluye el ID, que se genera al guardar en la BD
public record ProductoResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Categoria categoria
) {
}
