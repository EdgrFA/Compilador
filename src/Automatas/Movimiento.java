package Automatas;

import java.util.HashSet;

public interface Movimiento {
    HashSet<Estado> Mover(Estado e, char c);
    HashSet<Estado> MoverC(HashSet<Estado> E, char c);
    
    public static void main(String[] args){
        HashSet<String> hs = new HashSet<>();
        hs.add("Hola");
        hs.add("k");
        hs.add("ace");
        hs.add("Hola");
        System.out.println(hs);
    }
}
