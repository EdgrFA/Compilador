package LR0;

import Analizadores.AnalizadorLexico;
import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.SimboloNoTerminal;
import LL1.Follow;
import LR1.EstadoLR;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import utilidades.Pila;
import utilidades.TablaColumnaUnitaria;

public class AlgoritmoLR0 {
    static int contadorEstado = 0;
    private Gramatica gramatica;
    private Estado s0;
    private List<Estado> estados;
    private List<SimboloNoTerminal> simbolosTerminales;
    private List<SimboloNoTerminal> simbolosNoTerminales;
    private HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    
    public AlgoritmoLR0(Gramatica gramatica){
        estados = new ArrayList<>();
        this.gramatica = gramatica;
        simboloFollowPrevio = new HashMap<>();
    }
    
    public void obtenerS1(Regla regla){
        Nodo nodo0 = new Nodo(regla, 0);
        Estado s0 = new Estado(contadorEstado++);
        Estado.cerradura(nodo0, s0);
        this.s0 = s0;
    }
    
    public void actualizarSimbolos(){
        
    }
    
    public void calcularEstados(){
        Estado estadoActual;
        LinkedList<Estado> cola = new LinkedList<>();
        cola.add( s0 );
        estados.add(s0);
        while( ! cola.isEmpty() ){
            estadoActual = cola.remove();
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("s"+ estadoActual.getIdEstado() +"\n");
            for(SimboloNoTerminal simbolo : estadoActual.getSimbolosBeta()){
                if(!simbolo.equals(Estado.FINAL)){
                    System.out.print(" irA(s"+estadoActual.getIdEstado()+","+simbolo+") = ");
                    Estado nuevoEstado = new Estado( contadorEstado,
                            Estado.cerradura( Estado.irA(estadoActual, simbolo) ));
                    //Estado.buscarEstado(nuevoEstado, estados);
                    Estado edoAux = Estado.buscarEstado(nuevoEstado, estados);
                    if( edoAux == null ){
                        estados.add(nuevoEstado);
                        cola.add(nuevoEstado);
                        System.out.println("***nuevoEstado S"+ nuevoEstado.getIdEstado() +" == "+nuevoEstado);
                        estadoActual.crearRelacion(simbolo, nuevoEstado);
                        contadorEstado++;
                    }else{
                        System.out.println(" S "+edoAux.getIdEstado());
                        estadoActual.crearRelacion(simbolo, edoAux);
                    }
                }
            }
        }
        simbolosTerminales = gramatica.buscarSimbTerminales();
        simbolosTerminales.add(Gramatica.RAIZ);
        simbolosNoTerminales = gramatica.buscarSimbNoTerminales();
    }
    
    public void calcularReducciones(){
        //Predicate<Nodo> filtroNodoTerminal = nodo -> (nodo instanceof NodoTerminal) ;
        for(Estado estado: estados){
            //estado.stream().filter(filtroNodoTerminal).forEach(n-> System.out.println("S"+estado.getIdEstado()+" = "+n));
            for(Nodo nodo : estado){
                if(nodo instanceof NodoTerminal){
                    Follow followAux = simboloFollowPrevio.get(nodo.getRegla().getLadoIzquierdo());
                    for(SimboloNoTerminal simbolo : followAux.getSimbolos()){
                        estado.crearRelacion(simbolo, nodo.getRegla());
                    }
                }
            }
        }
    }
    
    public void calcularFollow(){
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                Follow follow = new  Follow( gramatica.getSimbolo(i), gramatica.getListaReglas() );
                simboloFollowPrevio.put(gramatica.getSimbolo(i), follow);
                System.out.println("follow ("+gramatica.getSimbolo(i)+") = "+follow.getSimbolos() );
            }
        }
    }

    
    public void generarTabla(){
        System.out.println("**********************************************");
        estados.stream().forEach( edo -> System.out.println("s"+edo.getIdEstado() + " = " +edo));
        System.out.println("**********************************************");
        TablaColumnaUnitaria tabla = new TablaColumnaUnitaria(10);
        tabla.imprimirEncabezado(simbolosTerminales.toArray(),simbolosNoTerminales.toArray());
        for(Estado estado: estados){
            ArrayList<String> filaElementos = new ArrayList<>();
            
            for(SimboloNoTerminal simbolo: simbolosTerminales){
                Estado edoAux = estado.getDesplazamiento(simbolo);
                if(edoAux != null){
                    filaElementos.add("d"+edoAux.getIdEstado());
                }else{
                    Regla regla = estado.getReduccion(simbolo);
                    if(regla != null)
                        filaElementos.add("r"+regla.getNumeroRegla());
                    else
                        filaElementos.add("-");
                }
            }
            
            for(SimboloNoTerminal simbolo: simbolosNoTerminales){
                Estado edoAux = estado.getDesplazamiento(simbolo);
                if(edoAux != null){
                    filaElementos.add(""+edoAux.getIdEstado());
                }else{
                    filaElementos.add("-");
                }
            }
            tabla.imprimirFila(filaElementos.toArray());
        }
    }
    
}