package utilidades;

import LL1.Simbolo;
import java.util.ArrayList;

public class TestTabla {
    public static void main(String[] args) {
        TablaColumnaUnitaria tabla = new TablaColumnaUnitaria(40);
        ArrayList<String> filaElementos = new ArrayList<>();
        ArrayList<String> filaElementos2 = new ArrayList<>();
        filaElementos.add( "Hola" );
        filaElementos.add( "ko42" );
        filaElementos.add( "ase" );
        filaElementos.add( "cachorro" );
        tabla.imprimirEncabezado(filaElementos.toArray() );
        filaElementos2.add( "asdasdasd" );
        filaElementos2.add( "asdasdasdsjhjjkhkhjkhkhjjs" );
        filaElementos2.add( "asadsadasdasdas" );
        filaElementos2.add( "cachorrosddss" );
        tabla.imprimirFila( filaElementos2.toArray() );
        String cadena = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(cadena.length());
    }
}
