package utilidades;

import java.util.ArrayList;

public class TablaColumnaUnitaria extends Tabla{
    private int tamColumna;
    
    public TablaColumnaUnitaria(int tamColumna) {
        super(0, 0);
        this.tamColumna = tamColumna;
    }
    
    public void imprimirFila(Object[] elementoFila) {
        String filasSeparacion = "";
        ArrayList<String> filaElementos = new ArrayList<>();
        for (int i = 0; i < elementoFila.length ; i++) {
            int tamIzquierda = tamColumna - elementoFila[i].toString().length();
            filasSeparacion += "  %s %"+tamIzquierda+"s";
            filaElementos.add(elementoFila[i].toString());
            filaElementos.add("|");
        }  
        System.out.println(String.format(filasSeparacion, filaElementos.toArray()));
    }
    
    public void imprimirEncabezado(Object[] elementoEncabezado) {
        imprimirFila(elementoEncabezado);
        String linea = "";
        int tamLinea = tamColumna * elementoEncabezado.length ;
        for (int i = 0; i < tamLinea ; i++)
            linea+="-";
        System.out.println(linea);
    }

    @Override
    public void imprimirFila(ArrayList<String> elementoFila) {
        String filasSeparacion = "";
        ArrayList<String> filaElementos = new ArrayList<>();
        for (int i = 0; i < elementoFila.size() ; i++) {
            int tamIzquierda = tamColumna - elementoFila.get(i).length();
            filasSeparacion += "  %s %"+tamIzquierda+"s";
            filaElementos.add(elementoFila.get(i));
            filaElementos.add("|");
        }  
        System.out.println(String.format(filasSeparacion, filaElementos.toArray()));
    }

    @Override
    public void imprimirEncabezado(ArrayList<String> elementoEncabezado) {
        imprimirFila(elementoEncabezado);
        String linea = "";
        int tamLinea = tamColumna * elementoEncabezado.size();
        for (int i = 0; i < tamLinea ; i++)
            linea+="-";
        System.out.println(linea);
    }
    
    @Override
    public void imprimirTabla(String[] encabezado, String[][] elementos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
