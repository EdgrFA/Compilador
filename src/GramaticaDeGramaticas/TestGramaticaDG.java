package GramaticaDeGramaticas;

public class TestGramaticaDG {
    public static String GRAMATICAS_PRUEBAS[] = {
        //Gramatica 1
        "E -> T E';" +
        "E' -> + T E'| - T E'| \\e;"+
        "T -> F T' ;"+
        "T' -> * F T'| / F T'| \\e;"+
        "F -> ( E ) | num;",
        
        //Gramatica 2
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
        "Lv -> true | false;"
    };
    
    public GramaticaDeGramaticas gramaticaDG;
    public Gramatica gramatica;
    
    public TestGramaticaDG(){
        gramatica = new Gramatica();
        gramaticaDG = new GramaticaDeGramaticas(gramatica);
    }
    
    public void pruebaLexYSint(String cadena, String expresion){    
        boolean pasoLexico = gramaticaDG.analisisLexico(cadena);
        if(pasoLexico){
            System.out.println("\nEl Analisis Léxico fue correcto\n");
            boolean pasoSintactico = gramaticaDG.analisisSintactico(cadena, gramatica);
            if(pasoSintactico){
                System.out.println("Análisis Sintáctico Correcto");
                validarCadena(expresion);
            }else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
    }
    
    public void validarCadena(String expresion){}
    
    public static void main(String[] args) {
        String cadena = TestGramaticaDG.GRAMATICAS_PRUEBAS[0];
        
        TestGramaticaDG test = new TestGramaticaDG();
        test.pruebaLexYSint(cadena, "");
    }
}
