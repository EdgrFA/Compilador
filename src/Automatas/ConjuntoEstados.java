
package Automatas;

import java.util.HashSet;

public class ConjuntoEstados {
    private static int id = 0;
    private HashSet<Estado> coleccion;
    private boolean analizado;

    public ConjuntoEstados(HashSet<Estado> coleccion) {
        this.id = id++;
        this.coleccion = coleccion;
        this.analizado = false;
    }
    
    
    
}
