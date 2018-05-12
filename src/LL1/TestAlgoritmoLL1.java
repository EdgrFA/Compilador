package LL1;

public class TestAlgoritmoLL1 {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica();
        GramaticaLL1 gramaticaDeGramaticaLL1 = new GramaticaLL1(gramatica);
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
                gramaticaDeGramaticaLL1.algoritmoLL1(gramatica);
                //String expresion = "( num + num ) * num - num $";
                String expresion = "true ^ false $";
                boolean cadenaValida = gramaticaDeGramaticaLL1.analizarCadenaLL1(expresion);
                if(cadenaValida)
                    System.out.println("Cadena Aceptada");
                else
                    System.out.println("ERROR cadena NO aceptada");
                /*
                for(;;){
                    boolean salir = false;
                    if(salir)
                        break;
                }*/
            }else
                System.out.println("ERROR SINTACTICO");
        }else
            System.out.println("ERROR Léxico");
    }
}
