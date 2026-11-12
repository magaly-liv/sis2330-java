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

    public static void main(String[] args) {
        tiposPrimitivos();
        divisionEnteraVsDecimal();
        operadores();
    }
}
