package LR0;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.Simbolo;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.ArrayList;
import java.util.HashMap;

public class EstadoLR {
    private static int id = 0;
    private final int idEdo;
    private boolean analizado;
    private ArrayList<ReglaLR> reglasEdo;
    private HashMap<Simbolo, EstadoLR> derivacionesMap;
    private HashMap<Simbolo, EstadoLR> reduccionesMap;
    //Conjunto de reghlas con puntos
    
    public EstadoLR(ArrayList<ReglaLR> reglas) {
        this.idEdo = id++;
        this.analizado = false;
        this.reglasEdo = reglas;
        this.derivacionesMap = new HashMap<>();
        this.reduccionesMap = new HashMap<>();
    }
    
    public void crearDerivacion(Simbolo simbolo, EstadoLR estadoDestino){
        derivacionesMap.put(simbolo, estadoDestino);
    }
    
    public void crearReduccion(Simbolo simbolo, EstadoLR estadoDestino){
        reduccionesMap.put(simbolo, estadoDestino);
    }

    public boolean isAnalizado() {
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }
    
    /**
     * Compara una lista de reglas con la lista de reglas de el estado.
     * @param reglas : Reglas que se compararan.
     * @return true en caso de ser iguales, false son diferentes.
     */
    public boolean compararReglas(ArrayList<Regla> reglas){
        if(reglasEdo.size() != reglas.size())
            return false;   //Diferentes tamaños
        for (Regla regla : reglas) {
            
            boolean existe = false;
            for (Regla reglaEdo : reglasEdo) {
                //Comparar lado izquierdo
                if(!regla.getLadoIzquierdo().getExpresion().equals(
                        reglaEdo.getLadoIzquierdo().getExpresion()))
                    break;
                
                //Comparar numero de elementos lado derecho
                if(regla.getListaLadosDerechos().size() !=
                        reglaEdo.getListaLadosDerechos().size())
                    break;  
                
                //Comaparar Secuencia Simbolos lado derecho
                for (Simbolo simbolos : regla.getListaLadosDerechos())
                    for (Simbolo simbolosEdo : reglaEdo.getListaLadosDerechos())
                        if(!simbolos.getExpresion().equals(simbolosEdo.getExpresion()))
                            break;
                existe = true;
            }
            if(!existe)
                return false;
        }
        return true;
    }
}
