public class Fundamentos {

    static void tiposPrimitivos() {
        byte pequeno = 100;
        short corto = 30000;
        int edad = 20;
        long poblacion = 8000000000L;
        float temperatura = 36.6f;
        double promedio = 85.5;
        char inicial = 'M';
        boolean aprobado = promedio >= 51;

        System.out.println("--- Tipos primitivos ---");
        System.out.println("byte: " + pequeno);
        System.out.println("short: " + corto);
        System.out.println("int: " + edad);
        System.out.println("long: " + poblacion);
        System.out.println("float: " + temperatura);
        System.out.println("double: " + promedio);
        System.out.println("char: " + inicial);
        System.out.println("boolean: " + aprobado);
    }

    static void divisionEnteraVsDecimal() {
        int suma = 7;
        int cantidad = 2;
        int enteroMalo = suma / cantidad;
        double decimalBueno = (double) suma / cantidad;

        System.out.println("--- Division entera vs decimal ---");
        System.out.println("7 / 2 = " + enteroMalo + " (division entera)");
        System.out.println("(double) 7 / 2 = " + decimalBueno);
        System.out.println("7 / 3 = " + (7 / 3) + " (se trunca, no redondea)");
        System.out.println("(double) 7 / 3 = " + ((double) 7 / 3));
        System.out.println("-7 / 3 = " + (-7 / 3) + " (trunca hacia cero)");
    }

    static void operadores() {
        int a = 17;
        int b = 5;
        boolean x = true;
        boolean y = false;

        System.out.println("--- Operadores ---");
        System.out.println("17 + 5 = " + (a + b));
        System.out.println("17 - 5 = " + (a - b));
        System.out.println("17 * 5 = " + (a * b));
        System.out.println("17 / 5 = " + (a / b));
        System.out.println("17 % 5 = " + (a % b));
        System.out.println("17 > 5: " + (a > b));
        System.out.println("17 == 5: " + (a == b));


        System.out.println("true && false: " + (x && y));
        System.out.println("true || false: " + (x || y));
        System.out.println("!true: " + (!x));

        int total = 10;
        total += 5;
        total *= 2;
        System.out.println("total despues de += 5 y *= 2: " + total);

        int divisor = 0;
        if (divisor != 0 && 10 / divisor > 1) {
            System.out.println("no se imprime");
        } else {
            System.out.println("Cortocircuito: evito dividir entre cero");
        }

        String nombre = null;
        if (nombre != null && nombre.equals("Admin")) {
            System.out.println("es admin");
        } else {
            System.out.println("Cortocircuito: evito NullPointerException");
        }
    }

    static void decisionesYBucles() {
        System.out.println("--- Decisiones y bucles ---");

        int nota = 75;
        if (nota >= 90) {
            System.out.println("Nota " + nota + ": Excelente");
        } else if (nota >= 51) {
            System.out.println("Nota " + nota + ": Aprobado");
        } else {
            System.out.println("Nota " + nota + ": Reprobado");
        }

        int dia = 3;
        String nombreDia;
        switch (dia) {
            case 1:
                nombreDia = "Lunes";
                break;
            case 2:
                nombreDia = "Martes";
                break;
            case 3:
                nombreDia = "Miercoles";
                break;
            default:
                nombreDia = "Otro dia";
        }
        System.out.println("switch clasico: dia " + dia + " es " + nombreDia);

        String tipoDia = switch (dia) {
            case 6, 7 -> "Fin de semana";
            default -> "Dia habil";
        };
        System.out.println("switch expression: " + tipoDia);

        int suma = 0;
        for (int i = 1; i <= 5; i++) {
            suma += i;
        }
        System.out.println("for: suma de 1 a 5 = " + suma);

        int potencia = 1;
        int duplicaciones = 0;
        while (potencia < 100) {
            potencia *= 2;
            duplicaciones++;
        }
        System.out.println("while: " + duplicaciones + " duplicaciones para pasar de 100 (llego a " + potencia + ")");

        int intentos = 0;
        do {
            intentos++;
        } while (intentos < 3);
        System.out.println("do-while: se ejecuto " + intentos + " veces");
    }

    static double promedio(int[] valores) {
        int suma = 0;
        for (int v : valores) {
            suma += v;
        }
        return (double) suma / valores.length;
    }

    static double promedio(double a, double b) {
        return (a + b) / 2;
    }

    static void imprimirLinea() {
        System.out.println("------------------");
    }

    static void arrays() {
        System.out.println("--- Arrays ---");

        int[] notas = {85, 90, 78};
        System.out.println("for clasico:");
        for (int i = 0; i < notas.length; i++) {
            System.out.println("  notas[" + i + "] = " + notas[i]);
        }
        System.out.println("for-each:");
        for (int n : notas) {
            System.out.println("  " + n);
        }

        double[] precios = new double[3];
        precios[0] = 10.5;
        precios[1] = 20.0;
        precios[2] = 7.25;
        System.out.println("precios[2] = " + precios[2] + ", length = " + precios.length);

        int[][] matriz = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Matriz 3x3:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        int[][] triangulo = {{1}, {2, 3}, {4, 5, 6}};
        System.out.println("Array irregular: triangulo[0].length = " + triangulo[0].length
                + ", triangulo[2].length = " + triangulo[2].length);
    }

    static void metodos() {
        System.out.println("--- Metodos y sobrecarga ---");
        int[] notas = {80, 90, 70};
        System.out.println("promedio(int[]) = " + promedio(notas));
        System.out.println("promedio(5.0, 7.0) = " + promedio(5.0, 7.0));
        imprimirLinea();
    }

    public static void main(String[] args) {
        tiposPrimitivos();
        divisionEnteraVsDecimal();
        operadores();
        decisionesYBucles();
        arrays();
        metodos();
    }
}
