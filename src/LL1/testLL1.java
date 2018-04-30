package LL1;

public class testLL1 {
    public static void main(String[] args) {
        GramaDeGramaLL1 gDG = new GramaDeGramaLL1();        
        String cadena = 
            
            "E -> T E';" +
            "E' -> + T E'| - T E'| \\e;"+
            "T -> F T' ;"+
            "T' -> * F T'| / F T'| \\e;"+
            "F -> ( E ) | num;";
            
            /*    
            "Sb -> I Sb';" +
            "Sb' -> /// I Sb' | \\e;" +
            "I -> Bt I';" +
            "I' -> c Bt I' | \\e;" +
            "Bt -> Bf Bt';" +
            "Bt' -> v Bf Bt' | \\e;" +
            "Bf -> Bs Bf';" +
            "Bf' -> ^ Bs Bf' | \\e;" +
            "Bs -> Bp | r Bp;" +
            "Bp -> Lv | i | ( Sb );" +
            "Lv -> true | false;";
        */
        boolean pasoLexico = gDG.analisisLexico(cadena);
        if(pasoLexico){
            System.out.println("\nEl Analisis Lexico fue correcto\n");
            boolean pasoSintactico = gDG.analisisSintactico(cadena);
            if(pasoSintactico)
                System.out.println("Análisis Sintáctico Correcto");
            else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
    }
}
