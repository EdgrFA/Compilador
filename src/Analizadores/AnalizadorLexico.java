
package Analizadores;

import Automatas.AFD;
import Automatas.ConjuntoEstados;

public class AnalizadorLexico {
    private String cadena;
    private ConjuntoEstados edoInicial;
    private ConjuntoEstados edoActual;
    private boolean edoAceptPrevio;
    private int indiceIniLex; //Indice del inicio del Lexema
    private int indiceActual;
    private char carActual; //Se puede quitar
    private int valorToken;
    private String lexema;

    public AnalizadorLexico(String cadena, AFD afd) {
        this.cadena = cadena;
        edoInicial = afd.getConjuntoInicial();
        edoActual = edoInicial;
        edoAceptPrevio = edoActual.isAceptacion();
        indiceIniLex = 0;
        indiceActual = 0;
    }
    
    public int obtenerToken(){
        if(indiceActual >= cadena.length())
            return 0;
        for(indiceActual = indiceIniLex; indiceActual < cadena.length(); indiceActual++){
            carActual = cadena.charAt(indiceActual);
            edoActual = edoActual.buscarTransicion(carActual);
            if(edoActual != null){
                if(edoActual.isAceptacion()){
                    edoAceptPrevio = true;
                    valorToken = edoActual.getToken();
                }
            }else{
                if(edoAceptPrevio){
                    lexema = cadena.substring(indiceIniLex, indiceActual);
                    edoAceptPrevio = false;
                    edoActual = edoInicial; //conjuntosEdos.get(idEstadoPrev);
                    indiceIniLex = indiceActual;
                    return valorToken;
                }else{
                    //Dos casos, ninguna transición con caracter o el caracter no esta dentro del alfabeto
                    System.out.println("\nNo existe transicion con el caracter \""+cadena.charAt(indiceActual)+"\"");
                    edoActual = edoInicial;
                    indiceIniLex++;
                }
            }
        }
        if(edoAceptPrevio){
            lexema = cadena.substring(indiceIniLex, indiceActual);
            edoAceptPrevio = false;
            edoActual = edoInicial;
            indiceIniLex = indiceActual;
            return valorToken;
        }
        return 0;
    }

    public String getLexema() {
        return lexema;
    }
}