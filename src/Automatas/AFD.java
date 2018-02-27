
package Automatas;

import java.util.ArrayList;
import java.util.HashSet;


public class AFD extends Automata{
    private Estado estadoInicial;
    private HashSet<ConjuntoEstados> conjuntosEdos;
    private HashSet<Character> alfabeto;
    
    public AFD(ArrayList<AFN> afns){
        //Union de todos los afns con el estado inicial de cada afn.
        estadoInicial = new Estado();
        alfabeto = new HashSet<>();
        for (AFN afn : afns){
            estadoInicial.crearTrancision(EPSILON, afn.getEstadoInicial());
            alfabeto.addAll(afn.getAlfabeto());
        }
        //Crear las cerraduras epsilon
        conjuntosEdos = new HashSet<>();
        conjuntosEdos.add(new ConjuntoEstados(CerraduraE(estadoInicial))) ;
        
        //Obtener todos los conjuntos Epsilon
        for (ConjuntoEstados ce : conjuntosEdos) {
            if(!ce.isAnalizado()){
                //Analizar el conjunto con el alfabeto
                HashSet<Estado> nuevosCE = CerraduraE(ce.getColeccionEstados());
            }
        }
        //CrearTabladeTransiciones
    }
    
}
