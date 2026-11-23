import modelo.Estudiante;
import servicio.SistemaEstudiantes;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDecimal(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private static void mostrarLista(ArrayList<Estudiante> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes.");
            return;
        }
        int posicion = 1;
        for (Estudiante e : lista) {
            System.out.println(posicion + ". " + e);
            posicion++;
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE ESTUDIANTES =====");
        System.out.println("1. Registrar");
        System.out.println("2. Buscar por codigo");
        System.out.println("3. Actualizar promedio");
        System.out.println("4. Ranking completo");
        System.out.println("5. Top N");
        System.out.println("6. Eliminar");
        System.out.println("7. Salir");
    }

    public static void main(String[] args) {
        SistemaEstudiantes sistema = new SistemaEstudiantes();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Opcion: ");

            switch (opcion) {
                case 1 -> {
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    double promedio = leerDecimal(sc, "Promedio (0-100): ");
                    try {
                        boolean ok = sistema.registrar(new Estudiante(codigo, nombre, promedio));
                        System.out.println(ok ? "Registrado" : "Codigo duplicado");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Codigo a buscar: ");
                    Estudiante encontrado = sistema.buscarPorCodigo(sc.nextLine().trim());
                    System.out.println(encontrado != null ? encontrado : "No encontrado");
                }
                case 3 -> {
                    System.out.print("Codigo: ");
                    String codigo = sc.nextLine().trim();
                    double nuevo = leerDecimal(sc, "Nuevo promedio (0-100): ");
                    try {
                        System.out.println(sistema.actualizarPromedio(codigo, nuevo)
                                ? "Promedio actualizado" : "No encontrado");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 4 -> mostrarLista(sistema.listarRanking());
                case 5 -> {
                    int n = leerEntero(sc, "N: ");
                    mostrarLista(sistema.topN(n));
                }
                case 6 -> {
                    System.out.print("Codigo a eliminar: ");
                    System.out.println(sistema.eliminar(sc.nextLine().trim())
                            ? "Estudiante eliminado" : "No encontrado");
                }
                case 7 -> System.out.println("Adios.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 7);

        sc.close();
    }
}
