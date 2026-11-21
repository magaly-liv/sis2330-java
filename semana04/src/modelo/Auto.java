package modelo;

import java.util.Objects;

public class Auto extends Vehiculo {

    private int numPuertas;

    public Auto(String marca, String modelo, double velocidadMax, int numPuertas) {
        super(marca, modelo, velocidadMax);
        this.numPuertas = numPuertas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    @Override
    public String describir() {
        return "Auto " + marca + " " + modelo + ": " + numPuertas
                + " puertas, velocidad maxima " + velocidadMax + " km/h";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto otro = (Auto) o;
        return numPuertas == otro.numPuertas
                && Double.compare(velocidadMax, otro.velocidadMax) == 0
                && marca.equals(otro.marca)
                && modelo.equals(otro.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, velocidadMax, numPuertas);
    }
}
