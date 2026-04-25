package org.example3;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Solo usa el pasaporte para comparar al ser pasaporte único
public class Passenger {
    private String name;
    @EqualsAndHashCode.Include      //Este es el ID real y EqualsAndHascode ignora los demas atributos a la hora de comparar.
    private String passport;
}
