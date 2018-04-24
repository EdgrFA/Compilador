package GramaticaDeGramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import java.util.ArrayList;

public class AnalizadorSintacGramaticas {
    private AnalizadorLexico lexic;
    private AFD afd;
    
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
        //System.out.println("-*listaReglas");
        if(regla(reglas)){
            int tok = lexic.obtenerToken();
            if(tok == TokensGramatica.PC)
                if(listaReglasP(reglas))
                    return true;
        }
        return false;
    }
    
    boolean listaReglasP(Gramatica reglas){
        //System.out.println("-**listaReglasP'");
        int x = lexic.getEdo();
        //System.out.println("Valor de X con "+x);
        if(regla(reglas)){
            int token = lexic.obtenerToken();
            if(token == TokensGramatica.PC)
                if(listaReglasP(reglas))
                    return true;
            return false;
        }
        //System.out.println("Asigne X con "+x);
        lexic.setEdo(x);
        return true;
    }

    boolean regla(Gramatica reglas){
        //System.out.println("-*Regla");
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
        //System.out.println("-*LadoIzquierdo");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            regla.setSimbolo(lexic.getLexema());
            return true;
        }
        return false;
    }

    boolean listaLadosDerechos(Nodo regla){
        //System.out.println("-*ListaLadosDerechos");
        ArrayList<String> listaSimbs = regla.agregarListaSimbs();
        if(ladoDerecho(listaSimbs))
            if(listaLadosDerechosP(regla))
                return true;
        return false;
    }
    
    boolean listaLadosDerechosP(Nodo regla){
        //System.out.println("-**listaLadosDerechosP'");
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
        //System.out.println("-*LadoDerecho");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            listaSimbs.add(lexic.getLexema());
            if(ladoDerechoP(listaSimbs))
                return true;
        }
        return false;
    }
    
    boolean ladoDerechoP(ArrayList<String> listaSimbs){
        //System.out.println("-**LadoDerechoP'");
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
