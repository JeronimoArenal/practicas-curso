package org.example1;

public class GobalVariable {

    static String global = "Soy una variable global";

    public static void main(String[] args){
        String localMain = "Soy una variable local de main()";
        System.out.println("Tipo de Variable : " + localMain);
        System.out.println("Tipo de Variable : " + global);
        accesoVariable();
        // System.out.println("Tipo de Variable : " + localFuncion); // Error

    }

    public static void accesoVariable(){
        String localFuncion = "Soy una variable local de accesoVariable()";
        System.out.println("Tipo de Variable : " + localFuncion);
        System.out.println("Tipo de Variable : " + global);
        // System.out.println("Tipo de Variable : " + localMain); // Error
    }
}
