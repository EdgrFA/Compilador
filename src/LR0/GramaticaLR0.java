package LR0;

import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.GramaticaDeGramaticas;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.List;

public class GramaticaLR0 extends GramaticaDeGramaticas{
    private AlgoritmoLR0 lr0;
    private Gramatica gramatica;
    
    public GramaticaLR0(Gramatica gramatica) {
        super(gramatica);
        lr0 = new AlgoritmoLR0(gramatica);
        this.gramatica = gramatica;
    }
    
    public void algoritmoLR0(){
        System.out.println("\n******* ESTADOS ********");
        lr0.obtenerS1(gramatica.getListaReglas().get(0));
        lr0.calcularEstados();
        System.out.println("******* Follow **********");
        lr0.calcularFollow();
        System.out.println("\n******* Reducciones *****");
        lr0.calcularReducciones();
        System.out.println("\n***** TABLA  ********");
        lr0.generarTabla();
        System.out.println();

    }
    
}
