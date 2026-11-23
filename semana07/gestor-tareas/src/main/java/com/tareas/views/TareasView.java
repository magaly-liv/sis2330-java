package com.tareas.views;

import com.tareas.Tarea;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@Route("")
public class TareasView extends VerticalLayout {

    // Las tareas viven en memoria: se pierden al reiniciar la aplicacion
    private final List<Tarea> tareas = new ArrayList<>();
    private long siguienteId = 1;

    private final TextField campoDescripcion = new TextField("Descripcion");
    private final Div listaTareas = new Div();

    public TareasView() {
        campoDescripcion.setPlaceholder("Que hay que hacer?");

        Button botonAgregar = new Button("Agregar", evento -> agregarTarea());
        botonAgregar.addClickShortcut(Key.ENTER);

        HorizontalLayout formulario = new HorizontalLayout(campoDescripcion, botonAgregar);
        formulario.setAlignItems(FlexComponent.Alignment.END);

        add(new H1("Gestor de Tareas"), formulario, listaTareas);
        refrescarLista();
    }

    private void agregarTarea() {
        String descripcion = campoDescripcion.getValue().trim();
        if (descripcion.isEmpty()) {
            Notification.show("Escribe una descripcion antes de agregar");
            return;
        }
        tareas.add(new Tarea(siguienteId, descripcion, false));
        siguienteId++;
        campoDescripcion.clear();
        campoDescripcion.focus();
        refrescarLista();
    }

    private void refrescarLista() {
        listaTareas.removeAll();
        if (tareas.isEmpty()) {
            listaTareas.add(new Paragraph("Aun no hay tareas."));
            return;
        }
        for (Tarea tarea : tareas) {
            String estado = tarea.completada() ? "[hecha]" : "[pendiente]";
            listaTareas.add(new Paragraph(tarea.id() + " - " + tarea.descripcion() + " " + estado));
        }
    }
}
