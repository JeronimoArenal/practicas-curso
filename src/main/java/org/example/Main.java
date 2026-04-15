package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. Creamos un Avión tipo Clase, lo que nos permite acceder a las variables de la clase
        Avion miAvion = Avion.builder()
                .nombre("My Avión")   //Clase Abuelo - Vehiculo
                .modelo("747-8")        //Clase Abuelo - Vehiculo
                .fabricante("Boeing")   //Clase Abuelo - Vehiculo
                .velocidadMaxima(988)   //Clase Padre - VehiculoAereo
                .numeroMotores(4)       //Clase Avion
                .numeroPasajeros(410)   //Clase Avion
                .build();
        // 2. Creamos un Helicoptero tipo Clase, lo que nos permite acceder a las variables de la clase
        Helicoptero myHelicoptero = Helicoptero.builder()
                .nombre("Mi Helicoptero")   //Clase Abuelo - Vehiculo
                .modelo("Black Haw")        //Clase Abuelo - Vehiculo
                .fabricante("Embraer")   //Clase Abuelo - Vehiculo
                .velocidadMaxima(258)   //Clase Padre - VehiculoAereo
                .cantidadRotores(2)
                .build();

        // 3. Creamos un Dron tipo Clase, por lo que puede acceder a las variables de la clase
        Dron miDron = Dron.builder()
                .nombre("SkyWatcher")         // Atributo de Vehiculo
                .fabricante("DJI")            // Atributo de Vehiculo
                .modelo("Mavic 3")            // Atributo de Vehiculo
                .velocidadMaxima(75)          // Atributo de VehiculoAereo
                .cantidadHelices(4)           // Atributo de Dron
                .build();

        // 3.1. Creamos otro Dron tipo Interface, por lo que no se puede acceder a las variables de la clase fuera de aqui (builder)
        Flying myDron = Dron.builder()
                .nombre("C3PO")
                .fabricante("Steven Spielberg")
                .modelo("Guerra de las galaxias")
                .cantidadHelices(3)
                .build();

        //4. Creamos una Bicicleta
        BicicletaCarrera miBici = BicicletaCarrera.builder()
                .nombre("Specialized Tarmac")
                .fabricante("Decathlon")
                .numeroRuedas(2)
                .anchoNeumatico(25.5)
                .build();

        //5. Creamos una Bicicleta Electrica
        BicicletaElectrica myBici = BicicletaElectrica.builder()
                .nombre("Bici Aria")
                .fabricante("London Calling")
                .modeloMotor("Bosch Performance Line") // Se asigna aquí y no cambia más
                .nivelBateria(50)
                .build();

       System.out.println("--- Iniciando operaciones de vuelo ---" + "\n");

/*      miAvion.despegar();
        miAvion.volar(10000);
        miAvion.aterrizar();
        System.out.println(miAvion.toString());

        System.out.println();

        myHelicoptero.despegar();
        myHelicoptero.volar(10000);
        myHelicoptero.aterrizar();
        System.out.println(myHelicoptero.toString());

        System.out.println();

        miDron.despegar();
        miDron.volar(955);//Declaramos los atributos únicos o métodos de Dron
        miDron.aterrizar();
        System.out.println(miDron.toString());

        // println no accede a las variables, sino que llama al metodo toString()
        //es decir no podriamos utilizar myDron.getVariables o myDron.metodosdeClase.
        //El compilador de Java solo mira el tipo de la variable (la parte izquierda), no el objeto real (la parte derecha)."
        System.out.println();

        myDron.despegar();
        myDron.volar(955);
        myDron.aterrizar();
        //Hacemos un Casting (obligando a Java a mirar detrás de la máscara):
        if (myDron instanceof Dron) {
            Dron dronReal = (Dron) myDron; // Casting
            System.out.println("Haciendo un Casting te puedo mostrar mi numero de hélices: "+ dronReal.getCantidadHelices()); // ✅ Ahora sí funciona
        }
        System.out.println(myDron.toString());
*/


        // El mismo método estático gestiona objetos totalmente distintos
        GestorVehiculo.presentarVehiculo(miAvion);
        GestorVehiculo.presentarVehiculo(myHelicoptero);
        GestorVehiculo.presentarVehiculo(miDron);
        GestorVehiculo.presentarVehiculo((Vehiculo) myDron); //Casting necesario porque no es tipo Vehiculo, sino Flying (interface)
        GestorVehiculo.presentarVehiculo(miBici);
        GestorVehiculo.presentarVehiculo(myBici);

        //Otra manera de presentar lo mismo que arriba mediante una LIST
        // Polimorfismo: Una lista de 'Vehiculo' acepta cualquier tipo de Vehículo.
        List<Vehiculo> miFlota = List.of(miAvion,
                miDron,
                (Vehiculo) myDron, // <--- Casting necesario aquí
                miBici,
                myBici,
                myHelicoptero);

        // Ejecutamos todo el proceso con una sola línea
        GestorVehiculo.procesarFlota(miFlota);

        }
}