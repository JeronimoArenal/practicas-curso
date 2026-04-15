package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@SuperBuilder
public abstract class VehiculoTerrestre extends Vehiculo {
    private int numeroRuedas;
    private String tipoTraccion; // Humana, Motor, etc.
}
