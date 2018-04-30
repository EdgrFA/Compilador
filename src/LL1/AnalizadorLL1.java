package LL1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class AnalizadorLL1 {
    HashSet<SimboloNoTerminal> simbolos;
    HashMap<SimboloNoTerminal, First> simboloFirstsPrevio;
    HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    Gramatica gramatica;
    
    public AnalizadorLL1(Gramatica gramatica){
        this.gramatica = gramatica;
        simboloFirstsPrevio = new HashMap<>();
        simboloFollowPrevio = new HashMap<>();
    }
        
    public void calcularFirstReglas( ){
        for (int i = 0; i < Gramatica.contadorReglas ; i++) {
            Regla regla = gramatica.getListaReglas().get(i);
            SimboloNoTerminal simboloInicial = regla.getListaLadosDerechos().get(0);
            First first = new  First( regla );
            
            System.out.println("\tRegla "+i+" ");
            System.out.println( regla );
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
                simboloFirstsPrevio.put(null, first);
                System.out.print("first( "+ gramatica.getSimbolo(i) +" ) = ");
                System.out.println( simbolosR);
            }
        }
        return simbolosR;
    }
    
    public void calcularFollow(){
        ArrayList<SimboloNoTerminal> simbolosR = null;
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                Follow follow = new  Follow( gramatica.getSimbolo(i), gramatica.getListaReglas() );
                simboloFollowPrevio.put(gramatica.getSimbolo(i), follow);
            }
        }
    }
    
    public void imprimirFirst(){
        System.out.println("******************************");
        System.out.println(simboloFirstsPrevio);
        for (int i = 0; i < simboloFirstsPrevio.size() ; i++) {
            
            /*
            System.out.println("\tRegla "+i+" ");
            System.out.println( regla );
            System.out.print("first( "+ simboloInicial +" ) = ");
            System.out.println( first.getSimbolos() );
            */
        }
    }
}