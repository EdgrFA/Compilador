package utilidades;

import java.util.ArrayList;

public class TablaColumnaUnitaria extends Tabla{
    private final int tamColumna;
    
    public TablaColumnaUnitaria(int tamColumna) {
        super(0, 0);
        this.tamColumna = tamColumna;
    }
    
    public void imprimirFila(Object[] elementoFila1, Object[] elementoFila2 ) {
        StringBuilder filaFormato = new StringBuilder("");
        ArrayList<Object> filaElementos = new ArrayList<>();
        for (Object elementoFila : elementoFila1) {
            int tamIzquierda = tamColumna - elementoFila.toString().length();
            filaFormato.append("  %s %").append(tamIzquierda).append('s');
            filaElementos.add(elementoFila);
            filaElementos.add("|");
        }
        for (Object elementoFila : elementoFila2) {
            int tamIzquierda = tamColumna - elementoFila.toString().length();
            filaFormato.append("  %s %").append(tamIzquierda).append('s');
            filaElementos.add(elementoFila);
            filaElementos.add("|");
        } 
        System.out.println(String.format(filaFormato.toString(), filaElementos.toArray()));
    }
    
    public void imprimirEncabezado(Object[] encabezado1,Object[] encabezado2) {
        imprimirFila(encabezado1,encabezado2);
        StringBuilder lineaSeparacion = new StringBuilder();
        int tamLinea = tamColumna * (encabezado1.length + encabezado2.length) ;
        for (int i = 0; i < tamLinea ; i++)
            lineaSeparacion.append("-");
        System.out.println(lineaSeparacion);
    }
    
    
    @Override
    public void imprimirFila(Object[] elementoFila) {
        StringBuilder filaFormato = new StringBuilder("");
        ArrayList<Object> filaElementos = new ArrayList<>();
        for (Object elementoFila1 : elementoFila) {
            int tamIzquierda = tamColumna - elementoFila1.toString().length();
            filaFormato.append("  %s %").append(tamIzquierda).append('s');
            filaElementos.add(elementoFila1);
            filaElementos.add("|");
        }  
        System.out.println(String.format(filaFormato.toString(), filaElementos.toArray()));
    }
    
    @Override
    public void imprimirEncabezado(Object[] elementoEncabezado) {
        imprimirFila(elementoEncabezado);
        StringBuilder lineaSeparacion = new StringBuilder();
        int tamLinea = tamColumna * elementoEncabezado.length ;
        for (int i = 0; i < tamLinea ; i++)
            lineaSeparacion.append("-");
        System.out.println(lineaSeparacion);
    }

    @Override
    public void imprimirTabla(String[] encabezado, String[][] elementos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
