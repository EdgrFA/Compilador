package LR1;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import GramaticaDeGramaticas.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import utilidades.Pila;
import utilidades.TablaColumnaUnitaria;

public class LR1 {
    private EstadoLR estadoInicial;
    private ArrayList<EstadoLR> estados;
    private FirstLR firstLR;
    private static final SimboloNoTerminal RAIZ = new SimboloNoTerminal("$");
    private Gramatica gramatica;

    public LR1(Gramatica gramatica){
        gramatica.crearAFDGramatica();
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
        buscarReducciones();
    }
    
    private void buscarReducciones(){
        for (EstadoLR estado : estados)
            for (ItemLR itemLR : estado.getItemsLR())
                if(itemLR.puntoAlFinal())
                    for (SimboloNoTerminal simboloNT : itemLR.getSimbolosT()) 
                        estado.crearReduccion(simboloNT, itemLR.getRegla());
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
        System.out.println("******************** TABLA LR1 **************************");
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
                if(!estado.reduccionesIsEmpty()){
                    Regla regla;
                    if((regla = estado.obtenerReduccion(simbolo)) != null){
                        filaElementos.add("r" + regla.getNumeroRegla());
                        continue;
                    }
                }
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
            tabla.imprimirFila(filaElementos.toArray());
        }   
    }
    
    public boolean evaluarExpresion(String expresion){
        AnalizadorLexico al = new AnalizadorLexico(expresion, gramatica.getAfdGr());
        Stack<EstadoLR> pilaEstados = new Pila();
        pilaEstados.push(estadoInicial);
        EstadoLR estadoActual = estadoInicial;
        int token = al.obtenerToken();
        while(token != -1){
            for (SimboloNoTerminal simbolo : gramatica.getSimbolos()) {
                //Revisar si el simbolo coincide con el token o si es el fin de cadena
                if(simbolo.getIdAfd() != token && token != 0)
                    continue;
                if(token == 0)
                    simbolo = RAIZ; //Sustituir simbolo de fin de cadena 
                //Revisar Derivaciones
                if(!estadoActual.derivacionesIsEmpty()){
                    //Obtener el nuevo estado obtenido de la derivacion y agregar a la pila
                    EstadoLR estadoAux = estadoActual.obtenerDerivacion(simbolo);
                    if(estadoAux != null){
                        estadoActual = estadoAux;
                        pilaEstados.push(estadoActual);
                        token = al.obtenerToken();
                        break;
                    }
                }
                //Revisar Reducciones
                if(!estadoActual.reduccionesIsEmpty()){
                    //Obtener regla de la reduccion
                    Regla regla = estadoActual.obtenerReduccion(simbolo);
                    if(regla != null){
                        EstadoLR estadoAux;
                        //Quitar i elementos de la pila segun la cantidad de simbolos que contenga la regla
                        for (int i = 0; i < regla.getListaLadosDerechos().size(); i++)
                            pilaEstados.pop();
                        //Revisar si la reduccion devolvio la regla 0 (Aceptar)
                        if(regla.getNumeroRegla() == 0){ 
                            //Revisar si la pila tiene el fin de expresion
                            estadoAux = (EstadoLR)pilaEstados.pop();
                            if(estadoAux.getId() == 0)
                                return true;
                            System.out.println("Error Sintactico (Expresion incompleta)");
                            return false;
                        }
                        //Obtener derivacion del ultimo elemento de la pila utilizando el SimboloIzquierdo de la regla  **Revisar en caso de obtener null
                        estadoAux = (EstadoLR)pilaEstados.get(pilaEstados.size()-1);
                        estadoActual = estadoAux.obtenerDerivacion(regla.getLadoIzquierdo());
                        pilaEstados.push(estadoActual);
                        break;
                    }
                }        
                System.out.println("Error Sintactico (No existe transicion con el simbolo '" + simbolo.getExpresion() + "')");
                return false;
            }
        }
        System.out.println("Error Lexico");
        return false;
    }
    
    @Override
    public String toString(){
        String cadenaAux = "************* ESTADOS *************\n";
        for (EstadoLR conjuntoEdo : estados)
            cadenaAux += conjuntoEdo + "\n";
        return cadenaAux;
    }
}
