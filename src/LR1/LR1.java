package LR1;

import GramaticaDeGramaticas.*;
import java.util.ArrayList;
import java.util.HashSet;

public class LR1 {
    private EstadoLR estadoInicial;
    private ArrayList<EstadoLR> conjuntoEdos;
    private static SimboloNoTerminal RAIZ = new SimboloNoTerminal("$");
    
//    private ArrayList<SimboloNoTerminal> simbolosTerminales;
//    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private Gramatica gramatica;

    public LR1(Gramatica gramatica) {
        this.gramatica = gramatica;
        conjuntoEdos = new ArrayList<>();
        ArrayList<ItemLR> itemsInicial = new ArrayList<>();
        //Crear nodo raiz
        HashSet<SimboloNoTerminal> snt = new HashSet<>();
        snt.add(RAIZ);
        itemsInicial.add(new ItemLR(new ReglaLR(gramatica.getListaReglas().get(0)), snt));
        
        
        estadoInicial = new EstadoLR(itemsInicial);
        conjuntoEdos.add(estadoInicial);
        
        
//        simboloFollowPrevio = new HashMap<>();
//        simbolosTerminales = new ArrayList<>();
//        simbolosNoTerminales = new ArrayList<>();
        
        
//        ArrayList<ReglaLR> reglasIni = new ArrayList<>();
//        reglasIni.add(new ReglaLR(gramatica.getListaReglas().get(0)));
//        reglasIni = opCerradura(reglasIni);
//        
//        for(Regla regla: reglasIni){
//            System.out.println(regla+ "\t\t"+ regla.getNumeroRegla());
//        }
    }
    
    private void ir_A(){
        
    }
    
    private ArrayList<ItemLR> opCerradura(ArrayList<ItemLR> itemsLR){
        ArrayList<ItemLR> nuevosItems = new ArrayList<>();
        nuevosItems.addAll(itemsLR);
        
        for (int i = 0; i < nuevosItems.size(); i++) {
            SimboloNoTerminal simbolo = nuevosItems.get(i).getSimboloPunto();
            if(simbolo != null)
                continue;
            else if (simbolo.isTerminal()){
                simbolo = nuevosItems.get(i).getSiguienteSimboloPunto();
                nuevosItems.get(i).agregarSimbolos(first(simbolo));
            }else {
                if()
            }
            
            
            Simbolo simbolo = newReglas.get(i).getListaLadosDerechos().get(newReglas.get(i).getIndicePunto());
            if(simbolo instanceof SimboloNoTerminal){
                for (Regla reglaGr : gramatica.getListaReglas()) {
                    if(reglaGr.equals(newReglas.get(i))){
                        System.out.println("continuo");
                        continue;
                    
                    }
                    if(reglaGr.getLadoIzquierdo().getExpresion().equals(simbolo.getExpresion()))
                        newReglas.add(new ReglaLR(reglaGr));
                }
            }
        }
        return nuevosItems;
    }
    
    private HashSet<SimboloNoTerminal> first(SimboloNoTerminal simbolo){
        HashSet<SimboloNoTerminal> simbolos = new HashSet<>();
        if(simbolo.isTerminal())
            simbolos.add(simbolo);
        else {
            for (Regla regla : gramatica.getListaReglas()) {
                if(regla.getLadoIzquierdo().getExpresion().equals(simbolo.getExpresion())){
                    ArrayList<String> simbsPasados = new ArrayList<>();
                    SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
                    simbolos.addAll(firstSR(primerSimbolo, simbsPasados));
                }
            }
        }
        return simbolos;
    }
    
    private HashSet<SimboloNoTerminal> firstSR(SimboloNoTerminal simbolo, ArrayList<String> simbsPasados){
        HashSet<SimboloNoTerminal> simbolos = new HashSet<>();
        if(simbolo.isTerminal())
            simbolos.add(simbolo);
        else {
            ArrayList<Regla> reglas = new ArrayList<>();
            for (Regla regla : gramatica.getListaReglas()) {
                String ladoIzquierdo = regla.getLadoIzquierdo().getExpresion();
                if(!simbsPasados.contains(ladoIzquierdo) && ladoIzquierdo.equals(simbolo.getExpresion()))
                    reglas.add(regla);
            }
            if(!reglas.isEmpty())
                simbsPasados.add(reglas.get(0).getLadoIzquierdo().getExpresion());
            for (Regla regla : reglas) {
                SimboloNoTerminal primerSimbolo = regla.getListaLadosDerechos().get(0);
                simbolos.addAll(firstSR(primerSimbolo, simbsPasados));
            }
        }
        return simbolos;
    }
    
    public void generarTablaLR0(){
        
    }
    
    @Override
    public String toString(){
        String cadenaAux = "--- ESTADOS ---\n\n";
        for (EstadoLR conjuntoEdo : conjuntoEdos)
            cadenaAux += conjuntoEdo + "\n";
        return cadenaAux;
    }
}
