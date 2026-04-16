package org.example;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString
@NoArgsConstructor
@SuperBuilder // Permite que las hijas hereden el Builder y pasarle sus datos a las clases hijas.
//Al ser abstract no se puede instanciar, solo sirve para que otras hereden de ella
//Si sus hij@s no implementan todos los métodos abstract debe declararse Abstract
public abstract class Vehiculo {
    private String nombre;
    private String fabricante;
    private String modelo;


    //............................... Methods .........................................
    public abstract void realizarAccionEspecial();
}
