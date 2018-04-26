package GramaticaDeGramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import java.util.ArrayList;

public class AnalizadorSintacGramaticas {
    private AnalizadorLexico lexic;
    private final AFD afd;
    
    public AnalizadorSintacGramaticas(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, Gramatica reglas){
        lexic = new AnalizadorLexico(cadena, afd);
        return G(reglas);
    }
    
    boolean G(Gramatica reglas){
        if(listaReglas(reglas))
            return true;
        return false;
    }
    
    boolean listaReglas(Gramatica reglas){
        if(regla(reglas)){
            int tok = lexic.obtenerToken();
            if(tok == TokensGramatica.PC)
                if(listaReglasP(reglas))
                    return true;
        }
        return false;
    }
    
    boolean listaReglasP(Gramatica reglas){
        int x = lexic.getEdo();
        if(regla(reglas)){
            int token = lexic.obtenerToken();
            if(token == TokensGramatica.PC)
                if(listaReglasP(reglas))
                    return true;
            return false;
        }
        lexic.setEdo(x);
        return true;
    }

    boolean regla(Gramatica reglas){
        Nodo regla = reglas.agregarRegla();
        if(ladoIzquierdo(regla)){
            int tok = lexic.obtenerToken();
            if(tok== TokensGramatica.FLECHA)
                if(listaLadosDerechos(regla))
                    return true;
        }
        if(!reglas.getReglas().isEmpty())
            reglas.removerRegla(regla);
        return false;
    }

    boolean ladoIzquierdo(Nodo regla){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            regla.setSimbolo(lexic.getLexema());
            return true;
        }
        return false;
    }

    boolean listaLadosDerechos(Nodo regla){
        ArrayList<String> listaSimbs = regla.agregarListaSimbs();
        if(ladoDerecho(listaSimbs))
            if(listaLadosDerechosP(regla))
                return true;
        return false;
    }
    
    boolean listaLadosDerechosP(Nodo regla){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.OR){
            ArrayList<String> listaSimbs = regla.agregarListaSimbs();
            if(ladoDerecho(listaSimbs))
                if(listaLadosDerechosP(regla))
                    return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
    
    boolean ladoDerecho(ArrayList<String> listaSimbs){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            listaSimbs.add(lexic.getLexema());
            if(ladoDerechoP(listaSimbs))
                return true;
        }
        return false;
    }
    
    boolean ladoDerechoP(ArrayList<String> listaSimbs){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            listaSimbs.add(lexic.getLexema());
            if(ladoDerechoP(listaSimbs))
                return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
}