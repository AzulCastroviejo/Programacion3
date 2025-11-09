package org.example;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Alisia", 7.5, "Java"), new Alumno("Luisa", 6.0, "Java"),
                new Alumno("Coco", 6.0, "Web"), new Alumno("Juana", 4.5, "Web"),
                new Alumno("Ana", 9.5, "Java"), new Alumno("Luis", 6.0, "Java"),
                new Alumno("Carla", 8.0, "Web"), new Alumno("Juan", 4.5, "Web"),
                new Alumno("Pedro", 7.0, "Java"), new Alumno("Maria", 10.0, "IA"),
                new Alumno("Lucia", 6.8, "Web")
        );

        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", "Tecnología", 1200.50, 10), new Producto("Mouse", "Tecnología", 40.0, 50),
                new Producto("Silla Gamer", "Oficina", 350.99, 5), new Producto("Teclado", "Tecnología", 110.0, 20),
                new Producto("Monitor", "Tecnología", 250.0, 15), new Producto("Escritorio", "Oficina", 99.99, 8)
        );

        List<Libro> libros = Arrays.asList(
                new Libro("Cien Años de Soledad", "García Márquez", 417, 25.50), new Libro("Don Quijote", "Cervantes", 863, 30.0),
                new Libro("El Aleph", "Borges", 146, 15.0), new Libro("Rayuela", "Cortázar", 600, 28.75),
                new Libro("Ficciones", "Borges", 200, 18.0)
        );

        List<Empleado> empleados = Arrays.asList(
                new Empleado("Roberto", "Ventas", 2100.0, 25), new Empleado("Sandra", "IT", 3500.0, 32),
                new Empleado("Miguel", "Ventas", 1900.0, 40), new Empleado("Laura", "IT", 3600.0, 28),
                new Empleado("Jorge", "RRHH", 1500.0, 22), new Empleado("Diana", "RRHH", 1700.0, 21)
        );

        System.out.println("--- Alumnos Aprobados ---");
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        aprobados.forEach(System.out::println);

        System.out.println("--- Promedio de alumnos ---");
        double promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        // LÍNEA FALTANTE:
        System.out.println("Promedio: " + promedioGeneral);

        System.out.println("--- Alumnos por curso ---");
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        // LÍNEA FALTANTE (los Map se imprimen mejor con forEach):
        alumnosPorCurso.forEach((curso, lista) -> System.out.println(curso + ": " + lista));

        System.out.println("--- Los 3 mejores promedios ---");
        List<Double> mejoresNotas = alumnos.stream()
                .map(Alumno::getNota)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        // LÍNEA FALTANTE:
        mejoresNotas.forEach(System.out::println);

        System.out.println("------------------------------------------------");
        System.out.println("---- PRODUCTOS ----");

        System.out.println("--- Productos con precio mayor a 100 y ordenados de mayor a menor ---");
        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
        // LÍNEA FALTANTE:
        productosCaros.forEach(System.out::println);

        System.out.println("--- Agrupar por categoría y calcular el stock total ---");
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        // LÍNEA FALTANTE:
        stockPorCategoria.forEach((cat, stock) -> System.out.println(cat + ": " + stock + " unidades"));

        System.out.println("--- Generar un String (nombre; precio) ---");
        String reporteProductos = productos.stream()
                .map(p -> p.getNombre() + ": $" + p.getPrecio())
                .collect(Collectors.joining("; "));
        // LÍNEA FALTANTE:
        System.out.println(reporteProductos);

        System.out.println("--- Calcular el precio promedio general y por categoría ---");
        // General
        double promGeneralProd = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        // LÍNEA FALTANTE:
        System.out.println("Promedio General de Productos: " + promGeneralProd);

        // Por Categoría
        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        // LÍNEA FALTANTE:
        System.out.println("Promedio por Categoría:");
        promedioPorCategoria.forEach((cat, prom) -> System.out.println(cat + ": " + prom));

        System.out.println("------------------------------------------------");
        System.out.println("---- LIBROS ----");


        System.out.println("--- Títulos de libros con > 300 páginas, ordenados alfabéticamente ---");
        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
        // LÍNEA FALTANTE:
        titulosLargos.forEach(System.out::println);


        System.out.println("--- Calcular el promedio de páginas ---");
        double promPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        // LÍNEA FALTANTE:
        System.out.println("Promedio de Páginas: " + promPaginas);

        System.out.println("--- Agrupar por autor y contar cuántos libros tiene ---");
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        // LÍNEA FALTANTE:
        librosPorAutor.forEach((autor, cantidad) -> System.out.println(autor + ": " + cantidad + " libro(s)"));

        System.out.println("--- Obtener el libro más caro ---");
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio));
        // LÍNEA FALTANTE (los Optional se imprimen de forma segura con ifPresent):
        libroMasCaro.ifPresent(libro -> System.out.println("El libro más caro es: " + libro));


        System.out.println("------------------------------------------------");
        System.out.println("---- EMPLEADO ----");
        System.out.println("--- Empleados con salario > 2000, ordenados por salario descendente ---");
        List<Empleado> empleadosMejorPagos = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());
        // LÍNEA FALTANTE:
        empleadosMejorPagos.forEach(System.out::println);

        System.out.println("--- Calcular el salario promedio general ---");
        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        // LÍNEA FALTANTE:
        System.out.println("Salario Promedio: " + salarioPromedio);

        System.out.println("--- Agrupar por departamento y sumar salarios ---");
        Map<String, Double> salariosPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        // LÍNEA FALTANTE:
        salariosPorDepto.forEach((depto, suma) -> System.out.println(depto + ": $" + suma));

        System.out.println("--- Obtener los nombres de los 2 empleados más jóvenes ---");
        List<String> empleadosMasJovenes = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
        // LÍNEA FALTANTE:
        empleadosMasJovenes.forEach(System.out::println);
    }
}