
package Automatas;

import java.util.ArrayList;
import java.util.HashSet;


public class AFD extends Automata{
    private ConjuntoEstados conjuntoInicial;
    private HashSet<ConjuntoEstados> conjuntosEdos;
    private HashSet<Character> alfabeto;
    //La tabla de transiciones solo se consultaria
    
    public AFD(ArrayList<AFN> afns){
        //Union de todos los afns con el estado inicial de cada afn.
        alfabeto = new HashSet<>();
        Estado estadoP = new Estado();
        for (AFN afn : afns){
            estadoP.crearTrancision(EPSILON, afn.getEstadoInicial());
            alfabeto.addAll(afn.getAlfabeto());
        }
        alfabeto.remove(EPSILON); //Remover Epsilon del Alfabeto
        
        //Crear la cerradura Epsilon Inicial
        conjuntosEdos = new HashSet<>();
        conjuntoInicial = new ConjuntoEstados(CerraduraE(estadoP));
        conjuntosEdos.add(conjuntoInicial) ;
        
        //Obtener todos los conjuntos 
        //***talvez falta reiniciar el ciclo cada vez que se encuentre algun conjunto sin analizar
        for (ConjuntoEstados ce : conjuntosEdos) {
            if(!ce.isAnalizado()){
                //Analizar el conjunto con el alfabeto
                for (Character caracter : alfabeto) {
                    HashSet<Estado> estadosD = IrA(ce.getColeccionEstados(), caracter);
                    //Si no se obtuvo ningun conjunto seguir con la siguiente letra
                    if(estadosD.isEmpty()){
                        ce.crearTrancision(caracter, null);
                        continue;
                    }
                    //Comprobar que el conjunto no existe
                    Boolean esNuevo = true;
                    for (ConjuntoEstados ces : conjuntosEdos){
                        if(!ces.compararConjuntos(estadosD)){
                            ce.crearTrancision(caracter, ces);
                            esNuevo = false;
                            break;
                        }
                    }
                    //Agregar Transiciones para generar al final la tabla
                    if(esNuevo){
                        ConjuntoEstados nuevoCE = new ConjuntoEstados(estadosD);
                        ce.crearTrancision(caracter, nuevoCE);
                        conjuntosEdos.add(nuevoCE);
                    }
                }
                ce.setAnalizado(true);
            }
        }
        //CrearTabladeTransiciones
    }
    
}
