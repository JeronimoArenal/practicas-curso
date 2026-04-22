package org.example4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Vehiculo extends Base {
    private String marca;
    private String modelo;

    // Este método lo tendrán que implementar las clases finales (Coche, Avión, etc.)
    public abstract void realizarAccionEspecial();
}