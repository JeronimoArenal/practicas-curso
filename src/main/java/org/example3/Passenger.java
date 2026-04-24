package org.example3;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Solo usa el pasaporte para comparar al ser pasaporte único
public class Passenger {
    private String name;

    @EqualsAndHashCode.Include      //indica que este campo debe ser utilizado para determinar si dos objetos son iguales
    private String passport;
}
