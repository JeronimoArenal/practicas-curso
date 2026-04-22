package org.example4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Barco extends VehiculoMaritimo {
    private String puertoBase;
}
