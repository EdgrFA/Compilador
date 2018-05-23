package LR0;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.SimboloNoTerminal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Estado extends ArrayList<Nodo> {
    public static SimboloNoTerminal FINAL = new SimboloNoTerminal("\\0");
    private static Function<Nodo,Nodo> moverFunction = nodo2 ->  
        ( nodo2.getUbicacionPunto() +1 < nodo2.getNumeroElementos() ) ?
            new Nodo(nodo2.getRegla(),nodo2.getUbicacionPunto()+1) 
            : new NodoTerminal(nodo2.getRegla());
    
    private final int idEstado;
    private SimboloNoTerminal reductor;
    private final HashMap<SimboloNoTerminal, Estado> relacion;
    private final HashMap<SimboloNoTerminal, Regla> reduccion;
    
    public Estado(int noEdo, List<Nodo> conjuntoNodos) {
        this.idEstado = noEdo;
        this.addAll(conjuntoNodos);
        relacion = new HashMap<>();
        reduccion = new HashMap<>();
    }
    
    public Estado(int noEdo) {
        this.idEstado = noEdo;
        relacion = new HashMap<>();
        reduccion = new HashMap<>();
    }
    
    public void crearRelacion(SimboloNoTerminal simbolo, Estado estado){
        relacion.put(simbolo, estado);
    }
    
    public void crearRelacion(SimboloNoTerminal simbolo, Regla regla){
        reduccion.put(simbolo, regla);
    }
    
    public Estado getDesplazamiento(SimboloNoTerminal simbolo){
        return relacion.get(simbolo);
    }
    
    public Regla getReduccion(SimboloNoTerminal simbolo){
        return reduccion.get(simbolo);
    }
    
    public List<SimboloNoTerminal> getSimbolosBeta(){
        List<SimboloNoTerminal> simbolosBeta = new ArrayList<>();
        this.forEach( nodo -> {
            if(!simbolosBeta.contains(nodo.getSimboloBeta()))
                simbolosBeta.add(nodo.getSimboloBeta());
        });
        return simbolosBeta;
    }
    
    public int getIdEstado(){
        return idEstado;
    }
    
    public static List<Nodo> irA( Estado estado , SimboloNoTerminal simbolo){
        List<Nodo> R;
        //Filtro, se buscan los Nodos que pueden tener transición con Simbolo
        Predicate<Nodo> filtroNodosBeta = nodo -> nodo.getSimboloBeta().equals(simbolo);
        R = ( estado.stream()
                .filter(filtroNodosBeta).map(moverFunction)
                //.peek(n -> System.out.println("---"+n))
                .collect(Collectors.toList()) );         
        return R;
    }
    
    public static void cerradura( Nodo nodo , List<Nodo> conjuntoPrevio ){
        if( ! conjuntoPrevio.contains(nodo) ){
            conjuntoPrevio.add(nodo);
            if( !(nodo.getSimboloBeta().isTerminal()) && !(nodo instanceof NodoTerminal) ){
                for(Regla regla : nodo.getSimboloBeta().getReglasLadosDerechos()){
                    Nodo nodoAux = new Nodo(regla, 0);
                    cerradura(nodoAux, conjuntoPrevio);
                }
            }else if(nodo instanceof NodoTerminal){
                //reductor = nodo.getRegla().getLadoIzquierdo();
            }
        }
    }
    
    public static List<Nodo> cerradura( List<Nodo> conjuntoNodos ){
        List<Nodo> conjuntoPrevio = new ArrayList<>();
        for(Nodo nodo : conjuntoNodos){
            cerradura(nodo, conjuntoPrevio);
        }
        return conjuntoPrevio;
    }
    
    
    
    public static List<Nodo> mover(List<Nodo> lista, Nodo nodo ){
        List<Nodo> listaResultado = new ArrayList();
        listaResultado.add(moverFunction.apply(nodo));
        return listaResultado;
    }
    
    public static Estado buscarEstado(Estado estado, List<Estado> estados ){
        for(Estado estadoAux : estados){
            if(estadoAux.equals(estado)){
                return estadoAux;
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(Object obj){
        Estado estadoAux = (Estado ) obj;
        for(Nodo nodo: estadoAux){
            if(!this.contains(nodo))
                return false;
        }
        return true;
    }
    
    @Override
    public boolean contains(Object obj){
        Nodo nodoAux = (Nodo) obj;
        for(Nodo nodo: this){
            if(nodoAux.equals(nodo))
                return true;
        }
        return false;
    }
}
