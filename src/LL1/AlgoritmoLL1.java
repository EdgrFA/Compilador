package LL1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class AlgoritmoLL1 {
    private HashSet<SimboloNoTerminal> simbolos;
    private ArrayList<SimboloNoTerminal> simbolosTerminales;
    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    private Gramatica gramatica;
    
    public AlgoritmoLL1(Gramatica gramatica){
        this.gramatica = gramatica;
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
            if( ! simbolosTerminales.contains( regla.getListaLadosDerechos().get(0) )){
                if( regla.getListaLadosDerechos().get(0).isTerminal() )
                    simbolosTerminales.add( regla.getListaLadosDerechos().get(0) );
            }
            System.out.print("\tRegla "+i+" : ");
            System.out.print( regla +"\t");
            System.out.print("first( "+ simboloInicial +" ) = ");
            System.out.println( first.getSimbolos() );
            crearRelacionesSimbolosNT(first.getSimbolos(), regla);
        }
        AlgoritmoLL1.containsEpsilon(simbolosTerminales);
        simbolosTerminales.add(Gramatica.RAIZ);
    }
    
    private void crearRelacionesSimbolosNT(ArrayList<SimboloNoTerminal> simbolos ,Regla regla){
        for(SimboloNoTerminal nuevaRelacion : simbolos ){
            regla.getLadoIzquierdo().agregarRelacion(nuevaRelacion, regla );
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
    
    public void generarTabla(){      
        //Encabezado 
        String encabezadoSeparacion = "%1s";
        ArrayList<String> terminales = new ArrayList<>();
        terminales.add("");
        for(SimboloNoTerminal simboloColumna : simbolosTerminales ){
            encabezadoSeparacion+="%20s ";
            terminales.add(simboloColumna.toString());
        }
        System.out.println(String.format(encabezadoSeparacion,terminales.toArray()));
        
        
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
                else{
                    filaElementos.add(relacion.getListaLadosDerechos()+","+relacion.getNumeroRegla());
                }
                
            }
            System.out.println(String.format(filasSeparacion, filaElementos.toArray()));
        }
        
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
    
}