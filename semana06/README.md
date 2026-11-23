# Semana 06: Maven y JUnit 5

## Descripcion

Proyecto Maven que implementa un Sistema de Estudiantes: registrar estudiantes con codigo
unico, nombre y promedio (0 a 100), buscar por codigo, mostrar el ranking por promedio
descendente, obtener el top N, actualizar promedios y eliminar. Incluye una suite de
20 tests automatizados con JUnit 5.

## Estructura

    semana06/
      pom.xml
      src/main/java/
        Principal.java                           menu de consola
        modelo/Estudiante.java                   Comparable, equals y hashCode por codigo
        servicio/SistemaEstudiantes.java         HashMap + TreeSet + ArrayList
      src/test/java/
        servicio/SistemaEstudiantesTest.java     20 tests de JUnit 5

El `pom.xml` usa Java 17 (`maven.compiler.release`) y JUnit 5 con `scope=test`.
`target/` esta en el `.gitignore` y nunca se sube a Git.

## Como compilar, testear y empaquetar

    cd semana06
    mvn compile
    mvn test
    mvn package
    java -jar target/sistema-estudiantes-1.0-SNAPSHOT.jar

## Tests

Cada test parte de un `SistemaEstudiantes` vacio gracias a `@BeforeEach`.

1. `registrarEstudianteNuevoDevuelveTrue`: un estudiante nuevo se registra.
2. `registrarConCodigoDuplicadoDevuelveFalse`: no se permiten codigos repetidos.
3. `registrarNuloDevuelveFalse`: registrar null no rompe el sistema.
4. `buscarPorCodigoExistenteDevuelveElEstudiante`: la busqueda por codigo encuentra al estudiante.
5. `buscarPorCodigoInexistenteDevuelveNull`: un codigo desconocido devuelve null.
6. `listarRankingOrdenaPorPromedioDescendente`: el ranking va de mayor a menor promedio.
7. `listarRankingConPromediosIgualesDesempataPorCodigo`: con promedios iguales manda el codigo.
8. `listarRankingSinEstudiantesDevuelveListaVacia`: caso borde, sistema vacio.
9. `listarRankingDevuelveUnaCopiaQueNoAfectaAlSistema`: el ranking devuelto es una copia.
10. `actualizarPromedioReordenaElRanking`: cambiar un promedio reordena el ranking.
11. `actualizarPromedioDeCodigoInexistenteDevuelveFalse`: actualizar un codigo desconocido devuelve false.
12. `actualizarPromedioInvalidoLanzaExcepcionYConservaElPromedio`: un promedio invalido falla sin corromper el ranking.
13. `topNDevuelveSoloLosNMejores`: el top N respeta el limite.
14. `topNMayorQueLaCantidadDevuelveTodos`: caso borde, N mayor que la cantidad.
15. `topNCeroDevuelveListaVacia`: caso borde, N igual a cero.
16. `eliminarEstudianteExistenteLoQuitaDeBusquedaYRanking`: eliminar actualiza mapa y ranking.
17. `eliminarCodigoInexistenteDevuelveFalse`: eliminar un codigo desconocido devuelve false.
18. `estudianteConPromedioFueraDeRangoLanzaExcepcion`: limites 101 y -1 rechazados.
19. `estudianteConCodigoVacioLanzaExcepcion`: el codigo no puede estar vacio.
20. `estudiantesConElMismoCodigoSonIguales`: equals y hashCode se basan en el codigo.

## Salida real de mvn test

    Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
