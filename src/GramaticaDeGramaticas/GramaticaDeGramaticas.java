package GramaticaDeGramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class GramaticaDeGramaticas {
    private final AFD afd;
    private final AnalizadorSintacGramaticas as;
    
    public GramaticaDeGramaticas() {
        AFNs afns = new AFNs();

        afns.crearAFN(';'); //0
        afns.crearAFN('|'); //1
        afnFlecha(afns);    //2
        afnSIMB(afns);      //3        
        afd = new AFD(afns);
        
        //Asignación de los Tokens
        TokensGramatica.PC     = afns.getTokenAFN(0);
        TokensGramatica.OR     = afns.getTokenAFN(1);
        TokensGramatica.FLECHA = afns.getTokenAFN(2);
        TokensGramatica.SIMB   = afns.getTokenAFN(3);
        
        TokensGramatica.TokenInfo();
        
        afd.imprimirTablaTransiciones();
        as = new AnalizadorSintacGramaticas(afd);
    }
    
    public boolean analisisLexico(String expresion){
        AnalizadorLexico analizadorLexico = new AnalizadorLexico(expresion, afd);
        int token;
        while((token = analizadorLexico.obtenerToken()) != 0){
            if(token == -1)
                return false;
            System.out.println("Token = " + token + ", Lexema : " + analizadorLexico.getLexema());
        }
        return true;
    }
    
    public boolean analisisSintactico(String expresion){
        Gramatica gramatica = new Gramatica();
        boolean ok = as.AnalizarCadena(expresion, gramatica);
        gramatica.imprimirGramatica();
        return ok;
    }
    
    /***************************************************************************
    /***************    SIMBOLOS ESPECIALES   ****************************/
    
    private static void afnSIMB(AFNs afns){
        afns.crearAFN((char)33,(char)58); //3
        afns.crearAFN((char)63,(char)123); //4
        afns.crearAFN((char)125); //5
        
        afns.union(4, 5);
        afns.union(3, 4); // -> 3
        afns.cerraduraSuma(3); // (a-zA-z1-9)+
        
        afns.crearAFN('\''); //4  '
        afns.cerraduraAsterisco(4); // (')*
        afns.concatenar(3, 4); // -> (a-zA-z1-9)+&(')*
    }

    private static void afnFlecha(AFNs afns){
        afns.crearAFN('-'); //2
        afns.crearAFN('>'); //3
        afns.concatenar(2, 3); //2
    }
}
