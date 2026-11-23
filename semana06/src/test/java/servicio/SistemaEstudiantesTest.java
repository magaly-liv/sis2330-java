package servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import modelo.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaEstudiantesTest {

    private SistemaEstudiantes sistema;

    // Cada test parte de un sistema vacio
    @BeforeEach
    void setUp() {
        sistema = new SistemaEstudiantes();
    }

    private void registrar(String codigo, String nombre, double promedio) {
        sistema.registrar(new Estudiante(codigo, nombre, promedio));
    }

    @Test
    void registrarEstudianteNuevoDevuelveTrue() {
        assertTrue(sistema.registrar(new Estudiante("A001", "Ana", 90)));
    }

    @Test
    void registrarConCodigoDuplicadoDevuelveFalse() {
        registrar("A001", "Ana", 90);
        assertFalse(sistema.registrar(new Estudiante("A001", "Otra", 50)));
        assertEquals(1, sistema.listarRanking().size());
    }

    @Test
    void registrarNuloDevuelveFalse() {
        assertFalse(sistema.registrar(null));
    }

    @Test
    void buscarPorCodigoExistenteDevuelveElEstudiante() {
        registrar("A001", "Ana", 90);
        Estudiante e = sistema.buscarPorCodigo("A001");
        assertNotNull(e);
        assertEquals("Ana", e.getNombre());
    }

    @Test
    void buscarPorCodigoInexistenteDevuelveNull() {
        registrar("A001", "Ana", 90);
        assertNull(sistema.buscarPorCodigo("Z999"));
    }

    @Test
    void listarRankingOrdenaPorPromedioDescendente() {
        registrar("A001", "Ana", 90);
        registrar("A002", "Bruno", 85.5);
        registrar("A003", "Carla", 95);
        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertEquals("A003", ranking.get(0).getCodigo());
        assertEquals("A001", ranking.get(1).getCodigo());
        assertEquals("A002", ranking.get(2).getCodigo());
    }

    @Test
    void listarRankingConPromediosIgualesDesempataPorCodigo() {
        registrar("A002", "Bruno", 90);
        registrar("A001", "Ana", 90);
        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertEquals(2, ranking.size());
        assertEquals("A001", ranking.get(0).getCodigo());
        assertEquals("A002", ranking.get(1).getCodigo());
    }

    @Test
    void listarRankingSinEstudiantesDevuelveListaVacia() {
        assertTrue(sistema.listarRanking().isEmpty());
    }

    @Test
    void listarRankingDevuelveUnaCopiaQueNoAfectaAlSistema() {
        registrar("A001", "Ana", 90);
        ArrayList<Estudiante> copia = sistema.listarRanking();
        copia.clear();
        assertEquals(1, sistema.listarRanking().size());
    }

    @Test
    void actualizarPromedioReordenaElRanking() {
        registrar("A001", "Ana", 90);
        registrar("A002", "Bruno", 85.5);
        assertTrue(sistema.actualizarPromedio("A002", 99));
        ArrayList<Estudiante> ranking = sistema.listarRanking();
        assertEquals("A002", ranking.get(0).getCodigo());
        assertEquals(99.0, ranking.get(0).getPromedio(), 0.0001);
    }

    @Test
    void actualizarPromedioDeCodigoInexistenteDevuelveFalse() {
        assertFalse(sistema.actualizarPromedio("Z999", 80));
    }

    @Test
    void actualizarPromedioInvalidoLanzaExcepcionYConservaElPromedio() {
        registrar("A001", "Ana", 80);
        assertThrows(IllegalArgumentException.class,
                () -> sistema.actualizarPromedio("A001", 150));
        assertEquals(80.0, sistema.buscarPorCodigo("A001").getPromedio(), 0.0001);
        assertEquals(1, sistema.listarRanking().size());
    }

    @Test
    void topNDevuelveSoloLosNMejores() {
        registrar("A001", "Ana", 90);
        registrar("A002", "Bruno", 85.5);
        registrar("A003", "Carla", 95);
        ArrayList<Estudiante> top = sistema.topN(2);
        assertEquals(2, top.size());
        assertEquals("A003", top.get(0).getCodigo());
        assertEquals("A001", top.get(1).getCodigo());
    }

    @Test
    void topNMayorQueLaCantidadDevuelveTodos() {
        registrar("A001", "Ana", 90);
        registrar("A002", "Bruno", 85.5);
        assertEquals(2, sistema.topN(10).size());
    }

    @Test
    void topNCeroDevuelveListaVacia() {
        registrar("A001", "Ana", 90);
        assertTrue(sistema.topN(0).isEmpty());
    }

    @Test
    void eliminarEstudianteExistenteLoQuitaDeBusquedaYRanking() {
        registrar("A001", "Ana", 90);
        registrar("A002", "Bruno", 85.5);
        assertTrue(sistema.eliminar("A001"));
        assertNull(sistema.buscarPorCodigo("A001"));
        assertEquals(1, sistema.listarRanking().size());
        assertEquals("A002", sistema.listarRanking().get(0).getCodigo());
    }

    @Test
    void eliminarCodigoInexistenteDevuelveFalse() {
        registrar("A001", "Ana", 90);
        assertFalse(sistema.eliminar("Z999"));
        assertEquals(1, sistema.listarRanking().size());
    }

    @Test
    void estudianteConPromedioFueraDeRangoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("A001", "Ana", 101));
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("A001", "Ana", -1));
    }

    @Test
    void estudianteConCodigoVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante("  ", "Ana", 80));
    }

    @Test
    void estudiantesConElMismoCodigoSonIguales() {
        Estudiante a = new Estudiante("A001", "Ana", 90);
        Estudiante b = new Estudiante("A001", "Otro nombre", 50);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
