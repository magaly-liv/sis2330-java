package modelo;

public class AutoElectrico extends Auto implements Electrico {

    private int autonomiaKm;
    private int nivelBateria;

    public AutoElectrico(String marca, String modelo, double velocidadMax,
                         int numPuertas, int autonomiaKm) {
        super(marca, modelo, velocidadMax, numPuertas);
        this.autonomiaKm = autonomiaKm;
        this.nivelBateria = 20;
    }

    public int getAutonomiaKm() {
        return autonomiaKm;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    @Override
    public String describir() {
        return super.describir() + " | Electrico: autonomia " + autonomiaKm + " km";
    }

    @Override
    public void cargarBateria() {
        nivelBateria = 100;
        System.out.println("Cargando bateria del " + marca + " " + modelo
                + "... nivel: " + nivelBateria + "%");
    }
}
