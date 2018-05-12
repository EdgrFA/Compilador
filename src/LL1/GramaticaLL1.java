package LL1;

import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.GramaticaDeGramaticas;

public class GramaticaLL1 extends GramaticaDeGramaticas{
    private AlgoritmoLL1 ll;
    
    public GramaticaLL1(Gramatica gramatica) {
        super(gramatica);
        ll = new AlgoritmoLL1(gramatica);
    }
        
    public boolean analizarCadenaLL1(String expresion){
        if(ll != null)
            return ll.validarCadena(expresion);
        else 
            return false;
    }
    
    public void algoritmoLL1(Gramatica gramatica){
        System.out.println("************* REGLAS ***************");
        gramatica.imprimirReglas();
        System.out.println("************* SIMBOLOS *************");
        gramatica.imprimirSimbolos();
        System.out.println("\n*********** FOLLOW ****************");
        ll = new AlgoritmoLL1(gramatica);
        ll.calcularFollow();
        System.out.println("\n*********** FIRST ****************");
        ll.calcularFirstSNT();
        System.out.println("\n***** FIRST Y FOLLOW POR REGLAS ********");
        ll.calcularFirstReglas();
        System.out.println("\n***** TABLA  ********");
        ll.generarTablaLL1();
        System.out.println();
    }
}
