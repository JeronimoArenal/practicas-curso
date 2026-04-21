package org.example2;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Necesario para que el Builder vea los campos de la clase padre
public abstract class Base {

    private Long id;
    private String name;
    private String url;
}
