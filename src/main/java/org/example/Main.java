package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String nombreVehiculo = "My Avión";

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
                .fabricante("Embraer")   //Clase Abuelo - Vehiculo
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
                .numeroMotores(3)
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

        //............................... Ordenar y Procesar la Flota  .....................................
        gestor.ordenarPorNombreYFabricante();
        gestor.procesarFlota();

        //............................... Búsqueda (true/false) .....................................
        gestor.buscarPorNombre(nombreVehiculo);

        //............................... Búsqueda devolviendo el objeto .................................
        gestor.obtenerPorNombre(nombreVehiculo)
                .ifPresent(v -> { // ¡Aquí sí puedes actuar sobre el objeto!
                    //v.realizarAccionEspecial();
                    v.toString();
                    System.out.println("Vehículo recuperado: " + v.toString());
                });
//                .ifPresent(System.out::println);

//............................... Mantenimiento con Interface Funcional .................................

// Sustituimos todo el filtrado manual y el forEach por una sola llamada al Service:
        gestor.realizarMantenimientoFiltrado(
                // 1. EL FILTRO: ¿A quién? (Solo los que tienen +2 motores)
                v -> v instanceof Motorizado m && m.getNumeroMotores() > 2,

                // 2. LA ACCIÓN: ¿Qué les hacemos?
                v -> "Limpieza profunda de sus " + ((Motorizado) v).getNumeroMotores() + " motores."
        );

        int total = gestor.obtenerVehiculosQueCumplen(v -> v instanceof Motorizado m && m.getNumeroMotores() > 2).size();
        System.out.println("\nTotal de vehículos que han pasado la revisión: " + total);
    }

}