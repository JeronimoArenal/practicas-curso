package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
public final class Helicoptero extends VehiculoAereo implements Motorizado{
    private int cantidadRotores;
    private int numeroMotores;


    //............................... Methods .........................................
    // Métodos obligatorios de la interfaz Flying que heredamos
    @Override
    public void despegar() {
        System.out.println("El helicóptero " + getNombre() + " despega del helipuerto.");
    }

    @Override
    public void aterrizar() {
        System.out.println("El helicóptero aterrizado.");
    }

    @Override
    protected void enviarMensaje() {
        super.enviarMensaje(); // Llamamos al motodo de la clase padre, para que lo imprima.
        System.out.println("Identificación de helicóptero confirmada.");
    }

    @Override
    public int getNumeroMotores() {
        return this.numeroMotores;
    }

    @Override
    public String toString() {
        // Aprovechamos el numeroMotores también en el toString
        return "[HELICÓPTERO] " + super.toString() +
                ", Rotores: " + cantidadRotores +
                ", Motores: " + numeroMotores;
    }

 /*   @Override ALL poner final en VehiculoAereo se delega la responsabilidad en este
    public void realizarAccionEspecial() {
        this.despegar(); // El helicóptero sabe que su acción es despegar
    } */
}
