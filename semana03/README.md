# Semana 03: POO I

Gestor de tareas de consola con una clase `Tarea` encapsulada y un `ArrayList<Tarea>`.

## Que se aprendio

Clases, objetos, constructores, encapsulacion con `this`, `ArrayList<T>` generico
y organizacion en paquetes.

## Estructura

    semana03/src/
      modelo/Tarea.java       clase con atributos private, getters/setters e ID autoincremental
      app/GestorTareas.java   menu de consola y CRUD con ArrayList<Tarea>

## Compilar y ejecutar

    cd semana03
    mkdir -p out
    javac -d out src/modelo/Tarea.java src/app/GestorTareas.java
    java -cp out app.GestorTareas
