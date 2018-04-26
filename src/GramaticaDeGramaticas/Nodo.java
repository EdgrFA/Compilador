package GramaticaDeGramaticas;

import java.util.ArrayList;

public class Nodo {
    private Simbolo simbolo;
    private ArrayList<ArrayList<Simbolo>> listasSimbs;

    public Nodo() {
        listasSimbs = new ArrayList<ArrayList<Simbolo>>();
        simbolo = new Simbolo("");
    }
    
    public ArrayList<Simbolo> agregarListaSimbs(){
        ArrayList<Simbolo> listaSimbs = new ArrayList<>();
        listasSimbs.add(listaSimbs);
        return listaSimbs;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }
    
    public String getStrSimbolo(){
        return simbolo.getSimbolo();
    }

    public void setSimbolo(String simbolo) {
        this.simbolo.setSimbolo(simbolo);
    }

    public ArrayList<ArrayList<Simbolo>> getListasSimbs() {
        return listasSimbs;
    }
}
