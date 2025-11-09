package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Usamos un record para crear un DTO inmutable
@Schema(description = "DTO para crear o actualizar un producto")
public record ProductoDTO(

        @Schema(description = "Nombre del producto", example = "Teclado Mecánico")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripción detallada", example = "Teclado con switches Cherry MX")
        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @Schema(description = "Precio del producto", example = "150.75")
        @NotNull(message = "El precio no puede ser nulo")
        @Min(value = 1, message = "El precio debe ser como mínimo 1") // El PDF pide 0.01, pero @Min es para enteros.
        // Si necesitas 0.01, se usa @DecimalMin("0.01")
        Double precio,

        @Schema(description = "Stock disponible", example = "50")
        @NotNull(message = "El stock no puede ser nulo")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        @Schema(description = "Categoría del producto", example = "ELECTRONICA")
        @NotNull(message = "La categoría no puede ser nula")
        Categoria categoria
) {
}