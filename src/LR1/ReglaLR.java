package LR1;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.Simbolo;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.ArrayList;

public class ReglaLR extends Regla{
    private int indicePunto;

    public ReglaLR(Regla regla) {
        super(regla.getNumeroRegla(), regla.getLadoIzquierdo(), regla.getListaLadosDerechos());
        this.indicePunto = 0;
    }
    
    public ReglaLR(ReglaLR reglaLR) {
        super(reglaLR.getNumeroRegla(), reglaLR.getLadoIzquierdo(), reglaLR.getListaLadosDerechos());
        this.indicePunto = reglaLR.getIndicePunto();
    }
    
    public boolean recorrerPunto(){
        if(indicePunto < getListaLadosDerechos().size()){
            indicePunto++;
            return true;
        } 
        return false;
    }

    public int getIndicePunto() {
        return indicePunto;
    }
    
    public SimboloNoTerminal getSimboloPunto(){
        if(indicePunto < getListaLadosDerechos().size())
            return getListaLadosDerechos().get(indicePunto);
        return null;
    }
    public SimboloNoTerminal getSiguienteSimboloPunto(){
        if(indicePunto + 1 < getListaLadosDerechos().size())
            return getListaLadosDerechos().get(indicePunto + 1);
        return null;
    }
    
    @Override
    public boolean equals(Object o){
        ReglaLR reglaLR = (ReglaLR) o;
        //Comparar lado izquierdo
        if(!getLadoIzquierdo().getExpresion().equals(
                reglaLR.getLadoIzquierdo().getExpresion()))
            return false;
        //Comparar indice punto
        if(indicePunto != reglaLR.getIndicePunto())
            return false;
        //Comparar numero de elementos lado derecho
        if(getListaLadosDerechos().size() != 
                reglaLR.getListaLadosDerechos().size())
            return false;
        //Comaparar Secuencia Simbolos lado derecho
        for (Simbolo simbolo : getListaLadosDerechos()){
            boolean existio = false;
            for (Simbolo simboloRLR : reglaLR.getListaLadosDerechos()){
                if(simbolo.getExpresion().equals(simboloRLR.getExpresion())){
                    existio = true;
                    break;
                }
            }
            if(!existio)
                return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String cadenaAux = "";
        cadenaAux = getLadoIzquierdo().getExpresion() + " -> ";
        int i;
        for(i = 0; i < getListaLadosDerechos().size(); i++){
            if(i == indicePunto)
                cadenaAux += ((char)164) + " "; //Simbolo Punto
            cadenaAux += getListaLadosDerechos().get(i).getExpresion() + " ";
        }
        if(i == indicePunto)
            cadenaAux += ((char)164) + " ";
        return cadenaAux;
    }
}
