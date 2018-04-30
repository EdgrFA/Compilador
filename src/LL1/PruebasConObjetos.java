package LL1;

import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<SimboloNoTerminal,First> simbolosFirstPrev = new HashMap<>();
        pco.cambioDentroArray(newS2);
        pco.herencia(newS2);
        
        ArrayList<Simbolo> simbolos = new ArrayList<>();
        Simbolo s1 = new Simbolo("s1");
        Simbolo s2 = new Simbolo("s2");
        Simbolo s3 = new Simbolo("s3");
        simbolos.add(s1);
        simbolos.add(s2);
        simbolos.add(s3);
        simbolos.get(0);
        simbolos.contains(s2);
    }
}
