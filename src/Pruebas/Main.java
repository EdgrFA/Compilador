package Pruebas;

import Analizadores.*;
import Automatas.AFD;
import Automatas.AFNs;

/**
 *
 * @author Edgar Flores
 */
public class Main {
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        atm1(afns);//0
        atm2(afns);//1
        atm3(afns);//2
        atm4(afns);//3
        //atm5(afns);//4
        //atm6(afns);//5
        //atm7(afns);//6
        
        //Pruebas
        System.out.println("PRUEBA 0: "+afns.getAutomata(0).AnalizarCardena("SDDDD"));
        System.out.println("PRUEBA 1: "+afns.getAutomata(1).AnalizarCardena("SDDDD.D"));
        System.out.println("PRUEBA 2: "+afns.getAutomata(2).AnalizarCardena("LLDLDLDLD"));
        //System.out.println("PRUEBA 3: "+afns.getAutomata(3).AnalizarCardena("EEEE"));
        System.out.println("");
        //Convertir AFD
        AFD afd = new AFD(afns);
        afd.imprimirTablaTransiciones();
        //afd.algoritmoLEX("DDEEESDD.DELLDESDDESDD.D");
        
        AnalizadorLexico aL = new AnalizadorLexico("DDEEESDD.DELLDESDDESDD.D", afd);
        int token;
        while((token = aL.obtenerToken()) != 0){
            System.out.println("\nLexema: " + aL.getLexema());
            System.out.println("Token del Lexema: " + token);
        }
    }
    /* //Prueba en el salón
    public static void atm1(AFNs afns){
        afns.crearAFN('S');
        afns.operacionSigno(0);
        afns.crearAFN('D');
        afns.cerraduraSuma(1);
        afns.concatenar(0, 1);
        System.out.println(afns.getAutomata(0).getExpresionR());
        System.out.println(afns.getAutomata(0).getAlfabeto()+ "\n");
    }
    
    public static void atm2(AFNs afns){
        afns.crearAFN('S');
        afns.operacionSigno(1);
        afns.crearAFN('D');
        afns.cerraduraSuma(2);
        afns.concatenar(1, 2);
        afns.crearAFN('.');
        afns.concatenar(1, 2);
        afns.crearAFN('D');
        afns.cerraduraSuma(2);
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
        afns.crearAFN('E');
        afns.cerraduraSuma(3);
        System.out.println(afns.getAutomata(3).getExpresionR());
        System.out.println(afns.getAutomata(3).getAlfabeto()+ "\n");
    }

    public static void atm5(AFNs afns){         
        afns.crearAFN('+');
        System.out.println(afns.getAutomata(4).getExpresionR());
        System.out.println(afns.getAutomata(4).getAlfabeto()+ "\n");
    }

    public static void atm6(AFNs afns){         
        afns.crearAFN('-');
        System.out.println(afns.getAutomata(5).getExpresionR());
        System.out.println(afns.getAutomata(5).getAlfabeto()+ "\n");
    }

    public static void atm7(AFNs afns){         
        afns.crearAFN('+');
        afns.crearAFN('+');
        afns.concatenar(6, 7); 
        System.out.println(afns.getAutomata(6).getExpresionR());
        System.out.println(afns.getAutomata(6).getAlfabeto()+ "\n");
    }*/

    //S?|D+
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
        afns.union(3, 4);
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
    
}
