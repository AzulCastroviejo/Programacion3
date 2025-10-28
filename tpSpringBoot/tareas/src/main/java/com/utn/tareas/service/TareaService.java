package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    // --- 4.2 Inyección de las propiedades ---
    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;
    // --- Fin de la inyección ---


    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * 3.3 Método para Agregar una nueva tarea.
     * AHORA VALIDA EL LÍMITE.
     */
    public Tarea agregar(String descripcion, Prioridad prioridad) {

        // 4.2 Validar que no se supere max-tareas
        int totalActual = tareaRepository.findAll().size();
        if (totalActual >= maxTareas) {
            // Es mejor lanzar una excepción para que el controlador la atrape
            throw new RuntimeException("Límite de tareas (" + maxTareas + ") alcanzado. No se pueden agregar más.");
        }

        // Creamos una nueva instancia de Tarea
        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setDescripcion(descripcion);
        nuevaTarea.setPrioridad(prioridad);
        nuevaTarea.setCompletada(false); // Las tareas nuevas nunca están completadas

        // El repositorio le asignará un ID y la guardará
        return tareaRepository.save(nuevaTarea);
    }

    /**
     * 3.3 Método para Listar todas las tareas.
     */
    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    /**
     * 3.3 Método para Listar solo las tareas pendientes (no completadas).
     */
    public List<Tarea> listarPendientes() {
        List<Tarea> todas = tareaRepository.findAll();

        return todas.stream()
                .filter(tarea -> !tarea.isCompletada())
                .collect(Collectors.toList());
    }

    /**
     * 3.3 Método para Listar solo las tareas completadas.
     */
    public List<Tarea> listarCompletadas() {
        List<Tarea> todas = tareaRepository.findAll();

        return todas.stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    /**
     * 3.3 Método para Marcar una tarea como completada.
     */
    public Tarea marcarComoCompletada(Long id) {
        Optional<Tarea> opcional = tareaRepository.findById(id);

        if (opcional.isPresent()) {
            Tarea tarea = opcional.get();
            tarea.setCompletada(true);
            return tarea;
        }
        return null;
    }

    /**
     * 3.3 Método para Obtener estadísticas.
     * AHORA OBEDECE A LAS PROPIEDADES.
     */
    public String obtenerEstadisticas() {

        // 4.2 Validar si se deben mostrar
        if (!mostrarEstadisticas) {
            return "Las estadísticas están desactivadas por configuración.";
        }

        List<Tarea> todas = tareaRepository.findAll();
        int total = todas.size();
        long completadas = todas.stream()
                .filter(Tarea::isCompletada)
                .count();
        long pendientes = total - completadas;

        // 4.2 Usamos el nombre de la app inyectado
        return String.format("Estadísticas (%s): Total: %d, Completadas: %d, Pendientes: %d",
                nombreApp, total, completadas, pendientes);
    }

    /**
     * 4.2 Nuevo método para imprimir la configuración.
     * Este método solo imprimirá en la consola de Spring Boot.
     */
    public void imprimirConfiguracion() {
        System.out.println("========================================");
        System.out.println("CONFIGURACIÓN DE LA APLICACIÓN");
        System.out.println("Nombre: " + nombreApp);
        System.out.println("Máx. Tareas: " + maxTareas);
        System.out.println("Mostrar Stats: " + mostrarEstadisticas);
        System.out.println("========================================");
    }
}