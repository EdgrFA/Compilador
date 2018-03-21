
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class Calculadora {
    
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        atmNum(afns);       //0
        afns.crearAFN('+'); //1
        afns.crearAFN('-'); //2
        afns.crearAFN('*'); //3
        afns.crearAFN('/'); //4
        afns.crearAFN('('); //5
        afns.crearAFN(')'); //6     
        atmSEN(afns);       //7
        atmCOS(afns);       //8
        atmTAN(afns);       //9
        afns.crearAFN('^'); //10
        atmEXP(afns);       //11
        atmLN(afns);        //12
        atmLOG(afns);       //13
        
        AFD afd = new AFD(afns);
        
        Tokens.NUM   = afns.getTokenAFN(0);
        Tokens.SUM   = afns.getTokenAFN(1);
        Tokens.REST  = afns.getTokenAFN(2);
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
        
        Tokens.TokenInfo();
        afd.imprimirTablaTransiciones();
        
        AnalizadorLexico al = new AnalizadorLexico("5.5+(4-2)*LOG(e^5)", afd);
    }

    //NUM -> 0
    public static void atmNum(AFNs afns){
        afns.crearAFN('0', '9'); //0
        afns.cerraduraSuma(0);
        afns.crearAFN('.'); //1
        afns.crearAFN('0', '9'); //2
        afns.cerraduraSuma(2);
        afns.concatenar(1, 2);
        afns.operacionSigno(1);
        afns.concatenar(0, 1);
    }

    public static void atmSEN(AFNs afns){
        afns.crearAFN('S'); //7
        afns.crearAFN('I'); //8
        afns.crearAFN('N'); //9
        afns.concatenar(8, 9);
        afns.concatenar(7, 8);
    }
    
    public static void atmCOS(AFNs afns){
        afns.crearAFN('C'); //8
        afns.crearAFN('O'); //9
        afns.crearAFN('S'); //10
        afns.concatenar(9, 10);
        afns.concatenar(8, 9);
    }
    
    public static void atmTAN(AFNs afns){
        afns.crearAFN('T'); //9
        afns.crearAFN('A'); //10
        afns.crearAFN('N'); //11
        afns.concatenar(10, 11);
        afns.concatenar(9, 10);
    }
    
    public static void atmEXP(AFNs afns){
        afns.crearAFN('e'); //11
        afns.crearAFN('^'); //12
        afns.concatenar(11, 12);
    }
    
    public static void atmLN(AFNs afns){
        afns.crearAFN('L'); //12
        afns.crearAFN('N'); //13
        afns.concatenar(12, 13);
    }
    
    public static void atmLOG(AFNs afns){
        afns.crearAFN('L'); //13
        afns.crearAFN('O'); //14
        afns.crearAFN('G'); //15
        afns.concatenar(14, 15);
        afns.concatenar(13, 14);
    }
    
}