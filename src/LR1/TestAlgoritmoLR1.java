/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LR1;

import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.List;

/**
 *
 * @author xXEdG
 */
public class TestAlgoritmoLR1 {
        public static void main(String[] args) {
        Gramatica gramatica = new Gramatica();
        GramaticaLR1 gramaticaDeGramaticaLR0 = new GramaticaLR1(gramatica);
        String cadena = 
            /*"S' -> S ;" +
            "S -> E - E | f ;" +
            "S' -> S ;" +
            "S -> E = E | f ;" +
            "E -> T | E + T ;" +
            "T -> f | T * f ;";*/
        
            "E' -> E;" +
            "E -> E + T | T ;" +
            "T -> T * F | F ;" +
            "F -> ( E ) | num ;";
        boolean pasoLexico = gramaticaDeGramaticaLR0.analisisLexico(cadena);
        if(pasoLexico){
            System.out.println("\nEl Analisis Léxico de las Reglas de entrada fue correcto\n");
            boolean pasoSintactico = gramaticaDeGramaticaLR0.analisisSintactico(cadena, gramatica);
            if(pasoSintactico){
                System.out.println("Análisis Sintáctico Correcto Reglas de entrada");
                gramaticaDeGramaticaLR0.algoritmoLR1(gramatica);
                
                //Revisar expresion con la tabla LR
                
                String expresion = "num + num * ( num + num ) ";
                //String expresion = "( num + num ) * num - num $";
                //String expresion = "( SIMB OR ( SIMB & SIMB ) + ) ? OR SIMB $";
                boolean cadenaValida = gramaticaDeGramaticaLR0.analizarCadenaLR1(expresion);
                
                if(cadenaValida)
                   System.out.println("Cadena Aceptada");
                else
                   System.out.println("ERROR cadena NO aceptada");
            }else
                System.out.println("ERROR Sintactico Reglas de entrada");
        }else
            System.out.println("ERROR Léxico Reglas de entrada");
    }
}
