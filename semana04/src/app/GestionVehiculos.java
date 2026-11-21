package app;

import modelo.Auto;
import modelo.AutoElectrico;
import modelo.Camion;
import modelo.Electrico;
import modelo.Moto;
import modelo.Vehiculo;
import java.util.ArrayList;

public class GestionVehiculos {

    public static void main(String[] args) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Auto("Toyota", "Corolla", 180, 4));
        vehiculos.add(new Moto("Yamaha", "R15", 140, 155));
        vehiculos.add(new Camion("Volvo", "FH16", 110, 25000));
        vehiculos.add(new AutoElectrico("Tesla", "Model 3", 225, 4, 450));

        System.out.println("--- Recorrido polimorfico ---");
        for (Vehiculo v : vehiculos) {
            System.out.println(v.describir());
            if (v instanceof Electrico e) {
                e.cargarBateria();
            }
        }

        System.out.println();
        System.out.println("--- toString y equals ---");
        Auto a1 = new Auto("Toyota", "Corolla", 180, 4);
        Auto a2 = new Auto("Toyota", "Corolla", 180, 4);
        System.out.println(a1);
        System.out.println("a1 == a2: " + (a1 == a2));
        System.out.println("a1.equals(a2): " + a1.equals(a2));
        System.out.println("hashCode iguales: " + (a1.hashCode() == a2.hashCode()));
    }
}
