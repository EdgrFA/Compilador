package Pruebas;

import java.util.*;

public class Prueba {
    public void referencia(StringBuilder r){
        r.append(" a todos");
    }
    
    public static int change1(){
        System.out.println("change1");
        return 10;
    }
    
    public static int change2(){
        System.out.println("change2");
        return 20;
    }
    
    public static int change3(){
        System.out.println("change3");
        return 30;
    }
    
    public static void main(String[] args){
        Prueba p  = new Prueba();
        StringBuilder r = new StringBuilder();
        r.append("Hola");
        String re = "xD";
        int a1=10, a2=20, a3=30;
        if(a1==change1() || a2==change2() || re.equals("x") || a3==change3() ){
            System.out.println("Entre al if");
        }
        
        p.referencia(r);
        System.out.println(r);
    }
}
