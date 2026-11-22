package modelo;

import java.util.Locale;
import java.util.Objects;

public class Estudiante implements Comparable<Estudiante> {

    private final String codigo;
    private String nombre;
    private double promedio;

    public Estudiante(String codigo, String nombre, double promedio) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        setPromedio(promedio);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        if (Double.isNaN(promedio) || promedio < 0 || promedio > 100) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 100");
        }
        this.promedio = promedio;
    }

    // Orden natural: promedio descendente, desempate por codigo
    @Override
    public int compareTo(Estudiante otro) {
        int porPromedio = Double.compare(otro.promedio, this.promedio);
        if (porPromedio != 0) {
            return porPromedio;
        }
        return this.codigo.compareTo(otro.codigo);
    }

    // Igualdad por codigo (identificador unico e inmutable)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estudiante)) {
            return false;
        }
        return codigo.equals(((Estudiante) o).codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "#%s %s (%.1f)", codigo, nombre, promedio);
    }
}
