
package Automatas;

import java.util.ArrayList;
import java.util.HashSet;

public class ConjuntoEstados {
    private static int id = 0;
    private int idCE;
    private HashSet<Estado> coleccion;
    private boolean analizado;
    private ArrayList<TransicionCE> transiciones;
    //TOKEN SI EXISTE
    private int token; //****Falta hacer el cambio de este dato en el metodo esEstadoFinal

    public ConjuntoEstados(HashSet<Estado> coleccion, int token) {
        this.idCE = id++;
        this.coleccion = coleccion;
        this.analizado = false;
        this.transiciones = new ArrayList<>();
        this.token = token;
    }
    
    public void crearTrancision(char c, ConjuntoEstados conjuntoDestino){
        TransicionCE t = new TransicionCE(c, conjuntoDestino);
        transiciones.add(t);
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
    
    public int getToken(){ //***Modificable a entero utilizando tokens y a agregar un dato esFinal
        for (Estado estado : coleccion)
            if(estado.isEsAceptacion())
                return token;
        return token;
    }
    
    /**
     * Este método comparara los dos conjuntos de estados para verificar si son los mismos.
     * @param conjunto: Conjunto con el que se hara la comparacion.
     * @return: true - Si son iguales, false - Si son distintos.
     */
    public boolean compararConjuntos(HashSet<Estado> conjunto){
        if(conjunto.size() != coleccion.size())
            return false;
        for (Estado estado : coleccion) {
            if(!conjunto.contains(estado))
                return false;
        }
        return true;
    }
    
    public ArrayList<TransicionCE> getTransiciones(){
        return transiciones;
    }

    public int getId() {
        return idCE;
    }
    
    public void setToken(int valorToken){
        token= valorToken;
    }
}
