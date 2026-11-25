# Gestor de Tareas - Grid y CRUD (Vaadin)

## Descripcion

Aplicacion web hecha con Vaadin y Spring Boot. Una tabla (`Grid`) muestra las tareas y un
formulario lateral, validado con `Binder`, permite crear, editar, eliminar y marcar tareas
como completadas. Las tareas viven en una lista en memoria: se pierden al reiniciar.

## Requisitos

- JDK 21 o superior (Vaadin 25 lo exige)
- Maven 3.9 o superior
- Conexion a Internet la primera vez (Vaadin descarga Node.js y el frontend)

## Como ejecutar

    cd semana08/gestor-tareas
    mvn spring-boot:run

Luego abrir http://localhost:8080 en el navegador. Para detener: Ctrl+C.

## Estructura

    gestor-tareas/
      pom.xml                                       Spring Boot 4.1.1 + Vaadin 25.3.0
      src/main/java/com/tareas/
        Application.java                            punto de entrada @SpringBootApplication
        Tarea.java                                  record inmutable (id, descripcion, completada)
        views/TareaEditable.java                    clase mutable, puente entre Binder y el record
        views/TareasView.java                       Grid + formulario + CRUD
      src/main/resources/application.properties     puerto 8080

## Funcionalidades

- Grid con columnas ID, Descripcion y Completada.
- Formulario con TextField (descripcion obligatoria) y Checkbox (completada).
- Guardar: agrega una tarea nueva si no hay fila seleccionada, o actualiza la seleccionada.
- Eliminar: quita la tarea seleccionada.
- Al hacer clic en una fila, el formulario se llena con sus datos.
- Limpiar: deselecciona la fila y vacia el formulario.

## Flujo del CRUD

1. El usuario hace clic en una fila: se dispara el listener de seleccion.
2. Los datos de la fila se cargan en el formulario con `binder.readBean(...)`.
3. El usuario edita y pulsa Guardar: `binder.writeBeanIfValid(...)` valida y escribe en `TareaEditable`.
4. Se construye un `Tarea` nuevo (record) y reemplaza al anterior en la lista, buscandolo por id.
5. Se llama a `grid.setItems(...)` para refrescar la tabla.

## Que se aprendio

- `Grid<T>` es una tabla tipada: cada fila es un objeto y cada columna se define con `addColumn`.
- Hay que volver a llamar `grid.setItems(...)` despues de cada cambio en la lista.
- `Binder` conecta campos con propiedades y valida antes de guardar (`asRequired`).
- Como `Tarea` es un record sin setters, se usa `TareaEditable` como clase mutable auxiliar.
- Las tareas se buscan por `id`, no por referencia, porque cada edicion crea un record nuevo.
