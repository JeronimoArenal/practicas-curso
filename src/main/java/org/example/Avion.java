package org.example;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter @Setter
//@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
//Declaramos los atributos únicos o métodos de Avion
public class Avion extends VehiculoAerero{
    private String tipo;
    private int numeroMotores;
    private int numeroPasajeros;


    //............................... Methods .........................................
    //el metodo volar() está en la clase padre por lo que no hay que implementarlo, aunque podriamos sobrescribirlo

    @Override
    public void despegar() {
        System.out.println("El avión " + getNombre() + " despega del aeropuerto.");
    }

    @Override
    public void aterrizar() {
        System.out.println("Avión aterrizado.");
    }

    @Override
    public String toString() {
        // Java 17 permite usar Optional para decidir si un String aparece o no
        //ofNullablle para Objetos/String --- of para tipos primitivos
        String datos = Stream.of(
               Optional.ofNullable(getNombre()).map(n -> "Nombre: " + n),           //Vehiculo
               Optional.ofNullable(getFabricante()).map(f -> "Fabricante: " + f),   //Vehículo
               Optional.ofNullable(getModelo()).map(m -> "Modelo: " + m),           //Vehículo
               Optional.of(getVelocidadMaxima()).filter(v -> v > 0).map(v -> "Velocidad: " + v + " km/h"),//VehiculoAereo
               Optional.of(getNumeroPasajeros()).filter(p -> p > 0).map(p -> "Pasajeros: " + p),//Avion
               Optional.ofNullable(tipo).map(t -> "Tipo: " + t),                                        //Avion
               Optional.of(numeroMotores).filter(m -> m > 0).map(m -> "Motores: " + m)          //Avion
                )
                .flatMap(Optional::stream) // Filtra los que están vacíos (nulos)
                .collect(Collectors.joining(", ")); // Une todo con comas

        return "[AVIÓN] " + datos;
    }

}
