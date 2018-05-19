package LR0;

import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.GramaticaDeGramaticas;

public class GramaticaLR0 extends GramaticaDeGramaticas{
    private AlgoritmoLR0 lr;
    
    public GramaticaLR0(Gramatica gramatica) {
        super(gramatica);
    }
    
    public void algoritmoLR0(Gramatica gramatica){
        System.out.println("************* REGLAS ***************");
        gramatica.imprimirReglas();
        System.out.println("************* SIMBOLOS *************");
        gramatica.imprimirSimbolos();
        System.out.println("\n*********** FOLLOW ****************");
        lr = new AlgoritmoLR0(gramatica);
        
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
