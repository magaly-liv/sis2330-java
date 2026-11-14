package app;

import modelo.Tarea;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== GESTOR DE TAREAS =====");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero valido.");
                sc.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(sc, "Opcion: ");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Descripcion: ");
                    agregar(sc.nextLine());
                }
                case 2 -> listar();
                case 3 -> completar(leerEntero(sc, "ID a completar: "));
                case 4 -> eliminar(leerEntero(sc, "ID a eliminar: "));
                case 5 -> {
                    salir = true;
                    System.out.println("Hasta luego.");
                }
                default -> System.out.println("Opcion invalida.");
            }
        }
        sc.close();
    }
}
