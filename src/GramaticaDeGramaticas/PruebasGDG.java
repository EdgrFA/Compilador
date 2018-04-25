package GramaticaDeGramaticas;

public class PruebasGDG {
    
    public static void main(String[] args) {
        GramaticaDeGramaticas gDG = new GramaticaDeGramaticas();
        String cadena = "G -> ListaReglas ;"
                + "ListaReglas -> Regla Pc ListaReglas' ;"
                + "ListaReglas' -> Regla Pc ListaReglas' |e  ;"
                + "Regla -> LadoIzquierdo FLECHA ListaLadosDerechos ;"
                + "LadoIzquierdo           ->           SIMBOLO  ;"
                + "ListaLadosDerechos -> LadoDerecho ListaLadosDerechos'           ;"
                + "ListaLadosDerechos' -> OR LadoDerecho ListaLadosDerechos'           |e;"
                + "LadoDerecho -> SIMBOLO LadoDerecho'             ;"
                + "LadoDerecho'      -> SIMBOLO LadoDerecho' |e;"; //"E -> T E' ;E' -> + T E' |- T E' ;"; //"E->TE';E'->+TE'|-TE'|e;T->FT';T'->*FT'|/FT'|e;F->(E)|num;" ;
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
