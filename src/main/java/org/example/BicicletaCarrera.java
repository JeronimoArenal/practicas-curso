package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor
@SuperBuilder
public final class BicicletaCarrera extends Bicicleta {
    private double anchoNeumatico;

    //............................... Methods .........................................
    @Override
    public String toString() {
        return "[BICI DE CARRERAS] " + getNombre() + " del fabricante "+ getFabricante() + " con neumáticos de " + anchoNeumatico + "mm";
    }
}