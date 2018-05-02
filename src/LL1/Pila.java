package LL1;

import java.util.Stack;
import java.util.ArrayList;

public class Pila extends Stack{
    
    public static Pila invertirArraySNT(ArrayList<SimboloNoTerminal> simbolos){
        Pila pilaInvertida = new Pila();
        for (int i = simbolos.size()-1; i != -1 ; i--) {
            pilaInvertida.add(simbolos.get(i));
        }
        return pilaInvertida;
    }
    
    @Override
    public String toString(){
        String resultado = super.toString();
        return resultado.replace(",","").replace("[","").replace("]", "");
    }
}
