package LL1;

import java.util.ArrayList;
import java.util.HashSet;
import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.SimboloNoTerminal;
import GramaticaDeGramaticas.SimboloInicial;
import GramaticaDeGramaticas.Regla;

public class Follow {
    private HashSet<SimboloNoTerminal> simbolos;
    private ArrayList<Regla> reglas;
    
    public Follow(SimboloNoTerminal simbolo, ArrayList<Regla> reglas){
        simbolos = new HashSet<>();
        this.reglas = reglas;
        
        simbolos = follow(simbolo);
    }
    
    public HashSet<SimboloNoTerminal> follow(SimboloNoTerminal simbolo){
        HashSet<SimboloNoTerminal> simbolosResultado = new HashSet<>();
        if(simbolo instanceof SimboloInicial)
            simbolosResultado.add(Gramatica.RAIZ);
        for(Regla regla : reglas){
            int indiceSimbExist = regla.comprobarSimbolo(simbolo);
            if(indiceSimbExist != -1){
                int tamanoMaxRegla = regla.getListaLadosDerechos().size();
                if(++indiceSimbExist == tamanoMaxRegla){
                    if(!regla.getLadoIzquierdo().equals(simbolo))
                        simbolosResultado.addAll(follow( regla.getLadoIzquierdo() ));
                }else{
                    simbolosResultado.addAll(First.first( regla.getSimbolo(indiceSimbExist) ));
                    if(AlgoritmoLL1.containsEpsilon(simbolosResultado)){
                        simbolosResultado.addAll(follow( regla.getLadoIzquierdo() ));
                    }
                }
            }
        }
        return simbolosResultado;
    }
    
    public HashSet<SimboloNoTerminal> getSimbolos(){
        return simbolos;
    }
}
