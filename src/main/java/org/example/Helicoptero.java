package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
public class Helicoptero extends VehiculoAerero {
    private int cantidadRotores;


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
    public String toString() {
        // super.toString() llama al toString de VehiculoAereo
        // que a su vez llama al de Vehiculo (por el callSuper = true que hay allí)
        return "🚁 [HELICÓPTERO] " + super.toString() + ", Rotores: " + cantidadRotores;
    }

    @Override
    public void realizarAccionEspecial() {
        this.despegar(); // El helicóptero sabe que su acción es despegar
    }
}
