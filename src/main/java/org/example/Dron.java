package org.example;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Getter @Setter
@NoArgsConstructor
//@ToString(callSuper = true)
@SuperBuilder
public final class Dron extends VehiculoAerero implements Cargable{
    private int cantidadHelices;
    private int nivelBateria;


    //............................... Methods .........................................
    //el metodo volar() está en la clase padre por lo que no hay que implementarlo, aunque podriamos sobrescribirlo

    @Override
    public void despegar() {

        System.out.println("El dron " + getNombre() + " se eleva verticalmente.");
    }

    @Override
    public void aterrizar() {

        System.out.println("Dron aterrizado en base.");
    }

    @Override
    public void cargarBateria(int porcentaje) {
        this.nivelBateria = Math.min(this.nivelBateria + porcentaje, 100);
        System.out.println("🛸 Batería del dron cargada al " + this.nivelBateria + "%.");
    }

    //Utilizamos StringJoiner para construir cadenas de texto y le pasamos los 3 parametros necesarios.
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[DRON] ", "");

        if (getNombre() != null)
            joiner.add("Nombre: " + getNombre());
        if (getFabricante() != null)
            joiner.add("Fabricante: " + getFabricante());
        if(getModelo() != null){
            joiner.add("Modelo: " + getModelo());
        }

        joiner.add("Hélices: " + cantidadHelices);
        joiner.add("Batería: " + nivelBateria + "%");

        return joiner.toString();
    }

}
