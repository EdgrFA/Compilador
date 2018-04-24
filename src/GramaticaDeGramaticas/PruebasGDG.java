/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GramaticaDeGramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;

/**
 *
 * @author xXEdG
 */
public class PruebasGDG {
    
    public static void main(String[] args) {
        GramaticaDeGramaticas gDG = new GramaticaDeGramaticas();
        String cadena = "G -> ListaReglas ;"
                + "ListaReglas -> Regla Pc ListaReglas' ;"
                + "ListaReglas' -> Regla Pc ListaReglas' |e  ;"
                + "Regla -> LadoIzquierdo FLECHA ListaLadosDerechos ;"
                + "LadoIzquierdo -> SIMBOLO ;"
                + "ListaLadosDerechos -> LadoDerecho ListaLadosDerechos' ;"
                + "ListaLadosDerechos' -> OR LadoDerecho ListaLadosDerechos' |e;"
                + "LadoDerecho -> SIMBOLO LadoDerecho';"
                + "LadoDerecho' -> SIMBOLO LadoDerecho' |e;"; //"E -> T E' ;E' -> + T E' |- T E' ;"; //"E->TE';E'->+TE'|-TE'|e;T->FT';T'->*FT'|/FT'|e;F->(E)|num;" ;
        boolean paso = gDG.analisisLexico(cadena);
        if(paso){
            System.out.println("\nEl Analisis Lexico fue correcto ");
            boolean pasoSintactico = gDG.analisisSintactico(cadena);
            if(pasoSintactico)
                System.out.println("Paso Sintactico");
            else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
        
    }
    
}
