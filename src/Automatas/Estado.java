package Automatas;

import java.util.ArrayList;
import java.util.HashSet;

public class Estado {
    //LLeva el contador de los ID's
    private static int idEstado = 0;
    
    public int idEdo;
    private ArrayList<Transicion> transiciones;
    private boolean aceptacion;

    public Estado() {
        this.idEdo = idEstado++;
        this.transiciones = new ArrayList<>();
        this.aceptacion = false;
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

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setEsAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
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
        str += "Final: " + isAceptacion();
        return str;
    }
}
