package LR1;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class ItemLR {
    private ReglaLR regla;
    private HashSet<SimboloNoTerminal> simbolosT;

    public ItemLR(Regla regla, HashSet<SimboloNoTerminal> simbolosT) {
        this.regla = new ReglaLR(regla);
        this.simbolosT = simbolosT;
    }
    
    public boolean recorrerPuntoRegla(){
        return regla.recorrerPunto();
    }
    
    public SimboloNoTerminal getSimboloPunto(){
        return regla.getSimboloPunto();
    }
    
    public SimboloNoTerminal getSiguienteSimboloPunto(){
        return regla.getSiguienteSimboloPunto();
    }

    public ReglaLR getRegla() {
        return regla;
    }

    public HashSet<SimboloNoTerminal> getSimbolosT() {
        return simbolosT;
    }

    public void setSimbolosT(HashSet<SimboloNoTerminal> simbolosT) {
        this.simbolosT = simbolosT;
    }
    
    public void agregarSimbolos(HashSet<SimboloNoTerminal> snt){
        simbolosT.addAll(snt); //Si surgen problemas cambiar a array list y ocupar equals
    }
    
    public void unirItems(ItemLR itemLR){
        this.simbolosT.addAll(itemLR.getSimbolosT());
    }
    
    @Override
    public boolean equals(Object o){
        ItemLR itemLR = (ItemLR) o;
        
        if(!getRegla().equals(itemLR.getRegla()))
            return false;
        
        for (SimboloNoTerminal simbolo1 : simbolosT) {
            boolean existe = false;
            for (SimboloNoTerminal simbolo2 : itemLR.getSimbolosT()) {
                if(simbolo1.equals(simbolo2)){
                    existe = true;
                    break;
                }
            }
            if(!existe)
                return false;
        }
        
        return true;
    }
    
    @Override
    public String toString(){
        String cadenaAux = "";
        cadenaAux = "[ " + regla + ", {";
        int i = 1;
        for (SimboloNoTerminal snt : simbolosT) {
            cadenaAux += snt.getExpresion();
            if(i < simbolosT.size())
                cadenaAux += ",";
            i++;
        }
        cadenaAux += "} ]";
        return cadenaAux;
    }
}