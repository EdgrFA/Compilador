package LL1;

import java.util.ArrayList;

public class Follow {
    private ArrayList<SimboloNoTerminal> simbolos;
    private ArrayList<Regla> reglas;
    
    public Follow(SimboloNoTerminal simbolo, ArrayList<Regla> reglas){
        simbolos = new ArrayList<>();
        this.reglas = reglas;
        
        simbolos = follow(simbolo);
    }
    
    public ArrayList<SimboloNoTerminal> follow(SimboloNoTerminal simbolo){
        ArrayList<SimboloNoTerminal> simbolosResultado = new ArrayList<>();
        for(Regla regla : reglas){
            if(regla.getListaLadosDerechos().contains(simbolo)){
                System.out.println(regla);
                System.out.println("Contengo a "+simbolo);
            }
        }
        
        return simbolosResultado;
    }
}
