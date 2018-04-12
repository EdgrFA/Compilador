package ExpresionesRegulares;

import Automatas.AFD;
import Automatas.AFNs;

public class ExpresionesRegulares {
    //private final AFD afd;
    //private final AnalizadorSintacticoER as;
    
    
    
    public ExpresionesRegulares() {
        AFNs afns = new AFNs();
        
        afns.crearAFN('|'); //0
        afns.crearAFN('&'); //1
        afns.crearAFN('+'); //2
        afns.crearAFN('*'); //3
        afns.crearAFN('?'); //4
        afns.crearAFN('('); //5
        afns.crearAFN(')'); //6
        afns.crearAFN('['); //7
        afns.crearAFN(']'); //8
        afnSIMB(afns);
        
        TokensER.UNION = afns.getTokenAFN(0);
        TokensER.CONC = afns.getTokenAFN(1);
        TokensER.CERR_SUM = afns.getTokenAFN(2);
        TokensER.CERR_ASTER = afns.getTokenAFN(3);
        TokensER.CERR_SIGNO = afns.getTokenAFN(4);
        TokensER.PAR_I = afns.getTokenAFN(5);
        TokensER.PAR_D = afns.getTokenAFN(6);
        TokensER.CORCH_I = afns.getTokenAFN(7);
        TokensER.CORCH_D = afns.getTokenAFN(8);
        TokensER.SIMB = afns.getTokenAFN(9);
    }

    //SIMB -> 9
    public static void afnSIMB(AFNs afns){
        afns.crearAFN((char)32, (char)37); //9
        afns.crearAFN((char)39); //10
        afns.crearAFN((char)44, (char)46); //11
        afns.crearAFN((char)48, (char)62); //12
        afns.crearAFN((char)64, (char)90); //13
        afns.crearAFN((char)92); //14
        afns.crearAFN((char)94, (char)123); //15
        afns.crearAFN((char)125, (char)126); //16
        afns.union(15, 16); 
        afns.union(14, 15);
        afns.union(13, 14);
        afns.union(12, 13);
        afns.union(11, 12);
        afns.union(10, 11);
        afns.union(9, 10); // -> 9
        //Simbolos especiales
        afns.crearAFN('/'); //10*
        afns.crearAFN('+'); //11
        afns.crearAFN('&'); //12
        afns.crearAFN('?'); //13
        afns.crearAFN('*'); //14
        afns.crearAFN('('); //15
        afns.crearAFN(')'); //16
        afns.crearAFN('['); //17
        afns.crearAFN(']'); //18
        afns.crearAFN('/'); //19
        //Union de todos los simbolos especiales
        afns.union(18, 19); 
        afns.union(17, 18);
        afns.union(16, 17);
        afns.union(15, 16);
        afns.union(14, 15);
        afns.union(13, 14);
        afns.union(12, 13);
        afns.union(11, 12); // -> 11 
        //Concatenarlos con '/'
        afns.concatenar(10, 11);
        //Unir todos los simbolos imprimibles
        afns.union(9, 10);    
    }

    public static void main(String[] args) {
    }
}
