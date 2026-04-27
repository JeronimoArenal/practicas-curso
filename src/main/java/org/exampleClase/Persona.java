package org.exampleClase;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Persona(String nombre,
                      String primerApellido,
                      String segundoApellido,
                      LocalDate fechaNacimiento,
                      Genero genero) {

}