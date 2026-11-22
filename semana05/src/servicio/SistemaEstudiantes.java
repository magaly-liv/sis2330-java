package servicio;

import modelo.Estudiante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class SistemaEstudiantes {

    // Busqueda por codigo en O(1)
    private final HashMap<String, Estudiante> porCodigo = new HashMap<>();

    // Ranking siempre ordenado por promedio descendente
    private final TreeSet<Estudiante> ranking = new TreeSet<>();

    public boolean registrar(Estudiante e) {
        if (e == null || porCodigo.containsKey(e.getCodigo())) {
            return false;
        }
        porCodigo.put(e.getCodigo(), e);
        ranking.add(e);
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

    public boolean actualizarPromedio(String codigo, double nuevoPromedio) {
        Estudiante e = porCodigo.get(codigo);
        if (e == null) {
            return false;
        }
        // El TreeSet ordena con compareTo: hay que sacarlo antes de mutar
        ranking.remove(e);
        try {
            e.setPromedio(nuevoPromedio);
        } finally {
            ranking.add(e);
        }
        return true;
    }

    // Copia en un ArrayList: nunca se expone el TreeSet interno
    public ArrayList<Estudiante> listarRanking() {
        return new ArrayList<>(ranking);
    }

    public ArrayList<Estudiante> topN(int n) {
        ArrayList<Estudiante> top = new ArrayList<>();
        int i = 0;
        for (Estudiante e : ranking) {
            if (i >= n) {
                break;
            }
            top.add(e);
            i++;
        }
        return top;
    }

    public boolean eliminar(String codigo) {
        Estudiante e = porCodigo.remove(codigo);
        if (e == null) {
            return false;
        }
        ranking.remove(e);
        return true;
    }
}
