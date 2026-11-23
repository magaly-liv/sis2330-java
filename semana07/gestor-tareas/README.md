# Gestor de Tareas (Vaadin)

## Descripcion

Aplicacion web hecha con Vaadin y Spring Boot. Una unica vista permite escribir la
descripcion de una tarea, agregarla a una lista en memoria y verla como texto plano.
No hay HTML, CSS ni JavaScript escritos a mano: toda la interfaz es Java del lado del servidor.

## Requisitos

- JDK 21 o superior (Vaadin 25 lo exige)
- Maven 3.9 o superior
- Conexion a Internet la primera vez (Vaadin descarga Node.js y el frontend)

## Como ejecutar

    cd semana07/gestor-tareas
    mvn spring-boot:run

Luego abrir http://localhost:8080 en el navegador. Para detener: Ctrl+C.


## Estructura

    gestor-tareas/
      pom.xml                                       Spring Boot 4.1.1 + Vaadin 25.3.0
      src/main/java/com/tareas/
        Application.java                            punto de entrada @SpringBootApplication
        Tarea.java                                  record (id, descripcion, completada)
        views/TareasView.java                       vista @Route("") con TextField, Button y Div
      src/main/resources/application.properties     puerto 8080

## Funcionalidades

- Campo de texto para la descripcion de la tarea.
- Boton "Agregar" (tambien con la tecla Enter) que crea una tarea con ID incremental.
- Las tareas se muestran como texto plano dentro de un Div.
- El campo se limpia despues de agregar.
- Si el campo esta vacio, se muestra una Notification y no se agrega nada.

## Que se aprendio

- Una vista Vaadin es una clase Java con `@Route` que extiende un layout.
- La interfaz se arma en el constructor con `add(...)`.
- Los eventos (`addClickListener`) se ejecutan en el servidor, dentro de una lambda.
- Las tareas viven en una `List<Tarea>` en memoria: se pierden al reiniciar la aplicacion.
- Un `record` sirve como modelo de datos inmutable.
