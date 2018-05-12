package utilidades;

import java.util.ArrayList;

public abstract class Tabla {
    private int filas;
    private int columnas;

    public Tabla(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    abstract public void imprimirFila(Object[] elementoFila);
    abstract public void imprimirEncabezado( Object[] elementoEncabezado);
    abstract public void imprimirTabla(String[] encabezado, String[][]elementos);
}