
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class Calculadora {
    
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        afnNum(afns);       //0
        afns.crearAFN('+'); //1
        afns.crearAFN('-'); //2
        afns.crearAFN('*'); //3
        afns.crearAFN('/'); //4
        afns.crearAFN('('); //5
        afns.crearAFN(')'); //6     
        afnSEN(afns);       //7
        afnCOS(afns);       //8
        afnTAN(afns);       //9
        afns.crearAFN('^'); //10
        afnEXP(afns);       //11
        afnLN(afns);        //12
        afnLOG(afns);       //13
        afnRAIZ(afns);      //14
        
        AFD afd = new AFD(afns);
        
        Tokens.NUM   = afns.getTokenAFN(0);
        Tokens.SUMA  = afns.getTokenAFN(1);
        Tokens.RESTA = afns.getTokenAFN(2);
        Tokens.PROD  = afns.getTokenAFN(3);
        Tokens.DIV   = afns.getTokenAFN(4);
        Tokens.PAR_I = afns.getTokenAFN(5);
        Tokens.PAR_D = afns.getTokenAFN(6);
        Tokens.SIN   = afns.getTokenAFN(7);
        Tokens.COS   = afns.getTokenAFN(8);
        Tokens.TAN   = afns.getTokenAFN(9);
        Tokens.POT   = afns.getTokenAFN(10);
        Tokens.EXP   = afns.getTokenAFN(11);
        Tokens.LN    = afns.getTokenAFN(12);
        Tokens.LOG   = afns.getTokenAFN(13);
        Tokens.RAIZ   = afns.getTokenAFN(14);
        
        Tokens.TokenInfo();
        afd.imprimirTablaTransiciones();
        
        AnalizadorLexico al = new AnalizadorLexico("5.5+(4-2)", afd);
        
        AnalizadorSintactico as = new AnalizadorSintactico(afd);
        //as.AnalizarCadena("70*(4+6)+40*SIN(90)+10^4");
        boolean salida = as.AnalizarCadena("(10^4*e^(2)+15)/10000+27SQRT3");
        System.out.println("\nLa salida fue "+salida);
        System.out.println(as.getResultado());
    }

    //NUM -> 0
    public static void afnNum(AFNs afns){
        afns.crearAFN('0', '9'); //0
        afns.cerraduraSuma(0);
        afns.crearAFN('.'); //1
        afns.crearAFN('0', '9'); //2
        afns.cerraduraSuma(2);
        afns.concatenar(1, 2);
        afns.operacionSigno(1);
        afns.concatenar(0, 1);
    }

    public static void afnSEN(AFNs afns){
        afns.crearAFN('S'); //7
        afns.crearAFN('I'); //8
        afns.crearAFN('N'); //9
        afns.concatenar(8, 9);
        afns.concatenar(7, 8);
    }
    
    public static void afnCOS(AFNs afns){
        afns.crearAFN('C'); //8
        afns.crearAFN('O'); //9
        afns.crearAFN('S'); //10
        afns.concatenar(9, 10);
        afns.concatenar(8, 9);
    }
    
    public static void afnTAN(AFNs afns){
        afns.crearAFN('T'); //9
        afns.crearAFN('A'); //10
        afns.crearAFN('N'); //11
        afns.concatenar(10, 11);
        afns.concatenar(9, 10);
    }
    
    public static void afnEXP(AFNs afns){
        afns.crearAFN('e'); //11
        afns.crearAFN('^'); //12
        afns.concatenar(11, 12);
    }
    
    public static void afnLN(AFNs afns){
        afns.crearAFN('L'); //12
        afns.crearAFN('N'); //13
        afns.concatenar(12, 13);
    }
    
    public static void afnLOG(AFNs afns){
        afns.crearAFN('L'); //13
        afns.crearAFN('O'); //14
        afns.crearAFN('G'); //15
        afns.concatenar(14, 15);
        afns.concatenar(13, 14);
    }
    
    public static void afnRAIZ(AFNs afns){
        afns.crearAFN('S'); //14
        afns.crearAFN('Q'); //15
        afns.crearAFN('R'); //16
        afns.crearAFN('T'); //17
        afns.concatenar(16, 17);
        afns.concatenar(15, 16);
        afns.concatenar(14, 15);
    }
}