package GramaticaDeGramaticas;

import java.util.ArrayList;
import java.util.HashSet;

public class AnalizadorLL1 {
    private ArrayList<Nodo> reglas;
    private ArrayList<String> simbolosT; //Simbolos terminales
    private ArrayList<String> simbolosNT; // Simbolos no terminales

    public AnalizadorLL1(Gramatica gramatica) {
        this.reglas = gramatica.getReglas();
        simbolosNT = new ArrayList<>();
        simbolosT = new ArrayList<>();
        //Obtener Simbolos No Terminales
        for (Nodo regla: reglas)
            simbolosNT.add(regla.getSimbolo().getSimbolo());
        //Obtener Simbolos Terminales
        for (Nodo regla: gramatica.getReglas()){
            for (ArrayList<Simbolo> simbolos : regla.getListasSimbs()){
                for (Simbolo simbolo : simbolos){
                    if(!simbolosNT.contains(simbolo.getSimbolo())){
                        simbolo.setEsTerminal(true);
                        if(!simbolosT.contains(simbolo.getSimbolo()))
                            simbolosT.add(simbolo.getSimbolo());
                    }
                }
            }
        }
        simbolosT.add("$");
        simbolosT.remove("\\e");
        
        /*for (Nodo regla : reglas) {
            ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
            simbolos.add(regla.getSimbolo());
            HashSet<String> smblsFirst = first(simbolos);
            System.out.println("Simbolos First de " + regla.getStrSimbolo());
            for (String smbl : smblsFirst)
                System.out.print(smbl + " ");
            System.out.println("\n");
        }*/
    }
    
    public void getSimbolosInfo(){
        System.out.print("Simbolos Terminales: {");
        for (String simbolo : simbolosT) 
            System.out.print(simbolo + " ");
        System.out.println("}");
        System.out.print("Simbolos No Terminales: {");
        for (String simbolo : simbolosNT) 
            System.out.print(simbolo + " ");
        System.out.println("}\n");
    }
    
    private HashSet<String> first(ArrayList<Simbolo> simbolos){
        System.out.print("First(");
        for (Simbolo simbolo : simbolos)
            System.out.print(simbolo.getSimbolo() + " ");
        System.out.println(")");
        
        
        HashSet<String> smbls = new HashSet();
        
        if (simbolos.get(0).equals("\\e") ){
            smbls.add("\\e");
            return smbls;
        }
        if(simbolos.get(0).esTerminal()){
            smbls.add(simbolos.get(0).getSimbolo());
            return smbls;
        }
        
        for (Nodo regla : reglas) {
            if(regla.getStrSimbolo().equals(simbolos.get(0).getSimbolo())){
                for (ArrayList<Simbolo> listaSimb : regla.getListasSimbs()) {
                    smbls.addAll(first(listaSimb));
                }
            }
        }
        
        if(smbls.contains("\\e")){
            smbls.remove("\\e");
            if(simbolos.size() > 1){
                ArrayList<Simbolo> smblsAux = new ArrayList<Simbolo>();
                for (int i = 1; i < simbolos.size(); i++)
                    smblsAux.add(simbolos.get(i));
                smbls.addAll(first(smblsAux));
                return smbls;
            }
        }
        return smbls;
    }
    
    private void follow(){
        
    }
    
    
    
}
