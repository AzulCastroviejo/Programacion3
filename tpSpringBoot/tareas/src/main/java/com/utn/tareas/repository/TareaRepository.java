package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {
    private final List<Tarea> tareas = new ArrayList<>();

    // 2.2 Contador para IDs automáticos (como sugiere la ayuda)
    private final AtomicLong idCounter = new AtomicLong(0);

    // 2.2 Constructor con 3-5 tareas de ejemplo
    public TareaRepository() {
        // Usamos nuestro propio método save() para que asigne los IDs
        save(new Tarea(null, "Hacer TP de Spring Boot", false, Prioridad.ALTA));
        save(new Tarea(null, "Estudiar para el parcial de Programación 3", false, Prioridad.MEDIA));
        save(new Tarea(null, "Comprar facturas", true, Prioridad.BAJA));
    }

    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            // Asigna un ID nuevo usando el contador atómico
            tarea.setId(idCounter.incrementAndGet());
            tareas.add(tarea);
        }
        // Omitimos la lógica de "actualizar" (update) por simplicidad,
        // ya que la consigna se enfoca en la generación de ID.
        return tarea;
    }
    public List<Tarea> findAll() {
        // Devolvemos una copia de la lista para evitar modificaciones externas
        return new ArrayList<>(tareas);
    }
    public Optional<Tarea> findById(Long id) {
        // Usamos un stream para buscar la primera tarea que coincida con el ID
        return tareas.stream()
                .filter(tarea -> tarea.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        // Usamos removeIf, que devuelve true si la colección fue modificada
        return tareas.removeIf(tarea -> tarea.getId().equals(id));
    }
}
