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
public class Coche extends VehiculoTerrestre {
    @Override
    public void realizarAccionEspecial() {
        System.out.println("🚗 El coche " + getName() + " circula por la carretera.");
    }
}