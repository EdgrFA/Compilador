package LL1;

import java.util.ArrayList;

/**
 * Gramatica contiene un conjunto de reglas con Simbolos Terminales y Simbolos No Terminales
 * @author Andres
 */
public class Gramatica {
    public static final Simbolo RAIZ = new Simbolo("$");
    public static final Simbolo EPSILON = new Simbolo("\\e");
    
    public static int contadorReglas;
    private ArrayList<Regla> listaReglas;
    private ArrayList<SimboloNoTerminal> simbolos;
    
    public Gramatica(){
        listaReglas = new ArrayList<>();
        simbolos = new ArrayList<>();
    }
    
    public void agregarRegla(Regla regla){
        regla.setNumeroRegla(contadorReglas++);
        listaReglas.add(regla);
    }
    
    public void agregarSimbolo(SimboloNoTerminal simbolo){
        simbolos.add(simbolo);
    }
    
    public int comprobarSimbolo(Simbolo simboloComporbar){
        for(int i = 0; i < simbolos.size();i++){
            if(simbolos.get(i).equals(simboloComporbar))
                return i;
        }
        return -1;
    }
    
    public SimboloNoTerminal getSimbolo(int index){
        return simbolos.get(index);
    }
    
    public int getNumeroSimbolos(){
        return simbolos.size();
    }
    
    public ArrayList<Regla> getListaReglas(){
        return listaReglas;
    }
    
    public void imprimirReglas(){
        for(Regla regla: listaReglas){
            System.out.println(regla+ "\t\t"+ regla.getNumeroRegla());
        }
    }
    
    public void imprimirSimbolos(){
        for(SimboloNoTerminal simbolo: simbolos){
            System.out.println(simbolo.getExpresion()+"\tTerminal="+simbolo.isTerminal());
            /*
            if(simbolo.isTerminal()== false){
                for(Regla regla : simbolo.getReglasLadosDerechos()){
                    System.out.println(regla);
                }
            }
            */
        }
    }
}