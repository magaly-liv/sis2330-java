# Semana 04: POO II

Gestion de vehiculos con herencia, polimorfismo, clase abstracta e interfaz.

## Que se aprendio

Herencia con `extends` y `super`, polimorfismo con `@Override` y binding dinamico,
clases abstractas, interfaces, `instanceof` y sobreescritura de `toString`, `equals`
y `hashCode`.

## Estructura

    semana04/src/
      modelo/Vehiculo.java        clase abstracta con marca, modelo y velocidadMax
      modelo/Auto.java            subclase con numPuertas
      modelo/Moto.java            subclase con cilindrada
      modelo/Camion.java          subclase con capacidadCargaKg
      modelo/Electrico.java       interfaz con cargarBateria()
      modelo/AutoElectrico.java   extiende Auto e implementa Electrico
      app/GestionVehiculos.java   recorrido polimorfico de una lista de Vehiculo

## Compilar y ejecutar

    cd semana04
    mkdir -p out
    javac -d out src/modelo/*.java src/app/GestionVehiculos.java
    java -cp out app.GestionVehiculos
