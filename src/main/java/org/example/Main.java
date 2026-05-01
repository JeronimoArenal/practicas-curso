package org.example;

public class Main {

    public static void main(String[] args) {

        // Obtenemos la instancia del Singleton
        GestorVehiculoService gestor = GestorVehiculoService.get();

        // Creamos un Avión tipo Clase, lo que nos permite acceder a las variables de la clase
        Avion miAvion = Avion.builder()
                .nombre("My Avión")   //Clase Abuelo - Vehiculo
                .modelo("747-8")        //Clase Abuelo - Vehiculo
                .fabricante("Boeing")   //Clase Abuelo - Vehiculo
                .velocidadMaxima(988)   //Clase Padre - VehiculoAereo
                .numeroMotores(4)       //Clase Avion
                .numeroPasajeros(410)   //Clase Avion
                .build();

        Avion myAvion = Avion.builder()
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
//        Flying myDron = Dron.builder()
//                .nombre("C3PO")
//                .fabricante("Steven Spielberg")
//                .modelo("Guerra de las galaxias")
//                .cantidadHelices(3)
//                .build();

        Dron myDron = Dron.builder()
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

        //............................... Registro de la Flota  .....................................
        gestor.registrar(
                miAvion,
                myAvion,
                miDron,
                myDron,
                myHelicoptero,
                myBici,
                miBici
        );


        //............................... Procesar la Flota  .....................................
        gestor.procesarFlota();

        //............................... Búsqueda  .....................................
        gestor.buscarPorNombre("SkyWatcher");

    }

}