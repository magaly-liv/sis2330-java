# Semana 05: Colecciones

## Sistema de Estudiantes Avanzado

Sistema de consola para gestionar estudiantes (codigo, nombre y promedio de 0 a 100).
Permite registrar, buscar por codigo, actualizar el promedio, ver el ranking completo,
obtener el top N y eliminar. Combina tres colecciones, cada una elegida por una razon concreta.

## Por que cada coleccion

- **HashMap<String, Estudiante>** para buscar por codigo. Las operaciones `get`, `put`,
  `containsKey` y `remove` cuestan O(1) en promedio. Con un `ArrayList` cada busqueda
  recorreria toda la lista (O(n)). El codigo es la clave porque es unico e inmutable.
- **TreeSet<Estudiante>** para el ranking. Mantiene los elementos siempre ordenados segun
  `compareTo` (promedio descendente, desempate por codigo), sin llamar a `sort()` en cada
  consulta. Cuesta O(log n) por insercion, que es el precio de tener el orden siempre listo.
  El desempate por codigo evita que dos estudiantes con el mismo promedio se traten como
  duplicados y uno desaparezca.
- **ArrayList<Estudiante>** para devolver resultados (`listarRanking` y `topN`). Es una copia:
  quien llama a esos metodos no recibe el `TreeSet` interno y no puede modificar el ranking
  por fuera. Ademas permite acceso por posicion y recorrido simple.

Al actualizar un promedio se hace remove, mutar y add en el `TreeSet`, porque el `TreeSet`
calcula la posicion de un elemento al insertarlo. Si se muta con el objeto adentro, queda
en la posicion vieja y el arbol queda inconsistente.

`Estudiante` sobreescribe `equals` y `hashCode` usando solo `codigo`, y ese campo es `final`.

## Estructura

    semana05/src/
      modelo/Estudiante.java                 Comparable, equals, hashCode
      servicio/SistemaEstudiantes.java       HashMap + TreeSet + ArrayList
      Principal.java                         menu de consola

## Compilar y ejecutar

    cd semana05
    mkdir -p out
    javac -d out src/modelo/*.java src/servicio/*.java src/Principal.java
    java -cp out Principal

## Ejemplo de salida

    ===== SISTEMA DE ESTUDIANTES =====
    Opcion: 4
    1. #A003 Carla (95.0)
    2. #A001 Ana (90.0)
    3. #A002 Bruno (85.5)

    Opcion: 3
    Codigo: A002
    Nuevo promedio (0-100): 99
    Promedio actualizado

    Opcion: 5
    N: 2
    1. #A002 Bruno (99.0)
    2. #A003 Carla (95.0)
