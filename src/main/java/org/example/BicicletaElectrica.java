package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@Getter @Setter
//@NoArgsConstructor // NO se puede utilizar con "final" escepto qye le demos un valor
@SuperBuilder
public final class BicicletaElectrica extends Bicicleta implements Cargable{
    private int nivelBateria;
    private final String modeloMotor;



    //............................... Methods .........................................
    @Override
    public void cargarBateria(int nivelBateria) {
        // 1. Validamos que el valor esté entre 0 y 100
        if (nivelBateria >= 0 && nivelBateria <= 100) {
            this.nivelBateria = nivelBateria; // OBLIGATORIO: asigna el parámetro al atributo
            System.out.println("Batería cargada al " + this.nivelBateria + "%.");
        } else {
            System.out.println("Error: El nivel de batería debe estar entre 0 y 100.");
        }
    }

    //Sobreescribimos el metodo pedalear() de Bicicleta
    @Override
    public void pedalear() {
        // Opción A: Sustituir el comportamiento por completo
        System.out.println("Pedalear con asistencia eléctrica en la " + getNombre() + ". ¡Es más fácil!");
    }

    @Override
    public void realizarAccionEspecial() {
        // 1. Llama al pedalear() sobreescrito de esta clase
        this.pedalear();
        // 2. Ejecuta la lógica de carga que antes tenías en el Gestor
        this.cargarBateria(this.nivelBateria + 10);
    }

    //Utilizamos StringJoiner para construir cadenas de texto y le pasamos los 3 parametros necesarios.
    @Override
    public String toString() {
        // Configuramos el prefijo y el delimitador
        StringJoiner joiner = new StringJoiner(", ", "[BICI ELÉCTRICA] ", "");

        // Agregamos solo si tienen contenido (no null y no vacío)
        if (getNombre() != null && !getNombre().isEmpty()) {
            joiner.add("Nombre: " + getNombre());
        }

        if (getFabricante() != null && !getFabricante().isEmpty()) {
            joiner.add("Fabricante: " + getFabricante());
        }

        if (getModelo() != null && !getModelo().isEmpty()) {
            joiner.add("Modelo: " + getModelo());
        }

        if (getModeloMotor() != null && !getModeloMotor().isEmpty()) {
            joiner.add("Modelo motor: " + getModeloMotor());
        }

        // El nivel de batería siempre se muestra (al ser int, su valor mínimo es 0)
        joiner.add("Batería: " + nivelBateria + "%");

        return joiner.toString();
    }
}
