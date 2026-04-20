package org.example1;

import java.util.ArrayList;
import java.util.Arrays;

public class EstructuraDatosArray {

    public static void main(String[] args) {
        // 1. Ejemplo con double y búsqueda de índice
        ejemploDouble();

        // 2. Manejo de medallas (Array bidimensional)
        mostrarMedallas();

        // 3. Manejo de coches (Array bidimensional con for-each)
        mostrarCoches();

        // 4. Rellenar, imprimir y buscar en array de enteros
        int[] datos = generarArrayCuadrados();
        imprimirConSeparador(datos);
        buscarValor(datos, 100);

        // 5. Ordenación por burbuja
        ordenarYMostrar();

        // 1. Suma de elementos
        int suma = MyExercices.sumElements(datos);
        System.out.println("La suma de los elementos es: " + suma);

        // 2. Promedio elementos
        int media = MyExercices.averageElements(datos);
        System.out.println("La media de los cuadrados es: " + media);

        ArrayList<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(10);
        listaNumeros.add(20);
        listaNumeros.add(30);

        //Llamando a ArrayList
        int mediaLista = MyExercices.averageElements(listaNumeros);
        System.out.println("La media de la lista del ArrayList es: " + mediaLista);
    }

    static void ejemploDouble() {
        double[] data = new double[10];
        data[4] = 35;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 35) {
                System.out.println("El valor 35 se encuentra en el índice: " + i);
            }
        }
    }

    static void mostrarMedallas() {
        int[][] cuenta = {
                { 0, 0, 1 }, { 0, 1, 1 }, { 1, 0, 0 }, { 3, 0, 1 },
                { 0, 1, 0 }, { 0, 0, 1 }, { 0, 2, 0 }
        };
        for (int i = 0; i < cuenta.length; i++) {
            for (int j = 0; j < cuenta[i].length; j++) {
                System.out.printf("%8d", cuenta[i][j]);
            }
            System.out.println();
        }
    }

    static void mostrarCoches() {
        String[][] coches = {
                {"Audi", "BMW", "Seat"},
                {"Renault", "Toyota", "Skoda", "Tesla"}
        };
        for (String[] fila : coches) {
            for (String coche : fila) {
                System.out.println("Coche: " + coche);
            }
        }
    }

    static int[] generarArrayCuadrados() {
        int[] datos = new int[11];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = i * i;
        }
        System.out.println("Array generado: " + Arrays.toString(datos));
        return datos;
    }

    static void imprimirConSeparador(int[] datos) {
        System.out.print("Array con separador: ");
        for (int i = 0; i < datos.length; i++) {
            if (i > 0) System.out.print(" | ");
            System.out.print(datos[i]);
        }
        System.out.println();
    }

    static void buscarValor(int[] datos, int valorBuscado) {
        int pos = 0;
        boolean found = false;
        while (pos < datos.length && !found) {
            if (datos[pos] == valorBuscado) found = true;
            else pos++;
        }
        if (found) System.out.println("Valor " + valorBuscado + " hallado en posición: " + pos);
        else System.out.println("Valor no encontrado");
    }

    static void ordenarYMostrar() {
        int[] arr = {5, 2, 7, 1, 98, 1000, 1};
        burbuja(arr);
        System.out.println("Array ordenado: " + Arrays.toString(arr));
    }

    /*
    Método Burbuja
     */
    static void burbuja (int[] arr){
        int n = arr.length;                        // Guardamos la longitud en n que nos permite hacer el código más limpio

        for(int i = 0; i < n; i++){                // Por cada elemento recorremos el array entero
            for(int j = 1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){             // cambiamos los elementos
                    int temp = arr[j-1];           // Guardamos un elemento en una variable
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }







}
