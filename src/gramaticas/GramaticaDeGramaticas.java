package gramaticas;

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
        afnFlecha(afns);//2
        afnSIMB(afns); //3
        System.out.println('\\'); //15
        afd = new AFD(afns);
        
        //Asignación de los Tokens
        TokensGramatica.PC   = afns.getTokenAFN(0);
        TokensGramatica.OR  = afns.getTokenAFN(1);
        TokensGramatica.FLECHA = afns.getTokenAFN(2);
        TokensGramatica.SIMB  = afns.getTokenAFN(3);
        
        TokensGramatica.TokenInfo();
        
        afd.imprimirTablaTransiciones();
        as = new AnalizadorSintacGramaticas(afd);
    }
    
    public boolean evaluarLexico(String expresion){
        AnalizadorLexico analizadorLexico = new AnalizadorLexico(expresion, afd);
        int token;
        while((token = analizadorLexico.obtenerToken()) != 0){
            if(token == -1){
                return false;
            }
            System.out.println("Token= "+token);
        }
        return true;
    }
    
    public boolean evaluarSintactico(String expresion){
        return as.AnalizarCadena(expresion);
    }
    
    public static void main(String[] args) {
        System.out.println((char)32 +"->"+ (char)123); //15
        for(int i = 32; i != 124 ; i++ )
            System.out.println((char)i);
        System.out.println((char)125); //16
        
        GramaticaDeGramaticas gdg = new GramaticaDeGramaticas();
        String cadena = "E->TE';E'->+TE'|-TE'|e;"; //"E->TE';E'->+TE'|-TE'|e;T->FT';T'->*FT'|/FT'|e;F->(E)|num;" ;
        boolean paso = gdg.evaluarLexico(cadena);
        if(paso){
            System.out.println("El Analisis Lexico fue correcto ");
            for(int i=0; i<cadena.length(); i++){
                System.out.println(cadena.substring(i, i+1)+" | "+i);
            }
            boolean pasoSintactico = gdg.evaluarSintactico(cadena);
            if(pasoSintactico)
                System.out.println("Paso Sintactico");
            else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
        
    }
    
    /***************************************************************************
    /***************    OPERACIONES CALCULADORA   ****************************/
    
    private static void afnSIMB(AFNs afns){
        afns.crearAFN((char)32,(char)58); //3
        afns.crearAFN((char)60,(char)123); //4
        afns.crearAFN((char)125); //5
        System.out.println("RIFE XD");
        afns.union(4, 5);
        afns.union(3, 4); // -> 3
        System.out.println("RIFE XD");    
    }

    private static void afnFlecha(AFNs afns){
        afns.crearAFN('-'); //3
        afns.crearAFN('>'); //4
        System.out.println('-'); //3
        System.out.println('>'); //4
        afns.concatenar(2, 3);
    }
}
