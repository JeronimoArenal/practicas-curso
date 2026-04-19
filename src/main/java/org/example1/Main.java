package org.example1;

import java.time.*;

public class Main {
    public static final int NUMEROS_ENTEROS = 100;

    public static void main(String[] args){
        int[] lista = new int [NUMEROS_ENTEROS];

    /*    for(int i = 0; i < NUMEROS_ENTEROS; i++){
            lista[i] = i + 1;
            System.out.println("[" + i + "] " + lista[i]);
        }
       // Empezamos en 99 (NUMEROS_ENTEROS - 1)
        for (int i = NUMEROS_ENTEROS - 1; i >= 0; i--) {
            System.out.println("Posición: " + i + " - Valor: " + lista[i]);

        } */


        fechora();
        clascad();
        prePost();
        postPre();
        buscarNombre();
    }


    //Pizarra fecha y hora
    public static void fechora() {
        LocalDate date = LocalDate.of(2019, 11, 11); //1989-11-11
        System.out.println(date.getYear()); //2019
        System.out.println(date.getMonth()); //NOVEMBER
        System.out.println(date.getDayOfMonth()); //11

        LocalTime time = LocalTime.of(5, 30, 45, 35); //05:30:45:35
        System.out.println(time.getHour()); //5
        System.out.println(time.getMinute()); //30
        System.out.println(time.getSecond()); //45
        System.out.println(time.getNano()); //35

        LocalDateTime dateTime = LocalDateTime.of(1989, 11, 11, 5, 30, 45, 35); //1989-11-11T05:30:45.000000035
        //duration
        LocalTime localTime1 = LocalTime.of(12, 25);

        LocalTime localTime2 = LocalTime.of(17, 35);
        Duration duration = Duration.between(localTime1, localTime2);
        //period
        LocalDate localDate1 = LocalDate.of(2016, Month.JULY, 18);
        LocalDate localDate2 = LocalDate.of(2016, Month.JULY, 20);
        Period period = Period.between(localDate1, localDate2);


        LocalDate pascua;
        pascua = LocalDate.of(2014, 4, 20);
        LocalDate ascension;
        ascension = pascua.plusDays(39);

        LocalDate diaPartido;
        diaPartido = LocalDate.of(2014, 7, 13);
        LocalTime horaPartido;
        horaPartido = LocalTime.of(21, 00);
        LocalDateTime fin;
        fin = diaPartido.atTime(horaPartido);

    }

    //pizarra cadenas
    public static void clascad() {
        String cadena1 = "el invierno será lluvioso";
        String cadena2 = "el invierno será frío";


        System.out.println("el tercer carácter de la cadena1 es: " + cadena1.charAt(3));
        System.out.println("la cadena1 contiene: " + cadena1.length() + " caracteres");
        if (cadena1.equals(cadena2)) {
            System.out.println("las dos cadenas son idénticas");
        } else {
            System.out.println("las dos cadenas son diferentes");
        }
        if (cadena1.compareTo(cadena2) > 0) {
            System.out.println("cadena1 es superior a cadena2");
        } else if (cadena1.compareTo(cadena2) < 0) {
            System.out.println("cadena1 es inferior a cadena2");
        } else {
            System.out.println("las dos cadenas son idénticas");
        }

        String cadena = " segovia ";
        System.out.println("longitud de la cadena: " + cadena.length());
        System.out.println("longitud de la cadena sin espacios: " + cadena.trim().length());

        String busqueda = "e";
        int posicion = cadena1.indexOf(busqueda);
        while (posicion >= 0) {
            System.out.println("cadena encontrada en la posición: " + posicion);
            posicion = cadena1.indexOf(busqueda, posicion + 1);
        }

        //formateo
        int edad = 28;
        String nombre = "David";
        String patron = "El nombre de la persona es %s y tiene %d años";

        String resultado = String.format(patron, nombre, edad);

        System.out.print(resultado); // Pinta esto por pantalla -> El nombre de la persona es David y tiene 28 años

        int hora = 13;
        int minutos = 45;
        String patron1 = "%s ha accedido a las %d:%d h";
        String resultado1 = String.format(patron1, nombre, hora, minutos);
        System.out.println(resultado1);
        // Pinta esto por pantalla -> David ha accedido a las 13:45 h
    }

    public static void prePost(){
        int a = 10;
        int b = a++;
        System.out.println("a: " + a + " b: " + b );
    }
    public static void postPre(){
        int a = 10;
        int b = ++a;
        System.out.println("a: " + a + " b: " + b );
    }

    public static void buscarNombre() {
        String[] nombres = {"Ana", "Pedro", "Luis", "Marta", "Juan"};
        String objetivo = "marta";

        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(objetivo)) {
                System.out.println("¡Encontrado! " + nombres[i] + " está en la posición: " + i);
            }
        }
    }

}
