
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class Calculadora {
    
    public static void main(String[] args) {
        AFNs afns = new AFNs();
        atmNum(afns); //0
        afns.crearAFN('+'); //1
        afns.crearAFN('-'); //2
        afns.crearAFN('*'); //3
        afns.crearAFN('/'); //4
        afns.crearAFN('('); //5
        afns.crearAFN(')'); //6
        AFD afd = new AFD(afns);
        
        Tokens.NUM = afns.getTokenAFN(0);
        Tokens.SUM = afns.getTokenAFN(1);
        Tokens.REST = afns.getTokenAFN(2);
        Tokens.PROD = afns.getTokenAFN(3);
        Tokens.DIV = afns.getTokenAFN(4);
        Tokens.PAR_I = afns.getTokenAFN(5);
        Tokens.PAR_D = afns.getTokenAFN(6);
        
        Tokens.TokenInfo();
        afd.imprimirTablaTransiciones();
        
        
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
}
