package LL1;

import java.util.ArrayList;

public class SimboloNoTerminal extends Simbolo{
    private ArrayList<Regla> reglasLadosDerecho;
    
    public SimboloNoTerminal(String expresion){
        super(expresion);
        reglasLadosDerecho = new ArrayList<>();
    }
    
    public void agregarReglaLadoDerecho(Regla regla){
        reglasLadosDerecho.add(regla);
    }
    
    public ArrayList<Regla> getReglasLadosDerechos() {
        return reglasLadosDerecho;
    }
}
