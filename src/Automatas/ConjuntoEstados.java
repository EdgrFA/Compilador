
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
    
    public boolean isAnalizado(){
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }
    
    public HashSet<Estado> getColeccionEstados(){
        return coleccion;
    }
    
    public boolean existeEstadoFinal(){
        for (Estado estado : coleccion)
            if(estado.isEsAceptacion())
                return true;
        return false;
    }
}
