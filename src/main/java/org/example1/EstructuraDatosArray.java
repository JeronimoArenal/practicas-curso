package org.example1;

import java.util.Arrays;

public class EstructuraDatosArray {

    public static void main(String[] args) {
        double[] data;
        data = new double[10];
        data[4] = 35;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == 35) {
                // Aquí imprimimos la 'i', que es el índice donde está el 35
                System.out.println("El valor 35 se encuentra en el índice: " + i);
                System.out.println("Imprimimos el valor del array 4 " + data[i]);
            }
        }

      // final int PAISES = 7;
      // final int MEDALLAS = 3;
      // int[][] cuenta = new int[PAISES][MEDALLAS];
      // cuenta[0][0] = 0;

        /*
        Recorremos un Array bidimensional e imprimimos sus valores
         */
        final int PAISES = 7;
        final int MEDALLAS = 3;
        int[][] cuenta = {{ 0, 0, 1 },
                { 0, 1, 1 },
                { 1, 0, 0 },
                { 3, 0, 1 },
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 0, 2, 0 }
        };

        for (int i = 0; i < cuenta.length; i++) {// Proceso fila ith. SUstituimos PAISES por cuenta.length
            for (int j = 0; j < cuenta[i].length; j++) { // Procesa la jth columna en la fila ith. Sustituor MEDALLLAS po length
                System.out.printf("%8d", cuenta[i][j]);
            }
            System.out.println(); // Cambio línea al final de la fila
        }


        /*
        Array bidimensiona de conches, imprimiendo valor de array, bucle con indices y valores de array con for-each
         */
        String [][] coches = {
                {"Audi", "BMW", "Seat"},
                {"Renault", "Toyota", "Skoda", "Tesla"}
        };
        System.out.println(coches[1][3]); // Accedemos a un elemento
        System.out.println(coches.length); // Imprime 2 porque hay 2 arrays

        for(int i = 0; i < coches.length; i++){ // Iteramos sobre este array
            for(int j = 0; j < coches[i].length; j++) {
                System.out.println("El coche en el índice " + i + "," + j + " es " + coches[i][j]);
            }
        }

        for (String[] fila : coches) {          // 1. El primer for-each recorre las filas (que son arrays de Strings)
            for (String coche : fila) {         // 2. El segundo for-each recorre cada elemento de esa fila
                System.out.println("Coche: " + coche);
            }
        }

        /*
        Relllenamos un Array
         */
        int[] datos = new int[11];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = i * i;
        }
        System.out.println("Rellenando un array e imprimiendo con toString " + Arrays.toString(datos));

        //Incluimos separador para imprimir
        System.out.print("Imprimimos el array rellenado con separador ");
        for (int i = 0; i < datos.length; i++) {
            if (i > 0) {
                System.out.print(" | ");
            }
            System.out.print(datos[i]);
        }
        System.out.println();

        /*
        Buscamos un valor en su posición
         */
        int valorBuscado = 100;
        int pos = 0;
        boolean found = false;

        while (pos < datos.length && !found) {
            if (datos[pos] == valorBuscado) {
                found = true;
            } else {
                pos++;
            }
        } // cerramos el while

        if (found) {
            System.out.println("Valor Hallado en la posicion: " + pos);
        } else {
            System.out.println("No encontrado");
        }
    }

}
