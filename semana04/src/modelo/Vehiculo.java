package modelo;

public abstract class Vehiculo {

    protected String marca;
    protected String modelo;
    protected double velocidadMax;

    public Vehiculo(String marca, String modelo, double velocidadMax) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("La marca no puede estar vacia");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacio");
        }
        if (velocidadMax <= 0) {
            throw new IllegalArgumentException("La velocidad maxima debe ser mayor a 0");
        }
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMax = velocidadMax;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getVelocidadMax() {
        return velocidadMax;
    }

    // Cada subclase decide como describirse
    public abstract String describir();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{marca='" + marca + "', modelo='" + modelo
                + "', velocidadMax=" + velocidadMax + "}";
    }
}
