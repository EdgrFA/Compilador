
package LR0;

import GramaticaDeGramaticas.SimboloNoTerminal;
import GramaticaDeGramaticas.Regla;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestPruebas {
    public static void main(String[] args) {
        SimboloNoTerminal sN = new SimboloNoTerminal("K");
        SimboloNoTerminal sN1 = new SimboloNoTerminal("F");
        SimboloNoTerminal sN2 = new SimboloNoTerminal("S");
        SimboloNoTerminal sN3 = new SimboloNoTerminal("A");
        SimboloNoTerminal sN4 = new SimboloNoTerminal("B");
        
        Regla regla1 = new Regla(sN,0);
        regla1.agregarSimbolo(sN1);
        regla1.agregarSimbolo(sN2);
        
        Regla regla2 = new Regla(sN,0);
        regla2.agregarSimbolo(sN1);
        regla2.agregarSimbolo(sN3);
        regla2.agregarSimbolo(sN4);
                
        Nodo nodo1 = new Nodo(regla1, 0);
        Nodo nodo2 = new Nodo(regla2, 0);
        
        List<Nodo> lista = new ArrayList<>();
        List<Nodo> lista2 = new ArrayList<>();
        
        lista.add(nodo1);
        lista.add(nodo2);
        
        lista2.add(nodo2);
        lista2.add(nodo1);
        
        Estado edo1 = new Estado(0, lista);
        Estado edo2 = new Estado(1, lista);
        
        System.out.println("edo1 = "+edo1);
        Estado.irA(edo1, sN1);
        
        Stream.of(lista);
        
        //System.out.println(nodo1);
        //System.out.println(nodo2);
        
        //System.out.println(nodo1.equals(nodo2));    
        //System.out.println("---"+lista.equals(lista2));    
        
        //Consumer<String> c = (x) -> System.out.println(x.toUpperCase());
        //c.accept("Java2s.com");
        System.out.println("********************************************");
        Stream<String> language = Stream.of("java", "python", "node");

        //Convert a Stream to List
        List<String> result = language.collect(Collectors.toList());
        ArrayList<String> result2 = new ArrayList<String>(result);
        result.forEach(System.out::println);
        System.out.println("Soy arrayList" + result2);
    }
}
