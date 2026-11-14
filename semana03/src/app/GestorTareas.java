package app;

import modelo.Tarea;
import java.util.ArrayList;

public class GestorTareas {

    private static ArrayList<Tarea> tareas = new ArrayList<>();

    private static void agregar(String descripcion) {
        try {
            tareas.add(new Tarea(descripcion));
            System.out.println("Tarea agregada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listar() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas.");
            return;
        }
        for (Tarea t : tareas) {
            System.out.println(t);
        }
    }

    private static void completar(int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                t.marcarCompletada();
                System.out.println("Tarea " + id + " marcada como completada.");
                return;
            }
        }
        System.out.println("No existe una tarea con ID " + id);
    }

    private static void eliminar(int id) {
        boolean eliminado = tareas.removeIf(t -> t.getId() == id);
        System.out.println(eliminado ? "Tarea eliminada." : "No existe una tarea con ID " + id);
    }

    public static void main(String[] args) {
        agregar("Estudiar Git");
        agregar("Hacer capstone");
        completar(1);
        listar();
        eliminar(2);
        eliminar(99);
        listar();
    }
}
