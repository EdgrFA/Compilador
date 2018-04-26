package GramaticaDeGramaticas;

import java.util.ArrayList;
import java.util.HashSet;

public class AnalizadorLL1 {
    private ArrayList<Nodo> reglas;
    private ArrayList<String> simbolosT; //Simbolos terminales
    private ArrayList<String> simbolosNT; // Simbolos no terminales
    private String[][] tablaLL_1;

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
        tablaLL_1 = new String[simbolosNT.size()][simbolosT.size()];
        for (int i = 0; i < simbolosNT.size(); i++)
            for (int j = 0; j < simbolosT.size(); j++)
                tablaLL_1[i][j] = "-";
        
        System.out.println("Tbla size: filas " + tablaLL_1.length);
        System.out.println("Tbla size: columnas " + tablaLL_1[0].length);
        
        //FIRSTS
        for (Nodo regla : reglas) {
            ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
            simbolos.add(regla.getSimbolo());
            HashSet<String> smblsFirst = first(simbolos, true);
            System.out.println("Simbolos First de " + regla.getStrSimbolo());
            for (String smbl : smblsFirst)
                System.out.print(smbl + " ");
            System.out.println("\n");
        }
        
        //FOLLOWS
        for (Nodo regla : reglas) {
            for (ArrayList<Simbolo> listaSimbs : regla.getListasSimbs()) {
                if(listaSimbs.get(0).getSimbolo().equals("\\e")){
                    HashSet<String> smblsFollow = follow(regla.getStrSimbolo(), new ArrayList(), true);
                    System.out.println("Simbolos Follow de: " + regla.getStrSimbolo());
                    for (String smbls : smblsFollow) 
                        System.out.print(smbls + " ");
                    System.out.println("\n");
                    continue;
                }
            }
        }
        
        imprimirTablaLL_1();
        
//        HashSet<String> smblsFollow = follow("Bf'", new ArrayList());
//        System.out.println("Simbolos Follow de: " + "Bf'");
//        for (String smbls : smblsFollow) 
//            System.out.print(smbls + " ");
//        System.out.println("\n");
    }
    
    private HashSet<String> first(ArrayList<Simbolo> simbolos, boolean esRaiz){
        HashSet<String> smbls = new HashSet();
        
        if (simbolos.get(0).equals("\\e") ){
            smbls.add("\\e");
            return smbls;
        }
        
        if(simbolos.get(0).esTerminal()){
            smbls.add(simbolos.get(0).getSimbolo());
            return smbls;
        }
        
        for (Nodo regla : reglas){
            if(regla.getStrSimbolo().equals(simbolos.get(0).getSimbolo())){
                for (ArrayList<Simbolo> listaSimb : regla.getListasSimbs()){
                    HashSet<String> smblsAux = first(listaSimb, false);
                    if(esRaiz){
                        
                        String cadSimbs = "";
                        for (Simbolo simbolo : listaSimb)
                            cadSimbs += simbolo.getSimbolo() + " ";
                        for (String smbl : smblsAux){
                            //System.out.println("FILAS: " + simbolosNT.indexOf(simbolos.get(0).getSimbolo()) + " simb: " + simbolos.get(0).getSimbolo());
                            //System.out.println("COLUMNAS: " + simbolosT.indexOf(smbl) + " simb: " + smbl);
                            if(!smbl.equals("\\e"))
                                tablaLL_1[simbolosNT.indexOf(simbolos.get(0).getSimbolo())][simbolosT.indexOf(smbl)] = cadSimbs;
                        }
                    }
                    smbls.addAll(smblsAux);
                }
            }
        }
        
        //TALVEZ FALTE GUARDAR LOS SIMBOLOS GUARDADOS POR EL SIGUIENTE FIRST
        if(smbls.contains("\\e")){
            smbls.remove("\\e");
            if(simbolos.size() > 1){
                ArrayList<Simbolo> smblsAux = new ArrayList<Simbolo>();
                for (int i = 1; i < simbolos.size(); i++)
                    smblsAux.add(simbolos.get(i));
                smbls.addAll(first(smblsAux, false));
                return smbls;
            }
        }
        return smbls;
    }
    
    private HashSet<String> follow(String simbolo, ArrayList histSimbs, boolean esRaiz){
        HashSet<String> smbls = new HashSet();
        histSimbs.add(simbolo);
        
        if(simbolo.equals(reglas.get(0).getStrSimbolo()))
            smbls.add("$");
        
        for (Nodo regla : reglas) {
            for (ArrayList<Simbolo> listaSimb : regla.getListasSimbs()) {
                for(int i = 0; i < listaSimb.size(); i++){
                    if(listaSimb.get(i).getSimbolo().equals(simbolo)){
                        if(i+1 < listaSimb.size()){
                            ArrayList<Simbolo> smblsAux = new ArrayList<Simbolo>();
                            smblsAux.add(listaSimb.get(i+1));
                            smbls.addAll(firstF(smblsAux));
                            if(smbls.contains("\\e")){
                                smbls.remove("\\e");
                                if(!histSimbs.contains(regla.getStrSimbolo()))
                                    smbls.addAll(follow(regla.getStrSimbolo(), histSimbs, false));
                            }
                        }else if(!histSimbs.contains(regla.getStrSimbolo()))
                            smbls.addAll(follow(regla.getStrSimbolo(), histSimbs, false));
                    }
                }
            }
        }
        if(esRaiz)
            for (String smbl : smbls)
                tablaLL_1[simbolosNT.indexOf(simbolo)][simbolosT.indexOf(smbl)] = "\\e";
        return smbls;
    }
    
    private HashSet<String> firstF(ArrayList<Simbolo> simbolos){
        HashSet<String> smbls = new HashSet();

        if (simbolos.get(0).equals("\\e") ){
            smbls.add("\\e");
            return smbls;
        }
        
        if(simbolos.get(0).esTerminal()){
            smbls.add(simbolos.get(0).getSimbolo());
            return smbls;
        }
        
        for (Nodo regla : reglas)
            if(regla.getStrSimbolo().equals(simbolos.get(0).getSimbolo()))
                for (ArrayList<Simbolo> listaSimb : regla.getListasSimbs())
                    smbls.addAll(firstF(listaSimb));
        
        return smbls;
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
    
    public void imprimirTablaLL_1(){
        System.out.print("X\t\t\t" );
        for (String simbolo : simbolosT)
            System.out.print(simbolo+"\t\t\t");
        System.out.println("");
        for (int i = 0; i < simbolosNT.size(); i++) {
            System.out.print(simbolosNT.get(i) +"\t\t\t");
            for (int j = 0; j < simbolosT.size(); j++)
                System.out.print(tablaLL_1[i][j]+"\t\t\t");
            System.out.println("");
        }
        System.out.println("\n");
    }
}
