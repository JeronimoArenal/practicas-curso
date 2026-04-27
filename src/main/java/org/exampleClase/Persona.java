package org.exampleClase;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Comparator;

@Builder
public record Persona (String nombre,
                      String primerApellido,
                      String segundoApellido,
                      LocalDate fechaNacimiento,
                      Genero genero,
                      double salario)
    implements Comparable<Persona>
                      {

    //................................... M E T H O D S ...........................................
    @Override
    public int compareTo(Persona persona) {
        int cmpPrimerApellido = this.primerApellido()
                .compareTo(persona.primerApellido());
        int cmpSegundoApellido = this.segundoApellido()
                .compareTo(persona.segundoApellido());
        int cmpNombre = this.nombre()
                .compareTo(persona.nombre());
        return cmpPrimerApellido != 0 ? cmpPrimerApellido :
                cmpSegundoApellido != 0 ? cmpSegundoApellido : cmpNombre;
                          }

    /*                      @Override
                          public int compareTo(Persona persona) {
                              return Comparator.comparing(Persona::primerApellido)
                                      .thenComparing(Persona::segundoApellido)
                                      .thenComparing(Persona::nombre)
                                      .compare(this, persona);
                          }
                          */

 }