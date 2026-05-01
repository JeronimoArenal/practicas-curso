package org.example;

@FunctionalInterface
public interface Mantenimiento {
    // Un solo método abstracto: recibe el vehículo y devuelve un mensaje del resultado
    String ejecutar(Vehiculo v);
}
