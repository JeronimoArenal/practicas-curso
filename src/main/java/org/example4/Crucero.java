package org.example4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class Crucero extends Barco {
    private int capacidadPasajeros;

    @Override
    public void realizarAccionEspecial() {
        System.out.println("El crucero " + getName() + " inicia travesía turística con " + capacidadPasajeros + " personas.");
    }
}