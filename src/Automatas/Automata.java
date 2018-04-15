package Automatas;

import java.util.HashSet;
import java.util.Stack;

public abstract class Automata implements Movimiento{
    public final static char EPSILON = 0x00;
    
    public HashSet<Estado> CerraduraE(Estado e){
        Estado edoAuxiliar;
        HashSet<Estado> conjuntoResultante = new HashSet<>();
        Stack<Estado> pila = new Stack<>();
        pila.clear();
        pila.push(e);
        //Se revisa realiza hasta que la pila este vacia
        while(!pila.isEmpty()){
            edoAuxiliar=pila.pop();
            if(!conjuntoResultante.contains(edoAuxiliar)){
                conjuntoResultante.add(edoAuxiliar);
                for(Transicion t : edoAuxiliar.getTrancisiones()){
                    if(t.contains(EPSILON))
                        pila.push(t.getEstadoDestino());
                }
            }
        }
        return conjuntoResultante;
    }
    
    public HashSet<Estado> CerraduraE(HashSet<Estado> E){
        HashSet<Estado> conjuntoResultante = new HashSet<>();
        for(Estado edoActual : E){
            conjuntoResultante.addAll(CerraduraE(edoActual));
        }
        return conjuntoResultante;
    }
    
    public HashSet<Estado> IrA(HashSet<Estado> E, char simbolo) {
        HashSet<Estado> conjuntoResultante = new HashSet<>();
        for(Estado edoActual : E){
            conjuntoResultante.addAll(Mover(edoActual, simbolo));
        }
        return CerraduraE(conjuntoResultante);
    }
    
    @Override
    public HashSet<Estado> Mover(Estado e, char c) {
        HashSet<Estado> R = new HashSet<>();
        for(Transicion tran : e.getTrancisiones()) {
            if(tran.contains(c))
                R.add(tran.getEstadoDestino());
        }
        return R;
    }

    @Override
    public HashSet<Estado> MoverCjto(HashSet<Estado> E, char c) {
        HashSet<Estado> R = new HashSet<>();
        for(Estado e : E){
            R.addAll(Mover(e, c));
        }
        return R;
    }
}
