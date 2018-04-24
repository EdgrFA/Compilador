package GramaticaDeGramaticas;

import java.util.ArrayList;

public class Nodo {
    private String simbolo;
    private ArrayList<ArrayList<String>> listasSimbs;

    public Nodo() {
        listasSimbs = new ArrayList<ArrayList<String>>();
    }
    
    public ArrayList<String> agregarListaSimbs(){
        ArrayList<String> listaSimbs = new ArrayList<String>();
        listasSimbs.add(listaSimbs);
        return listaSimbs;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String Simbolo) {
        this.simbolo = Simbolo;
    }

    public ArrayList<ArrayList<String>> getListasSimbs() {
        return listasSimbs;
    }
}
