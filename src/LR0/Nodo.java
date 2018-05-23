package LR0;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.SimboloNoTerminal;

public class Nodo {
    private int ubicacionPunto;
    private int numeroElementos;
    private SimboloNoTerminal simboloBeta;
    private Regla regla;
    
    public Nodo(Regla regla, int ubicacionPunto){
        this.regla = regla;
        this.ubicacionPunto = ubicacionPunto;
        simboloBeta = regla.getListaLadosDerechos().get(ubicacionPunto);
        numeroElementos = regla.getListaLadosDerechos().size();
    }
    
    public Nodo(Regla regla){
        this.regla = regla;
        numeroElementos = regla.getListaLadosDerechos().size();
        ubicacionPunto = numeroElementos;
        simboloBeta = Estado.FINAL;
    }
    
    public int getUbicacionPunto(){
        return ubicacionPunto;
    }
    
    public SimboloNoTerminal getSimboloBeta(){
        return simboloBeta;
    }
    
    public Regla getRegla(){
        return regla;
    }
    
    public int getNumeroElementos(){
        return numeroElementos;
    }
    
    @Override
    public boolean equals(Object obj){
        Nodo nodoAux = (Nodo) obj;
        if( this.regla.equals(nodoAux.getRegla())){
            if( this.ubicacionPunto == nodoAux.getUbicacionPunto()){
                if(simboloBeta.equals( nodoAux.getSimboloBeta()))
                    return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return regla.toString() + "," + ubicacionPunto +" :: "+simboloBeta ;
    }
}