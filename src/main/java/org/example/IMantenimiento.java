package org.example;

@FunctionalInterface
// Un solo método abstracto: recibe el vehículo y devuelve un mensaje del resultado
public interface IMantenimiento {
    String ejecutar(Vehiculo v);
}
