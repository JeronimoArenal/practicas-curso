package org.example;

@FunctionalInterface
public interface IMotorizado {

    //Método abstracto
    int getNumeroMotores();

    // Método default (No rompe la interface funcional)
    default boolean tieneMotores() {
        return getNumeroMotores() > 0;
    }

    //Método estático (No rompe la interface funciona)
    static void imprimirInfo() {
        System.out.println("Interfaz para vehículos con motor");
    }
}
