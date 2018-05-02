package LL1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class PruebasConObjetos {
    private ArrayList<Simbolo> simbolos;
    
    public PruebasConObjetos(){
        simbolos = new ArrayList<>();
        Simbolo s1 = new Simbolo("s1");
        Simbolo s2 = new Simbolo("s2");
        Simbolo s3 = new Simbolo("s3");
        simbolos.add(s1);
        simbolos.add(s2);
        simbolos.add(s3);
    }
    
    public void cambiarObjeto(SimboloNoTerminal simboloTerminal){
        simboloTerminal=(SimboloNoTerminal) simbolos.get(0);
        System.out.println(simboloTerminal.getExpresion());
    }
    
    public void cambioDentroArray(Simbolo simbol){
        for(int i = 0 ; i < simbolos.size() ; i++){
            if(simbol.equals(simbolos.get(i))){
                Simbolo simboloAux = simbolos.get(i);
                SimboloNoTerminal simboloAux2 = new SimboloNoTerminal(simboloAux.getExpresion());
                simboloAux = simboloAux2;
            }
        }
    }
    
    public void herencia(Simbolo simbolo){
        System.out.println("*******************");
        Simbolo y = new Simbolo("y");
        SimboloNoTerminal r = new SimboloNoTerminal("r");
        System.out.println("y intanceof Simbolo = "+ (y instanceof Simbolo));
        System.out.println("y intanceof SimboloNoTerminal = "+ (y instanceof SimboloNoTerminal));
        System.out.println("y = "+y.getExpresion());
        System.out.println("********************************");
        simbolos.add(y);
        simbolos.add(r);
        y = r;
        SimboloNoTerminal r2 = new SimboloNoTerminal("r2");
        Regla regla = new Regla(r2, 0);
        r.agregarReglaLadoDerecho(regla);
        System.out.println("y intanceof Simbolo = "+ (y instanceof Simbolo));
        System.out.println("y intanceof SimboloNoTerminal = "+ (y instanceof SimboloNoTerminal));
        System.out.println("y = "+y.getExpresion());
        System.out.println("*******************");
        System.out.println("Auxiliar");
        SimboloNoTerminal nuevoNoTerminal = (SimboloNoTerminal) y;
        System.out.println(nuevoNoTerminal.getReglasLadosDerechos());
        System.out.println("**********************");
        System.out.println(y);
        System.out.println(r);
        System.out.println("**********************");
        for (Simbolo simbolo1 : simbolos) {
            System.out.println("sim = "+ simbolo1);
        }
        
    }
    
    public static void main(String[] args) {
        PruebasConObjetos pco =  new PruebasConObjetos();
        SimboloNoTerminal newS2 = new SimboloNoTerminal("s2");
        LinkedList cola = new LinkedList();
        Stack pila = new Stack();
        
        Simbolo s1 = new Simbolo("s1");
        Simbolo s2 = new Simbolo("s2");
        Simbolo s3 = new Simbolo("s3");
        cola.add(s1);
        cola.add(s2);
        cola.add(s3);
        System.out.println(cola);
        cola.remove();
        System.out.println(cola);
        Cola c = new Cola();
        c.add(s1);
        c.add(s2);
        c.add(s3);
        pila.add(s1);
        pila.add(s2);
        pila.add(s3);
        System.out.println("c= " + c);
        System.out.println("pila = "+ pila);
        pila.pop();
        pila.push("HOla");
        pila.addAll(c);
        ArrayList<SimboloNoTerminal> listisima = new ArrayList<>(c);
        
        System.out.println("¨*****************++");
        System.out.println(listisima);
        Pila pila2 = Pila.invertirArraySNT(listisima);
        System.out.println( pila2 );
    }
}
