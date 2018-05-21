package LR1;

import GramaticaDeGramaticas.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FirstLR {
    private HashMap<SimboloNoTerminal,HashSet<SimboloNoTerminal>> simbolosFirst;
    private Gramatica gramatica;

    public FirstLR(Gramatica gramatica) {
        simbolosFirst = new HashMap<>();
        this.gramatica = gramatica;
        for (SimboloNoTerminal simbolo : gramatica.getSimbolos())
            simbolosFirst.put(simbolo, first(simbolo));
    }
    
    private HashSet<SimboloNoTerminal> first(SimboloNoTerminal simbolo){
        HashSet<SimboloNoTerminal> simbolos = new HashSet<>();
        if(simbolo.isTerminal())
            simbolos.add(simbolo);
        else {
            for (Regla regla : gramatica.getListaReglas()) {
                if(regla.getLadoIzquierdo().getExpresion().equals(simbolo.getExpresion())){
                    ArrayList<String> simbsPasados = new ArrayList<>();
                    SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
                    simbolos.addAll(firstSR(primerSimbolo, simbsPasados));
                }
            }
        }
        return simbolos;
    }
    
    private HashSet<SimboloNoTerminal> firstSR(SimboloNoTerminal simbolo, ArrayList<String> simbsPasados){
        HashSet<SimboloNoTerminal> simbolos = new HashSet<>();
        if(simbolo.isTerminal())
            simbolos.add(simbolo);
        else {
            ArrayList<Regla> reglas = new ArrayList<>();
            for (Regla regla : gramatica.getListaReglas()) {
                String ladoIzquierdo = regla.getLadoIzquierdo().getExpresion();
                if(!simbsPasados.contains(ladoIzquierdo) && ladoIzquierdo.equals(simbolo.getExpresion()))
                    reglas.add(regla);
            }
            if(!reglas.isEmpty())
                simbsPasados.add(reglas.get(0).getLadoIzquierdo().getExpresion());
            for (Regla regla : reglas) {
                SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
                simbolos.addAll(firstSR(primerSimbolo, simbsPasados));
            }
        }
        return simbolos;
    }
    
    public HashSet<SimboloNoTerminal> getFirst(SimboloNoTerminal snt){
        return simbolosFirst.get(snt);
    }
    
    public String toString(){
        String cadenaAux = "*********** FIRST ****************\n";
        for (SimboloNoTerminal simbolo : gramatica.getSimbolos()) {
            cadenaAux += "first(" + simbolo + ") = {";
            HashSet<SimboloNoTerminal> simbolos = simbolosFirst.get(simbolo);
            int i = 1;
            for (SimboloNoTerminal snt : simbolos) {
                cadenaAux += snt.getExpresion();
                if(i < simbolos.size())
                    cadenaAux += ",";
                i++;
            }
            cadenaAux += "}\n";
        }
        return cadenaAux;
    }
}
