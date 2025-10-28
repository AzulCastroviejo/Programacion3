package com.utn.tareas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {
    
    
    @Value("${app.nombre}")
    private String nombreApp;

    public void mostrarMensajeBienvenida() {
        System.out.println("\n========================================");
        System.out.println(" BIENVENIDO A: " + nombreApp);
        System.out.println("========================================");
    }

    public void mostrarMensajeDespedida() {
        System.out.println("\n========================================");
        System.out.println(" GRACIAS POR USAR: " + nombreApp);
        System.out.println("========================================");
    }
}
