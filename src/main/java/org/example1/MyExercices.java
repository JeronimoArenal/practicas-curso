package org.example1;

import java.util.ArrayList;

public class MyExercices {

    // Constructor privado para que nadie pueda hacer "new MyExercices()"
    private MyExercices(){
    }

        /*
    1. Escriba una función que realice la suma de todos los elementos de un array de integers.
    */

    static int sumElements(int[] array) {
        int sum = 0;
        // Iteramos sobre el array
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

        /*
    2. Escribir una función que realice la media de todos los elementos de un array de integers. Sobrecarga (Overload) los metodos
     */

    static int averageElements(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    static int averageElements(ArrayList<Integer> array) {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        return sum / array.size();
    }


}
