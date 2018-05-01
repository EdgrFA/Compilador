package LL1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class AlgoritmoLL1 {
    HashSet<SimboloNoTerminal> simbolos;
    HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    Gramatica gramatica;
    
    public AlgoritmoLL1(Gramatica gramatica){
        this.gramatica = gramatica;
        simboloFollowPrevio = new HashMap<>();
    }
        
    public void calcularFirstReglas( ){
        for (int i = 0; i < Gramatica.contadorReglas ; i++) {
            Regla regla = gramatica.getListaReglas().get(i);
            SimboloNoTerminal simboloInicial = regla.getListaLadosDerechos().get(0);
            First first = new  First( regla );
            if(AlgoritmoLL1.containsEpsilon(first.getSimbolos())){
                Follow followAux = simboloFollowPrevio.get( regla.getLadoIzquierdo() );
                if( followAux != null){
                    ArrayList<SimboloNoTerminal> simbolosFollow = new ArrayList<SimboloNoTerminal>(followAux.getSimbolos());
                    first.getSimbolos().addAll(simbolosFollow);
                }
            }
            System.out.print("\tRegla "+i+" : ");
            System.out.print( regla +"\t");
            System.out.print("first( "+ simboloInicial +" ) = ");
            System.out.println( first.getSimbolos() );
        }
    }
    
    public ArrayList<SimboloNoTerminal> calcularFirstSNT(){
        ArrayList<SimboloNoTerminal> simbolosR = null;
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                First first = new  First( gramatica.getSimbolo(i) );
                simbolosR = First.first( gramatica.getSimbolo(i) );
                System.out.print("first( "+ gramatica.getSimbolo(i) +" ) = ");
                System.out.println( simbolosR);
            }
        }
        return simbolosR;
    }
    
    public void calcularFollow(){
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                Follow follow = new  Follow( gramatica.getSimbolo(i), gramatica.getListaReglas() );
                simboloFollowPrevio.put(gramatica.getSimbolo(i), follow);
                System.out.println("follow ("+gramatica.getSimbolo(i)+") = "+follow.getSimbolos());
            }
        }
    }
    
    public static boolean containsEpsilon(HashSet<SimboloNoTerminal> conjuntoSimbolos){
        for(Simbolo simbolo : conjuntoSimbolos){
            if(Gramatica.EPSILON.equals(simbolo)){
                conjuntoSimbolos.remove(simbolo);
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsEpsilon(ArrayList<SimboloNoTerminal> conjuntoSimbolos){
        for(Simbolo simbolo : conjuntoSimbolos){
            if(Gramatica.EPSILON.equals(simbolo)){
                conjuntoSimbolos.remove(simbolo);
                return true;
            }
        }
        return false;
    }
    
}