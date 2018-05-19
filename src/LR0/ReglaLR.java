package LR0;

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
    
    public Simbolo getSimboloPunto(){
        return getListaLadosDerechos().get(indicePunto);
    }
}
