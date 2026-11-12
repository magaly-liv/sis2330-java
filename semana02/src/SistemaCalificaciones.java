import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SistemaCalificaciones {

    static final int ESTUDIANTES = 5;
    static final int NOTAS = 3;
    static final double NOTA_APROBACION = 51;

    static String leerNombre(Scanner sc, String mensaje) {
        String nombre = "";
        while (nombre.isBlank()) {
            System.out.print(mensaje);
            nombre = sc.nextLine().trim();
            if (nombre.isBlank()) {
                System.out.println("Error: el nombre no puede estar vacio.");
            }
        }
        return nombre;
    }

    static double leerNota(Scanner sc, String mensaje) {
        double nota = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                nota = sc.nextDouble();
                if (nota < 0 || nota > 100) {
                    System.out.println("Error: la nota debe estar entre 0 y 100.");
                    continue;
                }
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un numero valido.");
                sc.next();
            }
        }
        return nota;
    }

    static double promedio(double[] notas) {
        double suma = 0;
        for (double n : notas) {
            suma += n;
        }
        return suma / notas.length;
    }

    static double maximo(double[] notas) {
        double max = notas[0];
        for (double n : notas) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    static double minimo(double[] notas) {
        double min = notas[0];
        for (double n : notas) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    static String estado(double promedio) {
        return promedio >= NOTA_APROBACION ? "Aprobado" : "Reprobado";
    }

    static void imprimirReporte(String[] nombres, double[][] notas) {
        System.out.println();
        System.out.println("============ REPORTE FINAL ============");
        System.out.printf(Locale.US, "%-15s %9s %8s %8s %-10s%n",
                "Nombre", "Promedio", "Maximo", "Minimo", "Estado");
        for (int i = 0; i < nombres.length; i++) {
            double prom = promedio(notas[i]);
            System.out.printf(Locale.US, "%-15s %9.2f %8.2f %8.2f %-10s%n",
                    nombres[i], prom, maximo(notas[i]), minimo(notas[i]), estado(prom));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        String[] nombres = new String[ESTUDIANTES];
        double[][] notas = new double[ESTUDIANTES][NOTAS];

        for (int i = 0; i < ESTUDIANTES; i++) {
            nombres[i] = leerNombre(sc, "Nombre del estudiante " + (i + 1) + ": ");
            for (int j = 0; j < NOTAS; j++) {
                notas[i][j] = leerNota(sc, "  Nota " + (j + 1) + ": ");
            }
            sc.nextLine();
        }

        imprimirReporte(nombres, notas);
        sc.close();
    }
}
