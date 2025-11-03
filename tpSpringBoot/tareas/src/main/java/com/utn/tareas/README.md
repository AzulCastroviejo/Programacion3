1. Estructura del Proyecto

✅ Organización correcta en capas: model, repository, service
✅ Uso apropiado de anotaciones de Spring (@Service, @Repository, @SpringBootApplication)
✅ Implementación de Lombok para reducir código boilerplate

2. Inyección de Dependencias

✅ Constructor injection en TareasApplication, TareaService
✅ Uso correcto de @Value para inyectar propiedades de configuración

3. Configuración de Profiles

✅ application.properties base con profile activo
✅ application-dev.properties (10 tareas, debug, stats activadas)
✅ application-prod.properties (1000 tareas, error, stats desactivadas)
✅ Validación del límite de tareas en TareaService.agregar()

4. Funcionalidad Completa

✅ CRUD básico implementado
✅ Filtros por estado (pendientes/completadas)
✅ Sistema de prioridades
✅ Estadísticas con validación de configuración
✅ Mensajes personalizados con el nombre de la app

5. Buenas Prácticas

✅ Uso de Optional en el repositorio
✅ Streams de Java 8+ para filtrado
✅ AtomicLong para generación thread-safe de IDs
✅ Manejo de excepciones en límite de tareas

📝 Gestor de Tareas - Spring Boot

- Autor
**Materia:** Programación 3 - UTN (Comision 1)
**Nombre:** Azul Castroviejo  **Legajo:** 52622
**Nombre:** Clara Mitre  **Legajo:** 52701
**Nombre:** Elizabeth Morato  **Legajo:** 52711
**Nombre:** Micaela Paco  **Legajo:** 


- Descripción
Sistema de gestión de tareas desarrollado con Spring Boot que permite crear mi proyecto más rápido y con todas as dependencias deseadas. Nos permite acelerar la creación del proyecto.

- Tecnologías Utilizadas
  - Java 21
  - Spring Boot 3.5.7
  - Lombok
  - Gradle 8.14.3

 -Instalación y Ejecución

* Clonar el repositorio
```bash
git clone [URL-DE-TU-REPO]
cd tpSpringBoot/tareas
```

* Ejecutar con profile DEV
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

* Ejecutar con profile PROD
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

- Configuración de Profiles

- Profile DEV
  - Máximo de tareas: 10
  - Estadísticas: Activadas
  - Logging: DEBUG

- Profile PROD
  - Máximo de tareas: 1000
  - Estadísticas: Desactivadas
  - Logging: ERROR

## Capturas de Pantalla

# Ejecución con Profile DEV
<img width="1283" height="521" alt="image" src="https://github.com/user-attachments/assets/90300159-56a0-4fe7-8ae5-b18882e5b9c5" />
<img width="1281" height="589" alt="image" src="https://github.com/user-attachments/assets/cef20415-45ca-4282-b93e-b8b19defa851" />


# Ejecución con Profile PROD
<img width="1300" height="501" alt="image" src="https://github.com/user-attachments/assets/f7e0b709-a7c1-42c2-9215-a294a11e8352" />
<img width="1292" height="481" alt="image" src="https://github.com/user-attachments/assets/a553956b-add1-407e-96d8-0c55a3998e11" />


## Funcionalidades Implementadas
-  CRUD de tareas
-  Sistema de prioridades (ALTA, MEDIA, BAJA)
-  Filtrado por estado
-  Estadísticas dinámicas
-  Validación de límites por configuración
-  Mensajes personalizados

##  Conceptos de Spring Boot Aplicados
- Inyección de dependencias por constructor
- Uso de `@Value` para propiedades
- Configuración por profiles
- Arquitectura en capas
- Stereotypes (`@Service`, `@Repository`)

