package modelo;

public class Camion extends Vehiculo {

    private double capacidadCargaKg;

    public Camion(String marca, String modelo, double velocidadMax, double capacidadCargaKg) {
        super(marca, modelo, velocidadMax);
        this.capacidadCargaKg = capacidadCargaKg;
    }

    public double getCapacidadCargaKg() {
        return capacidadCargaKg;
    }

    @Override
    public String describir() {
        return "Camion " + marca + " " + modelo + ": carga maxima " + capacidadCargaKg
                + " kg, velocidad maxima " + velocidadMax + " km/h";
    }
}
