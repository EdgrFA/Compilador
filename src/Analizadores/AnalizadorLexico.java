
package Analizadores;

import Automatas.ConjuntoEstados;

public class AnalizadorLexico {
    private String cadena;
    private ConjuntoEstados edoInicial;
    private ConjuntoEstados edoActual;
    private boolean edoAceptPrevio;
    private int indiceIniLex; //Indice del inicio del Lexema
    private int indiceActual;
    private char carActual;
    private int valorToken;
    private String lexema;
    //Poner caracter actual

    public AnalizadorLexico(String cadena, ConjuntoEstados conjuntoInicial) {
        this.cadena = cadena;
        edoInicial = conjuntoInicial;
        edoActual = conjuntoInicial;
        edoAceptPrevio = edoActual.isAceptacion();
        indiceIniLex = 0;
        indiceActual = 0;
    }
    
    public int obtenerToken(){
        System.out.println("");
        if(indiceActual >= cadena.length())
            return 0;
        for(indiceActual = indiceIniLex; indiceActual < cadena.length(); indiceActual++){ //indiceActual++ agregar esta wea
            System.out.println("Leyendo: " + cadena.charAt(indiceActual) + ", en indice: " + indiceActual);
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
                    System.out.println("No existe transicion con el caracter \""+cadena.charAt(indiceActual)+"\"");
                    //return -1;
                    edoActual = edoInicial;
                }
            }
        }
        
        System.out.println("FIN DE CADENA");
        
        if(edoAceptPrevio){
            lexema = cadena.substring(indiceIniLex, indiceActual);
            edoAceptPrevio = false;
            edoActual = edoInicial; //conjuntosEdos.get(idEstadoPrev);
            indiceIniLex = indiceActual;
            return valorToken;
        }
//        lexema = cadena.substring(indiceIniLex, indiceActual);
//        System.out.println("Lexema: "+lexema);
//        System.out.println("Token: "+ valorToken);
//        return valorToken;
        return 0;
    }

    public String getLexema() {
        return lexema;
    }
}