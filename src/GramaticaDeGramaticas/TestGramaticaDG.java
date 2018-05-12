package GramaticaDeGramaticas;

public class TestGramaticaDG {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica();
        GramaticaDeGramaticas gramaticaDeGramaticaLL1 = new GramaticaDeGramaticas(gramatica);
        String cadena = 
            /*
            "E -> T E';" +
            "E' -> + T E'| - T E'| \\e;"+
            "T -> F T' ;"+
            "T' -> * F T'| / F T'| \\e;"+
            "F -> ( E ) | num;";
            */
              
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
            
        boolean pasoLexico = gramaticaDeGramaticaLL1.analisisLexico(cadena);
        if(pasoLexico){
            System.out.println("\nEl Analisis Léxico fue correcto\n");
            boolean pasoSintactico = gramaticaDeGramaticaLL1.analisisSintactico(cadena, gramatica);
            if(pasoSintactico){
                System.out.println("Análisis Sintáctico Correcto");
                gramatica.imprimirSimbolos();
                gramatica.imprimirReglas();
            }else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
    }
}
