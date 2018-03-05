package Automatas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AFNs {
    private ArrayList<AFN> automatas;
    HashSet<Estado> edosAceptacion;
    HashMap<Estado,Integer> tokens;
    private int contadorToken = 10;
    
    public AFNs() {
        automatas = new ArrayList<>();
        edosAceptacion = new HashSet<>();
        tokens = new HashMap<>();
    }
    
    /**
     * Creacion de los automatas
     * @param carMin
     * @param carMax 
     */
    public void crearAFN(char carMin, char carMax){
        AFN automata = new AFN(carMin, carMax);
        automatas.add(automata);
    }
    
    public void crearAFN(char caracter){
        AFN automata = new AFN(caracter);
        automatas.add(automata);
    }
    
    //LLamada a los metodos
    public void union(int id1, int id2){
        automatas.get(id1).unirAFN(automatas.get(id2));
        automatas.remove(id2);
    }
    
    public void concatenar(int id1, int id2){
        automatas.get(id1).concatenarAFN(automatas.get(id2));
        automatas.remove(id2);
    }
    
    public void cerraduraSuma(int id1){
        automatas.get(id1).cerraduraSuma();
    }
    
    public void cerraduraAsterisco(int id1){
        automatas.get(id1).cerraduraAsterisco();
    }
    
    public void operacionSigno(int id1){
        automatas.get(id1).operacionSigno();
    }
    
    public boolean validar(int id, String s){
        return automatas.get(id).AnalizarCardena(s);
    }
    
    /*
    Valores de la clase
    */
    public ArrayList<AFN> getAutomatas() {
        return automatas;
    }
    
    public HashSet<Estado> getEdosAceptacion(){
        for(AFN afn: automatas){
            edosAceptacion.addAll(afn.getEdosAceptacion());
        }
        return edosAceptacion;
    }
    
    public void actualizarTokens(){
        for(AFN afn: automatas){
            edosAceptacion.addAll(afn.getEdosAceptacion());
        }
        contadorToken = 10;
        for(Estado e: edosAceptacion){
            tokens.put(e, contadorToken);
            contadorToken+=10;
        }
        
    }

    public int containsEdoAcept(HashSet<Estado> cjtoEdos){
        actualizarTokens();
        for(Estado edo: edosAceptacion){
            if(cjtoEdos.contains(edo))
                return tokens.get(edo);
        }
        return -1;
    }
    
    public int getNoAutomatas() {
        return automatas.size();
    }

    public void setAutomatas(ArrayList<AFN> Automatas) {
        this.automatas = Automatas;
    }
    
    public AFN getAutomata(int index){
        return automatas.get(index);
    }
    
    public void setAutomata(int index, char c){
        AFN afnaux= new AFN(c);
    }
    
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        afns.crearAFN('1','1');
        afns.crearAFN('3','9');
        afns.getAutomata(0).concatenarAFN(afns.getAutomata(1));
        System.out.println("Llego aqui merenges");
        afns.getAutomata(0).cerraduraAsterisco();
        afns.getAutomata(0).cerraduraSuma();
        afns.getAutomata(0).operacionSigno();
        for(Character c : afns.getAutomata(0).getAlfabeto()){
            System.out.println("c="+c);
        }
    }
}