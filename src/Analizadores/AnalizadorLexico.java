
package Analizadores;

import Automatas.AFD;
import Automatas.ConjuntoEstados;
import javax.swing.JOptionPane;

public class AnalizadorLexico {
    private String cadena;
    private ConjuntoEstados edoInicial;
    private ConjuntoEstados edoActual;
    private int EdoActual;
    private int indiceActual;
    private boolean edoAceptPrevio;
    private int indiceTokenAnterior;
    private int indiceIniLex; //Indice del inicio del Lexema
    private int valorToken;
    private String lexema;

    /**
     * Constructor del Analizador Lexico.
     * @param cadena : Cadena que sera analizada lexicamente.
     * @param afd : AFD con el que sera analizado la cadena.
     */
    public AnalizadorLexico(String cadena, AFD afd) {
        this.cadena = cadena;
        edoInicial = afd.getConjuntoInicial();
        edoActual = edoInicial;
        edoAceptPrevio = edoActual.isAceptacion();
        indiceIniLex = 0;
        indiceActual = 0;
        indiceTokenAnterior = 0;
    }
    
    public int obtenerToken(){
        for(indiceActual = indiceIniLex; indiceActual < cadena.length(); indiceActual++){
            char carActual = cadena.charAt(indiceActual);
            //Buscar Transicion de estados con el caracter actual.
            edoActual = edoActual.buscarTransicion(carActual);
            if(edoActual != null){
                if(edoActual.isAceptacion()){
                    edoAceptPrevio = true;
                    valorToken = edoActual.getToken();
                }
            }else{
                //Revisar si se encontro un estado de aceptacion previamente
                if(edoAceptPrevio){
                    lexema = cadena.substring(indiceIniLex, indiceActual);
                    dropSpaces(); //Omitir espacios
                    edoAceptPrevio = false;
                    edoActual = edoInicial; //conjuntosEdos.get(idEstadoPrev);
                    indiceTokenAnterior = indiceIniLex;
                    indiceIniLex = indiceActual;
                    return valorToken;
                }else{
                    //Dos casos, ninguna transición con el caracter o el caracter no esta dentro del alfabeto
                    //System.out.println("\nNo existe transicion con el caracter \""+cadena.charAt(indiceActual)+"\"");
                    JOptionPane.showMessageDialog(null,"Error Lexico\nNo existe transicion con el caracter \""+cadena.charAt(indiceActual)+"\"");
                    edoActual = edoInicial;
                    indiceIniLex++;
                    return -1;
                }
            }
        }
        if(edoAceptPrevio){
            lexema = cadena.substring(indiceIniLex, indiceActual);
            dropSpaces();
            edoAceptPrevio = false;
            edoActual = edoInicial;
            indiceTokenAnterior = indiceIniLex;
            indiceIniLex = indiceActual;
            indiceActual++;
            return valorToken;
        }
        lexema = "";
        indiceTokenAnterior = indiceIniLex;
        indiceIniLex = indiceActual;
        valorToken = 0;
        return valorToken;
    }

    public int getTokenActual() {
        return valorToken;
    }

    public String getLexema() {
        return lexema;
    }
    
    public int getEdo(){
        return indiceActual;
    }
    
    public void setEdo(int indiceEdoPrevio){
        indiceActual = indiceEdoPrevio;
        indiceIniLex = indiceEdoPrevio;
    }
    
    //Regresar a las posiciones del anterior Token
    public void regresarToken(){
        indiceActual = indiceTokenAnterior;
        indiceIniLex = indiceTokenAnterior;
    }
    
    /**
     * Omitir los espacios entre lexemas. 
     */
    private void dropSpaces(){
        char carActual;
        if(indiceActual < cadena.length())
            carActual = cadena.charAt(indiceActual);
        else
            return;
        while(carActual == ' '){
            indiceActual++;
            if(indiceActual < cadena.length())
                carActual = cadena.charAt(indiceActual);
            else
                break;
        }
    }
}