package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }
	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {

        // --- INICIO DEL FLUJO DE CONSOLA ---

        // 1. Mostrar mensaje de bienvenida
        mensajeService.mostrarMensajeBienvenida();

        // 2. Mostrar la configuración actual
        System.out.println("\n--- CONFIGURACIÓN ACTUAL ---");
        tareaService.imprimirConfiguracion();

        // 3. Listar todas las tareas iniciales
        System.out.println("\n--- TAREAS INICIALES (TODAS) ---");
        List<Tarea> todas = tareaService.listarTodas();
        todas.forEach(System.out::println);

        // 4. Agregar una nueva tarea
        System.out.println("\n--- AGREGANDO NUEVA TAREA ---");
        try {
            Tarea nueva = tareaService.agregar("Estudiar Docker", Prioridad.MEDIA);
            System.out.println("Tarea agregada: " + nueva.getDescripcion());
        } catch (Exception e) {
            System.err.println("ERROR AL AGREGAR TAREA: " + e.getMessage());
        }

        // 5. Listar tareas pendientes
        System.out.println("\n--- TAREAS PENDIENTES ---");
        tareaService.listarPendientes().forEach(System.out::println);

        // 6. Marcar una tarea como completada (marcaremos la Tarea con ID 1)
        System.out.println("\n--- COMPLETANDO TAREA ID 1 ---");
        Long idParaCompletar = 1L;
        Tarea completada = tareaService.marcarComoCompletada(idParaCompletar);
        if (completada != null) {
            System.out.println("Tarea marcada: '" + completada.getDescripcion() + "'");
        } else {
            System.out.println("No se encontró la tarea ID " + idParaCompletar);
        }

        // 7. Mostrar estadísticas
        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.println(tareaService.obtenerEstadisticas());

        // 8. Listar tareas completadas
        System.out.println("\n--- TAREAS COMPLETADAS ---");
        tareaService.listarCompletadas().forEach(System.out::println);

        // 9. Mostrar mensaje de despedida
        mensajeService.mostrarMensajeDespedida();
    }
}
