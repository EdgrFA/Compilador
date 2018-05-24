package LR1;

import GramaticaDeGramaticas.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import utilidades.TablaColumnaUnitaria;

public class LR1 {
    private EstadoLR estadoInicial;
    private ArrayList<EstadoLR> estados;
    private FirstLR firstLR;
    private static final SimboloNoTerminal RAIZ = new SimboloNoTerminal("$");
     
    
//    private ArrayList<SimboloNoTerminal> simbolosTerminales;
//    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private Gramatica gramatica;

    public LR1(Gramatica gramatica) {
        this.gramatica = gramatica;
        RAIZ.setTerminal(true);
        estados = new ArrayList<>();
        firstLR = new FirstLR(gramatica);
        System.out.println(firstLR);
        ArrayList<ItemLR> itemsInicial = new ArrayList<>();
        //Crear nodo raiz
        HashSet<SimboloNoTerminal> snt = new HashSet<>();
        snt.add(RAIZ);
        ItemLR itemAux = new ItemLR(new ReglaLR(gramatica.getListaReglas().get(0)));
        itemAux.agregarSimbolos(snt);
        itemsInicial.add(itemAux);        
        opCerradura(itemsInicial);
        estadoInicial = new EstadoLR(itemsInicial);
        estados.add(estadoInicial);
        //Calcular los demas estados
        System.out.println("Algoritmo LR1:\n");
        for(int i = 0; i < estados.size(); i++){
            System.out.println("Analizando S" + estados.get(i).getId());
            for (SimboloNoTerminal simbolo : gramatica.getSimbolos()) {
                System.out.print("Ir_A(S"+estados.get(i).getId()+","+simbolo+") = ");
                ArrayList<ItemLR> newItem = ir_A(estados.get(i), simbolo);
                if(newItem.isEmpty()){
                    System.out.println(" X ");
                    continue;
                }else {
                    boolean esNuevo = true;
                    for (EstadoLR estado : estados) {
                        if (estado.compararItems(newItem)){
                            estados.get(i).crearDerivacion(simbolo, estado);
                            esNuevo = false;
                            System.out.println(" S" + estado.getId());
                            break;
                        }
                    }
                    if (esNuevo) {
                        if(estados.get(i).compararItems(newItem))
                            estados.get(i).crearDerivacion(simbolo, estados.get(i));
                        else{
                            EstadoLR newEstado = new EstadoLR(newItem);
                            estados.add(newEstado);
                            estados.get(i).crearDerivacion(simbolo, newEstado);
                            System.out.println(newEstado);
                        }
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
//                if(nextSimbolo != null){
//                    ItemLR itemAux = new ItemLR(itemsLR.get(i));
//                    itemAux.agregarSimbolos(firstLR.getFirst(nextSimbolo));
//                    if(!existeEnLista(itemsLR, itemAux))
//                        itemsLR.add(itemAux);
//                }
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
    
    private ArrayList<SimboloNoTerminal> obtenerSimbolos(){
        ArrayList<SimboloNoTerminal> simbolos = new ArrayList<>();
        for (SimboloNoTerminal simbolo : gramatica.getSimbolos())
           if(simbolo.isTerminal())
               simbolos.add(simbolo);
        simbolos.add(RAIZ);
        for (SimboloNoTerminal simbolo : gramatica.getSimbolos())
           if(!simbolo.isTerminal())
               simbolos.add(simbolo);
        return simbolos;
    }
    
    
    public void imprimirTablaLR1(){
        System.out.println("**********************************************");
        TablaColumnaUnitaria tabla = new TablaColumnaUnitaria(gramatica.getSimbolos().size() + 2);
        //Encabezados
        Object[] encabezados = new Object[gramatica.getSimbolos().size() + 2];
        encabezados[0] = "Estados";
        ArrayList<SimboloNoTerminal> simbolos = obtenerSimbolos();
        for (int i = 0; i < simbolos.size(); i++)
            encabezados[i+1] = simbolos.get(i).getExpresion();
        tabla.imprimirEncabezado(encabezados);
        //Filas
        for (EstadoLR estado : estados){
            ArrayList<String> filaElementos = new ArrayList<>();
            filaElementos.add("S" + estado.getId());
            for (SimboloNoTerminal simbolo : simbolos) {
                if(estado.derivacionesIsEmpty()){
                    boolean reduccion = false;
                    for (ItemLR itemLR : estado.getItemsLR()) {
                        if(itemLR.contieneSimbolo(simbolo)){
                            filaElementos.add("r" + itemLR.getRegla().getNumeroRegla());
                            reduccion = true;
                            break;
                        }
                    }
                    if(!reduccion)
                        filaElementos.add("");
                }else{
                    int id = estado.getIndiceTrancision(simbolo);
                    if(simbolo.isTerminal()){
                        if(id != -1)
                            filaElementos.add("d" + id);
                        else
                            filaElementos.add("");
                    }else{
                        if(id != -1)
                            filaElementos.add(""+id);
                        else
                            filaElementos.add("");
                    }
                }
            }
            tabla.imprimirFila(filaElementos.toArray());
        }
        
    }
    
    @Override
    public String toString(){
        String cadenaAux = "************* ESTADOS *************\n";
        for (EstadoLR conjuntoEdo : estados)
            cadenaAux += conjuntoEdo + "\n";
        return cadenaAux;
    }
}
