package Automatas;

import java.util.ArrayList;
import java.util.HashSet;

public class Estado {
    //LLeva el contador de los ID's
    private static int idEstado = 0;
    
    //Variables propias de cada clase
    public int idEdo;
    private ArrayList<Transicion> transiciones;
    private boolean esAceptacion;

    public Estado() {
        this.idEdo = idEstado++;
        this.transiciones = new ArrayList<>();
        this.esAceptacion = false;
    }
    
    public void crearTrancision(char c, Estado estadoDestino){
        Transicion t = new Transicion(c, estadoDestino);
        transiciones.add(t);
    }
    
    public void crearTrancision(char carMin, char carMax, Estado estadoDestino){
        Transicion t = new Transicion(carMin, carMax, estadoDestino);
        transiciones.add(t);
    }
    
    public HashSet<Estado> Union(HashSet<Estado> edos){
        HashSet<Estado> R =  new HashSet<>();
        return R;
    }

    public boolean isEsAceptacion() {
        return esAceptacion;
    }

    public void setEsAceptacion(boolean esAceptacion) {
        this.esAceptacion = esAceptacion;
    }

    public ArrayList<Transicion> getTrancisiones() {
        return transiciones;
    }

    @Override
    public String toString() {
        String str = "\nEstado: " + idEdo + ", con las transiciones: \n";
        for (Transicion t : transiciones) {
            str += "C: "+t.getCarMax()+"\n ";
        }
        str += "Final: " + isEsAceptacion();
        return str;
    }
    
    
}
