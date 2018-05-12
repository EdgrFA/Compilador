package LL1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import utilidades.TablaColumnaUnitaria;

public class AlgoritmoLL1 {
    private ArrayList<SimboloNoTerminal> simbolosTerminales;
    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    private Gramatica gramatica;
    
    public AlgoritmoLL1(Gramatica gramatica){
        this.gramatica = gramatica;
        //gramatica.agregarSimbolo(Gramatica.RAIZ);
        simboloFollowPrevio = new HashMap<>();
        simbolosTerminales = new ArrayList<>();
        simbolosNoTerminales = new ArrayList<>();
    }
        
    public void calcularFirstReglas( ){
        for (int i = 0; i < Gramatica.contadorReglas ; i++) {
            Regla regla = gramatica.getListaReglas().get(i);
            SimboloNoTerminal simboloInicial = regla.getListaLadosDerechos().get(0);
            First first = new  First( regla );
            if(AlgoritmoLL1.containsEpsilon(first.getSimbolos())){
                Follow followAux = simboloFollowPrevio.get( regla.getLadoIzquierdo() );
                if( followAux != null){
                    ArrayList<SimboloNoTerminal> simbolosFollow = new ArrayList<>(followAux.getSimbolos());
                    first.getSimbolos().addAll(simbolosFollow);
                }
            }
            if( ! simbolosNoTerminales.contains( regla.getLadoIzquierdo() ))
                simbolosNoTerminales.add( regla.getLadoIzquierdo() );
            crearRelacionesSimbolosNT(first.getSimbolos(), regla);
            System.out.print("\tRegla "+i+" : "+regla+"\t");
            System.out.println("first( "+ simboloInicial +" ) = "+first.getSimbolos());
        }
        actualizarSimbolosTerminales();
        AlgoritmoLL1.containsEpsilon(simbolosTerminales);
        simbolosTerminales.add(Gramatica.RAIZ);
    }
    
    private void crearRelacionesSimbolosNT(ArrayList<SimboloNoTerminal> simbolos ,Regla regla){
        for(SimboloNoTerminal nuevaRelacion : simbolos ){
            regla.getLadoIzquierdo().agregarRelacion(nuevaRelacion, regla );
        }
    }
    
    private void crearRelacionesSimbolosT(SimboloNoTerminal simbolo){
        simbolo.agregarRelacion(simbolo, Gramatica.POP);
    }
    
    public void actualizarSimbolosTerminales(){
        for (SimboloNoTerminal simbolo : gramatica.getSimbolos()) {
            if( ! simbolosTerminales.contains( simbolo )){
                if( simbolo.isTerminal() ){
                    simbolosTerminales.add( simbolo );
                    crearRelacionesSimbolosT( simbolo );
                }
            }
        }
    }
    
    public ArrayList<SimboloNoTerminal> calcularFirstSNT(){
        ArrayList<SimboloNoTerminal> simbolosR = null;
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                simbolosR = First.first( gramatica.getSimbolo(i) );
                System.out.print("first( "+ gramatica.getSimbolo(i) +" ) = ");
                System.out.println( simbolosR);
            }
        }
        return simbolosR;
    }
    
    public void calcularFollow(){
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                Follow follow = new  Follow( gramatica.getSimbolo(i), gramatica.getListaReglas() );
                simboloFollowPrevio.put(gramatica.getSimbolo(i), follow);
                System.out.println("follow ("+gramatica.getSimbolo(i)+") = "+follow.getSimbolos());
            }
        }
    }
    
    public boolean validarCadena(String cadena){
        Pila pila = new Pila();
        pila.add(Gramatica.RAIZ);
        System.out.println("snt 0 = "+simbolosNoTerminales.get(0));
        pila.add(simbolosNoTerminales.get(0));
        System.out.println("Soy la pila "+ pila);
        Cola simbolosCadena = convertirElementos(cadena);
        TablaColumnaUnitaria tabla = new TablaColumnaUnitaria(40);
        Object[] elementoEncabezado = {"Cola","Cadena", "Accion"};
        tabla.imprimirEncabezado(elementoEncabezado);
        for(;;){
            ArrayList<String> filaElementos = new ArrayList<>();
            filaElementos.add( pila.toString() );
            filaElementos.add( simbolosCadena.toString() );
            SimboloNoTerminal simboloFinalPila = (SimboloNoTerminal) pila.pop();
            Regla relacion = simboloFinalPila.getRelacion().get( simbolosCadena.getFirst() );
            if(relacion != null){
                if( relacion.equals( Gramatica.POP ) ){
                    filaElementos.add("POP");
                    simbolosCadena.remove();
                }else if( relacion.equals( Gramatica.ACEPT ) ){
                    filaElementos.add("Acept");
                    tabla.imprimirFila(filaElementos.toArray());
                    return true;
                }
                else{
                    filaElementos.add( relacion.getListaLadosDerechos()+","+relacion.getNumeroRegla() );
                    if( ! relacion.getListaLadosDerechos().get(0).equals(Gramatica.EPSILON ) )
                        pila.addAll(Pila.invertirArraySNT(relacion.getListaLadosDerechos()) );                           
                }
                tabla.imprimirFila(filaElementos.toArray());
            }else{
                filaElementos.add("ERROR");
                tabla.imprimirFila(filaElementos.toArray());
                return false;
            }
        }
    }
    
    public Cola convertirElementos(String cadena){
        String[] arrayElementos = cadena.split(" ");
        Cola elementoCola = new Cola();
        for(String arrayElemento: arrayElementos){
            SimboloNoTerminal simbolo = contieneSimboloGramatica(new SimboloNoTerminal(arrayElemento));
            elementoCola.add(simbolo);
        }       
        return elementoCola;
    }
    
    public SimboloNoTerminal contieneSimboloGramatica(SimboloNoTerminal simbolo){
        for (SimboloNoTerminal simboloGramatica : gramatica.getSimbolos()) {
            if(simboloGramatica.equals(simbolo)){
                return simboloGramatica;
            }
            else if(Gramatica.RAIZ.equals(simbolo))
                return Gramatica.RAIZ;
        }
        return simbolo;
    }
    
    public static boolean containsEpsilon(HashSet<SimboloNoTerminal> conjuntoSimbolos){
        for(Simbolo simbolo : conjuntoSimbolos){
            if(Gramatica.EPSILON.equals(simbolo)){
                conjuntoSimbolos.remove(simbolo);
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsEpsilon(ArrayList<SimboloNoTerminal> conjuntoSimbolos){
        for(Simbolo simbolo : conjuntoSimbolos){
            if(Gramatica.EPSILON.equals(simbolo)){
                conjuntoSimbolos.remove(simbolo);
                return true;
            }
        }
        return false;
    }
    
    public void generarTablaLL1(){
        //Encabezado 
        String encabezadoSeparacion = "%1s";
        ArrayList<String> columnasElementos = new ArrayList<>();
        columnasElementos.add("");
        for(SimboloNoTerminal simboloColumna : simbolosTerminales ){
            encabezadoSeparacion+="%20s ";
            columnasElementos.add(simboloColumna.toString());
        }
        System.out.println(String.format(encabezadoSeparacion,columnasElementos.toArray()));
        
        //Elementos por filas
        for(SimboloNoTerminal simboloFila : simbolosNoTerminales ){
            String filasSeparacion = "%1s ";
            ArrayList<String> filaElementos = new ArrayList<>();
            filaElementos.add(simboloFila.toString());
            for(SimboloNoTerminal simboloColumna : simbolosTerminales ){
                filasSeparacion += "%20s ";
                Regla relacion = simboloFila.getRelacion().get(simboloColumna);
                if(relacion==null)
                    filaElementos.add("---");
                else
                    filaElementos.add(relacion.getListaLadosDerechos()+","+relacion.getNumeroRegla());
            }
            System.out.println(String.format(filasSeparacion, filaElementos.toArray()));
        }
    }
}