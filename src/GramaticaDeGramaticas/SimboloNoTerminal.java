package GramaticaDeGramaticas;

import java.util.ArrayList;
import java.util.HashMap;

public class SimboloNoTerminal extends Simbolo{
    private ArrayList<Regla> reglasLadosDerecho;
    private HashMap<SimboloNoTerminal,Regla> relacion;
    
    public SimboloNoTerminal(String expresion){
        super(expresion);
        reglasLadosDerecho = new ArrayList<>();
        relacion = new HashMap<>();
    }
    
    public void agregarReglaLadoDerecho(Regla regla){
        reglasLadosDerecho.add(regla);
    }
    
    public void agregarRelacion(SimboloNoTerminal nuevaRelacion, Regla regla){
        relacion.put(nuevaRelacion,regla);
    }
    
    public ArrayList<Regla> getReglasLadosDerechos() {
        return reglasLadosDerecho;
    }
    
    public HashMap<SimboloNoTerminal,Regla> getRelacion(){
        return relacion;
    }

}
