package Automatas;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Edgar Flores
 */
public class Automata implements Movimiento{
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
    
    public HashSet<Estado> IrA(HashSet<Estado> E, char c) {
        HashSet<Estado> R = new HashSet<>();
        for(Estado e : E){
            R.addAll(Mover(e, c));
        }
        return CerraduraE(R);
    }
    
    @Override
    public HashSet<Estado> Mover(Estado e, char c) {
        HashSet<Estado> R = new HashSet<>();
        for(Transicion tran : e.getTrancisiones()) {
            if(tran.SeEncuentra(c))
                R.add(tran.getEstadoDestino());
        }
        return R;
    }

    @Override
    public HashSet<Estado> MoverC(HashSet<Estado> E, char c) {
        HashSet<Estado> R = new HashSet<>();
        for(Estado e : E){
            R.addAll(Mover(e, c));
        }
        return R;
    }
}
