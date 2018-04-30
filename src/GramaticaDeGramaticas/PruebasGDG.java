package GramaticaDeGramaticas;

public class PruebasGDG {
    
    public static void main(String[] args) {
        GramaticaDeGramaticas gDG = new GramaticaDeGramaticas();
        /*String cadena = "G -> ListaReglas ;"
                + "ListaReglas -> Regla Pc ListaReglas' ;"
                + "ListaReglas' -> Regla Pc ListaReglas' |\\e  ;"
                + "Regla -> LadoIzquierdo FLECHA ListaLadosDerechos ;"
                + "LadoIzquierdo           ->           SIMBOLO  ;"
                + "ListaLadosDerechos -> LadoDerecho ListaLadosDerechos'           ;"
                + "ListaLadosDerechos' -> OR LadoDerecho ListaLadosDerechos'           |\\e;"
                + "LadoDerecho -> SIMBOLO LadoDerecho'             ;"
                + "LadoDerecho'      -> SIMBOLO LadoDerecho' |\\e;"; */
        
        String cadena = "Sb -> I Sb';" +
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

//"E -> T E' ;E' -> + T E' |- T E' ;"; //"E->TE';E'->+TE'|-TE'|e;T->FT';T'->*FT'|/FT'|e;F->(E)|num;" ;
        boolean paso = gDG.analisisLexico(cadena);
        if(paso){
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
}
