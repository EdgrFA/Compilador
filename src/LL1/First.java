package LL1;

import java.util.ArrayList;

public class First {
    private ArrayList<SimboloNoTerminal> simbolos;
    private SimboloNoTerminal simboloInicialDer;
    
    public First(SimboloNoTerminal simbolo){
        simbolos = new ArrayList<>();
        simbolos = First.firstSNT(simbolo);
    }
    
    public First(Regla regla){
        simbolos = new ArrayList<>();
        simboloInicialDer = regla.getListaLadosDerechos().get(0);
        simbolos = First.first(simboloInicialDer);
        regla.setFirstSimbolos(this);
        if(!simboloInicialDer.isTerminal())
            crearRelacionesSimbNoTer(regla);
    }
    
    private void crearRelacionesSimbNoTer(Regla regla){
        for(SimboloNoTerminal nuevaRelacion : simbolos ){
            regla.getLadoIzquierdo().agregarRelacion(nuevaRelacion, regla );
        }
    }
    
    public static ArrayList<SimboloNoTerminal> first( SimboloNoTerminal simbolo ){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        if(simbolo.isTerminal()){
            simbolosResultado.add(simbolo);
            return simbolosResultado;
        }
        for (Regla regla : simbolo.getReglasLadosDerechos()) {
            SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
            simbolosResultado.addAll(first(primerSimbolo));
        }
        return simbolosResultado;
    }
    
    //Utilo sólo para first de Simbolos NO TERMINALES y no Reglas
    public static ArrayList<SimboloNoTerminal> firstSNT( SimboloNoTerminal simbolo ){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        for (Regla regla : simbolo.getReglasLadosDerechos()) {
            SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
            if(primerSimbolo.isTerminal()){
                simbolosResultado.add(primerSimbolo);
                continue;
            }
            simbolosResultado.addAll(first(primerSimbolo));
        }
        return simbolosResultado;
    }
    
    public ArrayList<SimboloNoTerminal> getSimbolos(){
        return simbolos;
    }
    
    @Override
    public String toString(){
        return simbolos.toString();
    }
}
