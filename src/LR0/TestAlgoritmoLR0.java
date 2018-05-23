package LR0;

import GramaticaDeGramaticas.Gramatica;

public class TestAlgoritmoLR0 {
    
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica();
        GramaticaLR0 gramaticaDeGramaticaLR0 = new GramaticaLR0(gramatica);
        String cadena =   
            "E' -> E;" +
            "E -> E + T | T ;" +
            "T -> T * F | F ;" +
            "F -> ( E ) | num ;";
        
        boolean pasoLexico = gramaticaDeGramaticaLR0.analisisLexico(cadena);
        if(pasoLexico){
            System.out.println("\nEl Analisis Léxico fue correcto\n");
            boolean pasoSintactico = gramaticaDeGramaticaLR0.analisisSintactico(cadena, gramatica);
            if(pasoSintactico){
                System.out.println("Análisis Sintáctico Correcto");
                gramaticaDeGramaticaLR0.algoritmoLR0();
                //String expresion = "( num + num ) * num - num $";
                //String expresion = "( SIMB OR ( SIMB & SIMB ) + ) ? OR SIMB $";
                //boolean cadenaValida = gramaticaDeGramaticaLL1.analizarCadenaLL1(expresion);
                //if(cadenaValida)
                //   System.out.println("Cadena Aceptada");
                //else
                //   System.out.println("ERROR cadena NO aceptada");
                //if(cadenaValida)
                //    System.out.println("Cadena Aceptada");
                //else
                //    System.out.println("ERROR cadena NO aceptada");
            }else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
        
    }
}