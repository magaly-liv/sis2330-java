package com.tareas.views;

// Clase mutable usada solo dentro del Binder.
// El record Tarea es inmutable y no tiene setters, asi que el Binder
// escribe aqui y despues se construye un Tarea nuevo con estos valores.
public class TareaEditable {

    private long id;
    private String descripcion = "";
    private boolean completada;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
