package org.example4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@SuperBuilder
public abstract class VehiculoMaritimo extends Vehiculo {
    private double eslora; // longitud del barco
}