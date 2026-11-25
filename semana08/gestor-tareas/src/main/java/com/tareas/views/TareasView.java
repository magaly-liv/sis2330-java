package com.tareas.views;

import com.tareas.Tarea;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@Route("")
public class TareasView extends VerticalLayout {

    // Las tareas viven en memoria: se pierden al reiniciar la aplicacion
    private final List<Tarea> tareas = new ArrayList<>();
    private long siguienteId = 1;
    private Tarea seleccionActual = null;

    private final Grid<Tarea> grid = new Grid<>(Tarea.class, false);
    private final TextField descripcion = new TextField("Descripcion");
    private final Checkbox completada = new Checkbox("Completada");
    private final Binder<TareaEditable> binder = new Binder<>(TareaEditable.class);

    public TareasView() {
        // --- Tabla ---
        grid.addColumn(Tarea::id).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Tarea::descripcion).setHeader("Descripcion").setFlexGrow(1);
        grid.addColumn(t -> t.completada() ? "Si" : "No").setHeader("Completada").setAutoWidth(true);
        grid.setWidthFull();
        grid.asSingleSelect().addValueChangeListener(evento -> cargarEnFormulario(evento.getValue()));

        // --- Validacion con Binder ---
        binder.forField(descripcion)
                .asRequired("La descripcion es obligatoria")
                .withValidator(texto -> !texto.isBlank(), "La descripcion no puede ser solo espacios")
                .bind(TareaEditable::getDescripcion, TareaEditable::setDescripcion);
        binder.forField(completada)
                .bind(TareaEditable::isCompletada, TareaEditable::setCompletada);

        // --- Formulario lateral ---
        Button botonGuardar = new Button("Guardar", evento -> guardar());
        Button botonEliminar = new Button("Eliminar", evento -> eliminar());
        Button botonLimpiar = new Button("Limpiar", evento -> limpiar());

        VerticalLayout formulario = new VerticalLayout(descripcion, completada,
                new HorizontalLayout(botonGuardar, botonEliminar, botonLimpiar));
        formulario.setWidth("340px");

        HorizontalLayout contenido = new HorizontalLayout(grid, formulario);
        contenido.setWidthFull();
        contenido.setFlexGrow(1, grid);

        add(new H1("Gestor de Tareas"), contenido);
        refrescarGrid();
        cargarEnFormulario(null);
    }

    // Llena el formulario con la tarea (o lo deja vacio si es null)
    private void cargarEnFormulario(Tarea tarea) {
        seleccionActual = tarea;
        TareaEditable editable = new TareaEditable();
        if (tarea != null) {
            editable.setId(tarea.id());
            editable.setDescripcion(tarea.descripcion());
            editable.setCompletada(tarea.completada());
        }
        binder.readBean(editable);
    }

    private void guardar() {
        TareaEditable editable = new TareaEditable();
        if (!binder.writeBeanIfValid(editable)) {
            Notification.show("Revisa los datos del formulario");
            return;
        }
        String texto = editable.getDescripcion().trim();

        if (seleccionActual == null) {
            // Sin seleccion: tarea nueva
            tareas.add(new Tarea(siguienteId, texto, editable.isCompletada()));
            siguienteId++;
        } else {
            // Con seleccion: se reemplaza en su posicion, buscando por id (no por referencia)
            long id = seleccionActual.id();
            Tarea actualizada = new Tarea(id, texto, editable.isCompletada());
            for (int i = 0; i < tareas.size(); i++) {
                if (tareas.get(i).id() == id) {
                    tareas.set(i, actualizada);
                    break;
                }
            }
        }
        refrescarGrid();
        limpiar();
    }

    private void eliminar() {
        if (seleccionActual == null) {
            Notification.show("Selecciona una tarea para eliminar");
            return;
        }
        long id = seleccionActual.id();
        tareas.removeIf(t -> t.id() == id);
        refrescarGrid();
        limpiar();
    }

    private void limpiar() {
        grid.deselectAll();
        cargarEnFormulario(null);
    }

    // Hay que volver a llamar setItems cada vez que cambia la lista
    private void refrescarGrid() {
        grid.setItems(tareas);
    }
}
