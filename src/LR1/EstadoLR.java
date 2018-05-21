package LR1;

import GramaticaDeGramaticas.Simbolo;
import java.util.ArrayList;
import java.util.HashMap;

public class EstadoLR {
    private static int idGlobal = 0;
    private final int id;
    private boolean analizado;
    private ArrayList<ItemLR> itemsLR;
    private HashMap<Simbolo, EstadoLR> derivacionesMap;
    //private HashMap<Simbolo, EstadoLR> reduccionesMap;
    //Conjunto de reghlas con puntos
    
    public EstadoLR(ArrayList<ItemLR> itemsLR) {
        this.id = idGlobal++;
        this.analizado = false;
        this.itemsLR = itemsLR;
        this.derivacionesMap = new HashMap<>();
//        this.reduccionesMap = new HashMap<>();
    }
    
    public void crearDerivacion(Simbolo simbolo, EstadoLR estadoDestino){
        derivacionesMap.put(simbolo, estadoDestino);
    }
    
//    public void crearReduccion(Simbolo simbolo, EstadoLR estadoDestino){
//        reduccionesMap.put(simbolo, estadoDestino);
//    }

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
