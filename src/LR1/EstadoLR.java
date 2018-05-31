package LR1;

import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.Simbolo;
import java.util.ArrayList;
import java.util.HashMap;

public class EstadoLR {
    private static int idGlobal = 0;
    private final int id;
    private boolean analizado;
    private ArrayList<ItemLR> itemsLR;
    private HashMap<Simbolo, EstadoLR> derivacionesMap;
    private HashMap<Simbolo, Regla> reduccionesMap;
    //Conjunto de reghlas con puntos
    
    public EstadoLR(ArrayList<ItemLR> itemsLR) {
        this.id = idGlobal++;
        this.analizado = false;
        this.itemsLR = itemsLR;
        this.derivacionesMap = new HashMap<>();
        this.reduccionesMap = new HashMap<>();
    }
    
    public void crearDerivacion(Simbolo simbolo, EstadoLR estadoDestino){
        derivacionesMap.put(simbolo, estadoDestino);
    }
    
    public void crearReduccion(Simbolo simbolo, Regla regla){
        reduccionesMap.put(simbolo, regla);
    }
    
    public int getIndiceTrancision(Simbolo simbolo){
        if(derivacionesMap.isEmpty())
            return -1;
        if(derivacionesMap.get(simbolo) == null)
            return -1;
        return derivacionesMap.get(simbolo).getId();
    }
    
    public boolean derivacionesIsEmpty(){
        return derivacionesMap.isEmpty();
    }
    
    public boolean reduccionesIsEmpty(){
        return reduccionesMap.isEmpty();
    }
    
    public boolean isAnalizado() {
        return analizado;
    }

    public void setAnalizado(boolean analizado) {
        this.analizado = analizado;
    }

    public ArrayList<ItemLR> getItemsLR() {
        return itemsLR;
    }
    
    /**
     * Compara una lista de items con la lista de items de el estado.
     * @param itemsLR : Items que se compararan.
     * @return true en caso de ser iguales, false son diferentes.
     */
    public boolean compararItems(ArrayList<ItemLR> itemsLR){
        if(this.itemsLR.size() != itemsLR.size())
            return false;   //Diferentes tamaños
        
        for (ItemLR itemLR1 : this.itemsLR) {
            boolean existe = false;
            for (ItemLR itemLR2: itemsLR) {
                if(itemLR1.equals(itemLR2)){
                    existe = true;
                    break;
                }
            }
            if(!existe)
                return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }
    
    public EstadoLR obtenerDerivacion(Simbolo snt){
        return derivacionesMap.get(snt);
    }

    public Regla obtenerReduccion(Simbolo snt){
        return reduccionesMap.get(snt);
    }
    
    @Override
    public String toString(){
        String cadenaAux = "S" + id + " = { ";
        int i = 1;
        for (ItemLR itemLR : itemsLR) {
            cadenaAux += itemLR;
            if(i < itemsLR.size())
                cadenaAux += ", ";
            i++;
        }
        return cadenaAux + " }";
    }
}
