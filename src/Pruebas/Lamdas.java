/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import GramaticaDeGramaticas.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class Lamdas {
    public static final Simbolo inicial = new Simbolo("Gola");
    public static ArrayList<Simbolo> arreglo(){
        ArrayList<Simbolo> r = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            r.add(new Simbolo("Hola k pedo cachorros"));
        }
        return r;
    }
    
    public static void forConvensional( ArrayList<Simbolo> arg){
        for (Simbolo simbolo : arg) {
            if(simbolo.equals(inicial))
                System.out.println("asdsadads");
        }
    }
    
    public static void forLamda( ArrayList<Simbolo> arg){
        arg.stream().forEach(
            (simbolo) -> {
            //System.out.println(simbolo);
        });
    }
    
    public static void main(String[] args) {
        ArrayList<Simbolo> r = Lamdas.arreglo();
        
        final long start = System.nanoTime();
        Lamdas.forLamda(r);
        final long end = System.nanoTime();
        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
    }
}
