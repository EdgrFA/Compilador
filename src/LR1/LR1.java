package LR1;

import GramaticaDeGramaticas.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LR1 {
    private EstadoLR estadoInicial;
    private ArrayList<EstadoLR> conjuntoEdos;
    private FirstLR firstLR;
    private static SimboloNoTerminal RAIZ = new SimboloNoTerminal("$");
     
    
//    private ArrayList<SimboloNoTerminal> simbolosTerminales;
//    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private Gramatica gramatica;

    public LR1(Gramatica gramatica) {
        this.gramatica = gramatica;
        conjuntoEdos = new ArrayList<>();
        firstLR = new FirstLR(gramatica);
        ArrayList<ItemLR> itemsInicial = new ArrayList<>();
        //Crear nodo raiz
        HashSet<SimboloNoTerminal> snt = new HashSet<>();
        snt.add(RAIZ);
        ItemLR itemAux = new ItemLR(new ReglaLR(gramatica.getListaReglas().get(0)));
        itemAux.agregarSimbolos(snt);
        itemsInicial.add(itemAux);        
        opCerradura(itemsInicial);
        estadoInicial = new EstadoLR(itemsInicial);
        conjuntoEdos.add(estadoInicial);
        //Calcular los demas estados
        System.out.println("Algoritmo LR1:\n");
        for(int i = 0; i < conjuntoEdos.size(); i++){
            System.out.println("Analizando S" + conjuntoEdos.get(i).getId());
            for (SimboloNoTerminal simbolo : gramatica.getSimbolos()) {
                System.out.print("Ir_A(S"+conjuntoEdos.get(i).getId()+","+simbolo+") = ");
                ArrayList<ItemLR> newItem = ir_A(conjuntoEdos.get(i), simbolo);
                if(newItem.isEmpty()){
                    System.out.println(" X ");
                    continue;
                }else {
                    boolean esNuevo = true;
                    for (EstadoLR estado : conjuntoEdos) {
                        if (estado.compararItems(newItem)){
                            conjuntoEdos.get(i).crearDerivacion(simbolo, estado);
                            esNuevo = false;
                            System.out.println(" S" + estado.getId());
                            break;
                        }
                    }
                    if (esNuevo) {
                        EstadoLR newEstado = new EstadoLR(newItem);
                        conjuntoEdos.add(newEstado);
                        System.out.println(newEstado);
                    }
                }
            }
        }
        
        
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
    
    private ArrayList<ItemLR> ir_A(EstadoLR estado, SimboloNoTerminal snt){
        ArrayList<ItemLR> itemsLR = new ArrayList<>();
        for (ItemLR itemLR : estado.getItemsLR()) {
            if(itemLR.getSimboloPunto() != null && itemLR.getSimboloPunto().getExpresion().equals(snt.getExpresion())){
                ItemLR itemAux = new ItemLR(itemLR);
                itemAux.recorrerPuntoRegla();
                itemsLR.add(itemAux);
            }
        }
        if(!itemsLR.isEmpty())
            System.out.print("Ce"+itemsLR+" = ");
        opCerradura(itemsLR);
        return itemsLR;
    }
    
    private void opCerradura(ArrayList<ItemLR> itemsLR){
        for (int i = 0; i < itemsLR.size(); i++) {
            SimboloNoTerminal simbolo = itemsLR.get(i).getSimboloPunto();
            SimboloNoTerminal nextSimbolo = itemsLR.get(i).getSiguienteSimboloPunto();
            if(simbolo == null){
                continue;
            }else if (simbolo.isTerminal()){
                if(nextSimbolo != null){
                    ItemLR itemAux = new ItemLR(itemsLR.get(i));
                    itemAux.agregarSimbolos(firstLR.getFirst(nextSimbolo));
                    if(!existeEnLista(itemsLR, itemAux))
                        itemsLR.add(itemAux);
                }
            }else {
                //Revisar reglas que conciden con simbolo
                ArrayList<Regla> reglas = new ArrayList<>();
                for (Regla regla : gramatica.getListaReglas()) {
                    String ladoIzquierdo = regla.getLadoIzquierdo().getExpresion();
                    if(ladoIzquierdo.equals(simbolo.getExpresion()))
                        reglas.add(regla);
                }
                if(nextSimbolo != null){
                    for (Regla regla : reglas){
                        ItemLR itemAux = new ItemLR(regla);
                        itemAux.agregarSimbolos(firstLR.getFirst(nextSimbolo));
                        if(!existeEnLista(itemsLR, itemAux))
                            itemsLR.add(itemAux);
                    }                
                }else {
                    for (Regla regla : reglas){
                        ItemLR itemAux = new ItemLR(regla);
                        itemAux.agregarSimbolos(itemsLR.get(i).getSimbolosT());
                        if(!existeEnLista(itemsLR, itemAux))
                            itemsLR.add(itemAux);
                    }
                }
            }
        }
        unirItemsIguales(itemsLR);
    }
    
    private boolean existeEnLista(ArrayList<ItemLR> itemsLR, ItemLR newItem){
        for (ItemLR itemLR : itemsLR)
            if(itemLR.equals(newItem))
                return true;
        return false;
    }
    
    private void unirItemsIguales(ArrayList<ItemLR> itemsLR){
        for (int i = 0; i < itemsLR.size(); i++) {
            for (int j = i+1; j < itemsLR.size(); j++) {
                if(itemsLR.get(i).getRegla().equals(itemsLR.get(j).getRegla())){
                    itemsLR.get(i).agregarSimbolos(itemsLR.get(j).getSimbolosT());
                    itemsLR.remove(j);
                    j--;
                }
            }
        }
    }
    
    
    public void generarTablaLR0(){
        
    }
    
    @Override
    public String toString(){
        String cadenaAux = "************* ESTADOS *************\n";
        for (EstadoLR conjuntoEdo : conjuntoEdos)
            cadenaAux += conjuntoEdo + "\n";
        return cadenaAux;
    }
}
