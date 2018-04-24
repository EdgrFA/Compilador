package GramaticaDeGramaticas;

import java.util.ArrayList;

public class Gramatica {
    private ArrayList<Nodo> reglas;

    public Gramatica() {
        this.reglas = new ArrayList<Nodo>();
    }
    
    public Nodo agregarRegla(){
        Nodo regla = new Nodo();
        reglas.add(regla);
        return regla;
    }
    
    public void removerRegla(Nodo regla){
        reglas.remove(regla);
    }

    public ArrayList<Nodo> getReglas() {
        return reglas;
    }
    
    public void imprimirGramatica(){
        for (Nodo regla : reglas) {
            System.out.print(regla.getSimbolo() + " -> ");
            boolean inicial = true;
            for (ArrayList<String> listasSimbs : regla.getListasSimbs()) {
                if (inicial)
                    inicial = false;
                else
                    System.out.print("| ");
                for (String simbolo : listasSimbs)
                    System.out.print(simbolo + " ");
            }
            System.out.println(";");
        }
        System.out.println("");
    }
}
