package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    private Long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                ", prioridad=" + prioridad +
                '}';
    }
}
