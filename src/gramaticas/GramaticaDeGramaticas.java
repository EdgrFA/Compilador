package gramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class GramaticaDeGramaticas {
    private final AFD afd;
    private final AnalizadorSintacGramaticas as;
    
    public GramaticaDeGramaticas() {
        AFNs afns = new AFNs();

        System.out.println(';'); //0
        System.out.println('|'); //2 
        afnFlecha(afns);       //7
        //afnCOS(afns);       //8
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
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println((char)32 +"->"+ (char)37); //9
        System.out.println((char)39); //10
        System.out.println((char)44+"->"+ (char)46); //11
        System.out.println((char)48+"->"+ (char)62); //12
        System.out.println((char)64+"->"+ (char)90); //13
        System.out.println((char)92); //14
        System.out.println((char)94+"->"+ (char)123); //15
        System.out.println((char)125); //16
        
        /*
        Calculadora cal = new Calculadora();
        boolean paso = cal.evaluarLexico("\\-15+10");
        if(paso){
            System.out.println("El Analisis Lexico fue correcto ");
        }else
            System.out.println("ERROR Léxico");
        */
    }
    
    /***************************************************************************
    /***************    OPERACIONES CALCULADORA   ****************************/
    
    private static void afnSIMB(AFNs afns){
        afns.crearAFN((char)32, (char)37); //9
        afns.crearAFN((char)39); //10
        afns.crearAFN((char)44, (char)46); //11
        afns.crearAFN((char)48, (char)62); //12
        afns.crearAFN((char)64, (char)90); //13
        afns.crearAFN((char)92); //14
        afns.crearAFN((char)94, (char)123); //15
        afns.crearAFN((char)125); //16
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
        afns.crearAFN('~'); //20
        //Union de todos los simbolos especiales
        afns.union(19, 20); 
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

    private static void afnFlecha(AFNs afns){
        System.out.println('-'); //7
        System.out.println('>'); //8
        afns.concatenar(8, 9);
        afns.concatenar(7, 8);
    }
}
