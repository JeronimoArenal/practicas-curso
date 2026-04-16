package org.example;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
//Al ser una clase abstracta, hereda los métodos de la Interface pero no está obligada a darles cuerpo.
//Pasa la obligación a la primera clase (no abstracta). Si no lo fuese si estaría obligada a implementar todos los métodos
public abstract class VehiculoAerero extends Vehiculo implements Flying{
    private int altitudActual;
    private int velocidadMaxima;


    //............................... Methods .........................................
    @Override
    public void realizarAccionEspecial() {
        // Definimos la secuencia estándar para cualquier cosa que vuele
        this.enviarMensaje();
        this.despegar();   // Método de la interfaz Flying
        this.volar(1500);  // Método implementado abajo
        this.aterrizar();  // Método de la interfaz Flying
    }

    @Override
    public void volar(int altitud) {
        this.altitudActual = altitud; // OBLIGATORIO: asigna el parámetro al atributo y actualizamos el estado real del vehículo.
        System.out.println(getNombre() + " volando a " + altitud + " metros.");
    }

    public void enviarMensaje(){
        System.out.println("enviando mensaje a la torre de control");
    }
}
