package LL1;

import java.util.ArrayList;

public class PruebasConObjetos {
    private ArrayList<SimboloNoTerminal> simbolos;
    
    public PruebasConObjetos(){
        simbolos = new ArrayList<>();
        SimboloNoTerminal s1 = new SimboloNoTerminal("s1");
        SimboloNoTerminal s2 = new SimboloNoTerminal("s2");
        SimboloNoTerminal s3 = new SimboloNoTerminal("s3");
        simbolos.add(s1);
        simbolos.add(s2);
        simbolos.add(s3);
    }
    
    public void cambiarObjeto(SimboloNoTerminal simboloTerminal){
        simboloTerminal=simbolos.get(0);
        System.out.println(simboloTerminal.getExpresion());
    }
    
    public static void main(String[] args) {
        PruebasConObjetos pco =  new PruebasConObjetos();
        SimboloNoTerminal snt = new SimboloNoTerminal("Hola");
        pco.cambiarObjeto(snt);
        System.out.println(snt.getExpresion());
        System.out.println("***********************");
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        SimboloNoTerminal s1 = new SimboloNoTerminal("s1");
        SimboloNoTerminal s2 = new SimboloNoTerminal("s2");
        SimboloNoTerminal s3 = new SimboloNoTerminal("s3");
        simbolos.add(s1);
        simbolos.add(s2);
        simbolos.add(s3);
        
        
        System.out.println(s2 instanceof SimboloNoTerminal);
        Simbolo x = new Simbolo("x");
        x = (Simbolo) s2;
        System.out.println(x instanceof SimboloNoTerminal);
        x.setExpresion("K onda Cachorro");
        System.out.println(x);
        System.out.println(s2);
        System.out.println(s2 instanceof SimboloNoTerminal);
    }
}
