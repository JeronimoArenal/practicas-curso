package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Bicicleta extends VehiculoTerrestre {
    private int numeroMarchas;
    private String materialCuadro;


    //............................... Methods .........................................
    public void pedalear() {
        System.out.println("Pedaleando en la " + getNombre());
    }

    // Implementamos la acción especial para que el GestorVehiculo la use
    @Override
    public void realizarAccionEspecial() {
        pedalear();
    }
}