package modelo;

public class Tarea {

    // Contador compartido por todas las tareas: genera los IDs autoincrementales
    private static int contador = 0;

    private int id;
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        setDescripcion(descripcion);
        this.id = ++contador;
        this.completada = false;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        this.descripcion = descripcion.trim();
    }

    public boolean isCompletada() {
        return completada;
    }

    public void marcarCompletada() {
        this.completada = true;
    }

    @Override
    public String toString() {
        String estado = completada ? "[X]" : "[ ]";
        return estado + " (" + id + ") " + descripcion;
    }
}
