package com.utn.productos_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Marca esta clase como una entidad de base de datos
@Table(name = "productos") // Nombre de la tabla
@Getter // Lombok crea los getters (ej: getNombre())
@Setter // Lombok crea los setters (ej: setNombre(...))
@NoArgsConstructor // Lombok crea un constructor vacío (requerido por JPA)
@AllArgsConstructor // Lombok crea un constructor con todos los campos
public class Producto {

    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura el ID como autoincremental
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    @Enumerated(EnumType.STRING) // Le dice a JPA que guarde el enum como texto (ej: "ROPA")
    private Categoria categoria;
}