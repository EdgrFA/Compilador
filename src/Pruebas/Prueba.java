package Pruebas;

import java.util.*;

public class Prueba {
    public void referencia(StringBuilder r){
        r.append(" a todos");
    }
    
    public static void main(String[] args){
        Prueba p  = new Prueba();
        StringBuilder r = new StringBuilder();
        r.append("Hola");
        
        p.referencia(r);
        System.out.println(r);
    }
}
