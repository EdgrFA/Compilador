package LR0;

import LR1.EstadoLR;
import LR1.ReglaLR;
import GramaticaDeGramaticas.Gramatica;
import GramaticaDeGramaticas.Regla;
import GramaticaDeGramaticas.Simbolo;
import GramaticaDeGramaticas.SimboloNoTerminal;
import LL1.Follow;
import java.util.ArrayList;
import java.util.HashMap;

public class AlgoritmoLR0 {
    private EstadoLR estadoInicial;
    private ArrayList<EstadoLR> conjuntoEdos;
    
    private ArrayList<SimboloNoTerminal> simbolosTerminales;
    private ArrayList<SimboloNoTerminal> simbolosNoTerminales;
    private HashMap<SimboloNoTerminal, Follow> simboloFollowPrevio;
    private static String PUNTO = "\\.";
    private Gramatica gramatica;

    public AlgoritmoLR0(Gramatica gramatica) {
        this.gramatica = gramatica;
        simboloFollowPrevio = new HashMap<>();
        simbolosTerminales = new ArrayList<>();
        simbolosNoTerminales = new ArrayList<>();
        calcularFollow();
        
        ArrayList<ReglaLR> reglasIni = new ArrayList<>();
        reglasIni.add(new ReglaLR(gramatica.getListaReglas().get(0)));
        reglasIni = opCerradura(reglasIni);
        
        for(Regla regla: reglasIni){
            System.out.println(regla+ "\t\t"+ regla.getNumeroRegla());
        }
    }
    
    private void calcularFollow(){
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                Follow follow = new  Follow( gramatica.getSimbolo(i), gramatica.getListaReglas() );
                simboloFollowPrevio.put(gramatica.getSimbolo(i), follow);
                System.out.println("follow ("+gramatica.getSimbolo(i)+") = "+follow.getSimbolos() + "\n\n");
            }
        }
    }
    
    private void ir_A(){
        
    }
    
    private ArrayList<ReglaLR> opCerradura(ArrayList<ReglaLR> reglas){
        ArrayList<ReglaLR> newReglas = new ArrayList<>();
        newReglas.addAll(reglas);
        
        for (int i = 0; i < newReglas.size(); i++) {
            Simbolo simbolo = newReglas.get(i).getListaLadosDerechos().get(newReglas.get(i).getIndicePunto());
            if(simbolo instanceof SimboloNoTerminal){
                for (Regla reglaGr : gramatica.getListaReglas()) {
                    if(reglaGr.equals(newReglas.get(i))){
                        System.out.println("continuo");
                        continue;
                    
                    }
                    if(reglaGr.getLadoIzquierdo().getExpresion().equals(simbolo.getExpresion()))
                        newReglas.add(new ReglaLR(reglaGr));
                }
            }
        }
        return newReglas;
    }
    
    public void generarTablaLR0(){
        
    }
}
