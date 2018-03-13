package Automatas;

import java.util.ArrayList;
import java.util.HashSet;

public class AFD extends Automata{
    private ConjuntoEstados conjuntoInicial;
    private ArrayList<ConjuntoEstados> conjuntosEdos;
    private HashSet<Character> alfabeto;
  //La tabla de transiciones solo se consultaria
    
    public AFD(AFNs afns){
        //Union de todos los afns con el estado inicial de cada afn.
        alfabeto = new HashSet<>();
        Estado estadoP = new Estado();
        for (AFN afn : afns.getAutomatas()){
            estadoP.crearTrancision(EPSILON, afn.getEstadoInicial());
            alfabeto.addAll(afn.getAlfabeto());
        }
        alfabeto.remove(EPSILON); //Remover Epsilon del Alfabeto
        
        //Crear la cerradura Epsilon Inicial
        conjuntosEdos = new ArrayList<>();
        conjuntoInicial = new ConjuntoEstados(CerraduraE(estadoP),-1);
        conjuntosEdos.add(conjuntoInicial) ;
        
        //Obtener todos los conjuntos 
        //***talvez falta reiniciar el ciclo cada vez que se encuentre algun 
        //conjunto sin analizar
        for (int i = 0; i < conjuntosEdos.size(); i++) {
            ConjuntoEstados ce = conjuntosEdos.get(i);
            if(!ce.isAnalizado()){
                //Analizar el conjunto con el alfabeto
                for (Character caracter : alfabeto) {
                    HashSet<Estado> estadosD = IrA(ce.getColeccionEstados(),caracter);
                    //Si no se obtuvo ningun conjunto seguir con la siguiente letra
                    if(estadosD.isEmpty()){
                        ce.crearTrancision(caracter, null);
                        continue;
                    }
                    //Comprobar que el conjunto no existe
                    Boolean esNuevo = true;
                    for (ConjuntoEstados ces : conjuntosEdos){
                        if(ces.compararConjuntos(estadosD)){
                            ce.crearTrancision(caracter, ces);
                            esNuevo = false;
                            break;
                        }
                    }
                    //Agregar Transiciones para generar al final la tabla
                    if(esNuevo){
                        int token = afns.containsEdoAcept(estadosD);
                        ConjuntoEstados nuevoCE = new ConjuntoEstados(estadosD,token);
                        ce.crearTrancision(caracter, nuevoCE);
                        conjuntosEdos.add(nuevoCE);
                    }
                }
                ce.setAnalizado(true);
            }
        }
    }
    
    public void imprimirTablaTransiciones(){
        //Cabezera
        System.out.print("CE\t");
        for (Character caracter : alfabeto)
            System.out.print(caracter + "\t");
        System.out.println("Edos Acept");
        for (ConjuntoEstados ce : conjuntosEdos) {
            System.out.print("S" + ce.getId() + "\t");
            for (TransicionCE t : ce.getTransiciones()) {
                if(t.getConjuntoD() == null)
                    System.out.print("-1\t");
                else
                    System.out.print("S" + t.getConjuntoD().getId() + "\t");
            }
            System.out.println(ce.getToken());
        }
    }
    
    public HashSet<Character> getAlfabeto(){
        return alfabeto;
    }
    
    public ConjuntoEstados getConjuntoInicial() {
        return conjuntoInicial;
    }
}