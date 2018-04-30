package LL1;

import java.util.ArrayList;
import java.util.HashSet;

public class AnalizadorLL1 {
    HashSet<SimboloNoTerminal> simbolos;
    Gramatica gramatica;
    
    public AnalizadorLL1(Gramatica gramatica){
        this.gramatica = gramatica;
    }
    
    public ArrayList<SimboloNoTerminal> first(ArrayList<SimboloNoTerminal> expresion){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        SimboloNoTerminal primero = expresion.get(0);
        if(primero.isTerminal()){
            simbolosResultado.add(primero);
            return simbolosResultado;
        }
        for (Regla regla : primero.getReglasLadosDerechos()) {
            simbolosResultado.addAll(first(regla.getListaLadosDerechos()));
        }
        return simbolosResultado;
    }
    
    public ArrayList<SimboloNoTerminal> first( SimboloNoTerminal simbolo ){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        for (Regla regla : simbolo.getReglasLadosDerechos()) {
            simbolosResultado.addAll(first(regla.getListaLadosDerechos()));
        }
        return simbolosResultado;
    }
    
    public ArrayList<SimboloNoTerminal> follow(ArrayList<SimboloNoTerminal> expresion){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        SimboloNoTerminal primero = expresion.get(0);
        if(primero.isTerminal()){
            simbolosResultado.add(primero);
            return simbolosResultado;
        }
        for (Regla regla : primero.getReglasLadosDerechos()) {
            simbolosResultado.addAll(first(regla.getListaLadosDerechos()));
        }
        return simbolosResultado;
    }
}
