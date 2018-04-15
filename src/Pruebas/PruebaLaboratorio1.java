/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Analizadores.*;
import Automatas.AFD;
import Automatas.AFNs;
import static Pruebas.Main.atm1;
import static Pruebas.Main.atm2;
import static Pruebas.Main.atm3;
import static Pruebas.Main.atm4;

/**
 *
 * @author Edgar Flores
 */
public class PruebaLaboratorio1 {
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        atm1(afns);//0 - S?ºD±
        atm2(afns);//1 - S?ºD±º.ºD±
        atm3(afns);//2 - Lº(L|D)¤
        atm4(afns);//3 - E±
        atm5(afns);//4 - D±º.ºD±ºLº(L|D)±
        
        AFD afd = new AFD(afns);
        afd.imprimirTablaTransiciones();
        
        AnalizadorLexico aL = new AnalizadorLexico("SDDEED.DLDLESDD.DDELLDD.D", afd);
        int token;
        while((token = aL.obtenerToken()) != 0){
            System.out.println("\nLexema: " + aL.getLexema());
            System.out.println("Token del Lexema: " + token);
        }
    }
    
    public static void atm1(AFNs afns){
        afns.crearAFN('S'); //0
        afns.operacionSigno(0);
        afns.crearAFN('D'); //1
        afns.cerraduraSuma(1);
        afns.concatenar(0, 1);
        System.out.println(afns.getAutomata(0).getExpresionR());
        System.out.println(afns.getAutomata(0).getAlfabeto()+ "\n");
    }
    
    public static void atm2(AFNs afns){
        afns.crearAFN('S'); //1
        afns.operacionSigno(1);
        afns.crearAFN('D'); //2
        afns.cerraduraSuma(2);
        afns.crearAFN('.'); //3
        afns.crearAFN('D'); //4
        afns.cerraduraSuma(4);
        afns.concatenar(1, 2);
        afns.concatenar(1, 2);
        afns.concatenar(1, 2);
        System.out.println(afns.getAutomata(1).getExpresionR());
        System.out.println(afns.getAutomata(1).getAlfabeto()+ "\n");
    }
    
    public static void atm3(AFNs afns){
        afns.crearAFN('L'); //2
        afns.crearAFN('L'); //3
        afns.crearAFN('D'); //4
        afns.union(3,4);
        afns.cerraduraAsterisco(3);
        afns.concatenar(2, 3);
        System.out.println(afns.getAutomata(2).getExpresionR());
        System.out.println(afns.getAutomata(2).getAlfabeto()+ "\n");
    }
    
    public static void atm4(AFNs afns){         
        afns.crearAFN('E'); //3
        afns.cerraduraSuma(3);
        System.out.println(afns.getAutomata(3).getExpresionR());
        System.out.println(afns.getAutomata(3).getAlfabeto()+ "\n");
    }

    public static void atm5(AFNs afns){         
        afns.crearAFN('D'); //4
        afns.crearAFN('.'); //5
        afns.crearAFN('D'); //6
        afns.crearAFN('L'); //7
        afns.crearAFN('L'); //8
        afns.crearAFN('D'); //9
        afns.union(8,9);
        afns.cerraduraSuma(8);
        afns.concatenar(7, 8);
        afns.cerraduraSuma(6);
        afns.concatenar(6, 7);
        afns.concatenar(5, 6);
        afns.cerraduraSuma(4);
        afns.concatenar(4, 5);
        System.out.println(afns.getAutomata(4).getExpresionR());
        System.out.println(afns.getAutomata(4).getAlfabeto()+ "\n");
    }
}
