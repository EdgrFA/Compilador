package LL1;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;
import GramaticaDeGramaticas.TokensGramatica;
import java.util.ArrayList;

public class GramaDeGramaLL1 {
    private final AFD afd;
    private final AnalizadorSintacticoLL1 as;
    
    public GramaDeGramaLL1() {
        AFNs afns = new AFNs();

        afns.crearAFN(';'); //0
        afns.crearAFN('|'); //1
        afnFlecha(afns);    //2
        afnSIMB(afns);      //3       
        afnEPSILON(afns);   //4
        
        afd = new AFD(afns);
        
        //Asignación de los Tokens
        TokensGramatica.PC     = afns.getTokenAFN(0);
        TokensGramatica.OR     = afns.getTokenAFN(1);
        TokensGramatica.FLECHA = afns.getTokenAFN(2);
        TokensGramatica.SIMB   = afns.getTokenAFN(3);
        TokensGramatica.EPSILON   = afns.getTokenAFN(4);
        
        TokensGramatica.TokenInfo();
        
        afd.imprimirTablaTransiciones();
        as = new AnalizadorSintacticoLL1(afd);
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
        System.out.println("************* REGLAS ***************");
        gramatica.imprimirReglas();
        System.out.println("************* SIMBOLOS *************");
        gramatica.imprimirSimbolos();
        System.out.println("\n*********** FIRST ****************");
        AnalizadorLL1 ll = new AnalizadorLL1(gramatica);
        //ll.calcularFirstReglas();
        ll.calcularFirstSNT();
        ll.imprimirFirst();
        System.out.println("\n*********** FOLLOW ****************");
        ll.calcularFollow();
        
        /*
        for (int i = 0; i < gramatica.getNumeroSimbolos() ; i++) {
            if(!gramatica.getSimbolo(i).isTerminal()){
                System.out.print("follow( "+ gramatica.getSimbolo(i) +" ) = ");
                //simbolosR = ll.first( gramatica.getListaReglas().get(i).getListaLadosDerechos() );
                simbolosFollow = ll.follow( gramatica.getSimbolo(i) );
                System.out.println( simbolosFollow );
            }
        }
        */
        System.out.println();
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

    private static void afnEPSILON(AFNs afns){
        afns.crearAFN('\\'); //4
        afns.crearAFN('e'); //5
        afns.crearAFN('E'); //6
        afns.union(5, 6);
        afns.concatenar(4, 5); //2
    }
}
