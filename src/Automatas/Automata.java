/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automatas;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Edgar Flores
 */
public class Automata {
    public static char EPSILON = 0x00;
    
    public HashSet<Estado> CerraduraE(Estado e){
        Estado r;
        HashSet<Estado> c = new HashSet<>();
        Stack<Estado> s = new Stack<>();
        s.clear();
        s.push(e);
        //Se revisa realiza hasta que la pila este vacia
        while(!s.isEmpty()){
            r=s.pop();
            if(!c.contains(r)){
                c.add(r);
                for(Transicion t : r.getTrancisiones()){
                    if(t.SeEncuentra(EPSILON))
                        s.push(t.getEstadoDestino());
                }
            }
        }
        return c;
    }
    
    public HashSet<Estado> CerraduraE(HashSet<Estado> E){
        HashSet<Estado> c = new HashSet<>();
        for(Estado e : E){
            c.addAll(CerraduraE(e));
        }
        return c;
    }
    
}
