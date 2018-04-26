package LL1;

import java.util.ArrayList;

public class Simbolo {
    public static final char EPSILON = 'e';
    public static final Simbolo SIMBOLO_INICIAL = new Simbolo("$", null);
    
    private boolean terminal;
    private String expresion;
    private Simbolo simboloPadre;
    private ArrayList<Simbolo> reglas;
    private ArrayList<Simbolo> simbolosDerch;
    
    public Simbolo(String expresion, Simbolo simboloPadre){
        this.expresion = expresion;
        this.simboloPadre = simboloPadre;
    }
    
}
