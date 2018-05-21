/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LR1;

import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.GramaticaDeGramaticas;

/**
 *
 * @author xXEdG
 */
public class GramaticaLR1 extends GramaticaDeGramaticas{
    private LR1 lr;
    
    public GramaticaLR1(Gramatica gramatica) {
        super(gramatica);
    }
    
    public void algoritmoLR1(Gramatica gramatica){
        System.out.println("************* REGLAS ***************");
        gramatica.imprimirReglas();
        System.out.println("************* SIMBOLOS *************");
        gramatica.imprimirSimbolos();
        lr = new LR1(gramatica);
        System.out.println(lr);
        //System.out.println("\n***** TABLA  ********");
        //lr.generarTablaLR0();
        //System.out.println();
//        System.out.println("\n*********** FIRST ****************");
//        ll.calcularFirstSNT();
//        System.out.println("\n***** FIRST Y FOLLOW POR REGLAS ********");
//        ll.calcularFirstReglas();
//        System.out.println("\n***** TABLA  ********");
//        ll.generarTablaLL1();
//        System.out.println();
    }
    
}