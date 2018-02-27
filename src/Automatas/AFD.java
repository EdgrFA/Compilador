
package Automatas;

import java.util.ArrayList;
import java.util.HashSet;


public class AFD extends Automata{
    
    
    public AFD(ArrayList<AFN> afns){
        //Union de todos los afns con el estado inicial de cada afn.
        Estado edoP = new Estado();
        for (AFN afn : afns)
            edoP.crearTrancision(EPSILON, afn.getEstadoInicial());
                
        //Crear las cerraduras epsilon
        HashSet<Estado> conjuntoE = CerraduraE(edoP);
        
        //CrearTabladeTransiciones
    }
    
}
