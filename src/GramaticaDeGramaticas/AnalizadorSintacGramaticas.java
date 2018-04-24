package GramaticaDeGramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;
import GramaticaDeGramaticas.TokensGramatica;

public class AnalizadorSintacGramaticas {
    private AnalizadorLexico lexic;
    private AFD afd;
    
    public AnalizadorSintacGramaticas(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena){
        lexic = new AnalizadorLexico(cadena, afd);
        return G();
        /*
        boolean isOk = G();
        int tok = lexic.obtenerToken();
        return isOk && (tok == TokensGramatica.FIN);
        */
    }
    
    boolean G(){
        if(listaReglas())
            return true;
        return false;
    }
    
    boolean listaReglas(){
        //System.out.println("-*listaReglas");
        if(regla()){
            int tok = lexic.obtenerToken();
            if(tok == TokensGramatica.PC)
                if(listaReglasP())
                    return true;
        }
        return false;
    }
    
    boolean listaReglasP(){
        //System.out.println("-**listaReglasP'");
        int x = lexic.getEdo();
        //System.out.println("Valor de X con "+x);
        if(regla()){
            int token = lexic.obtenerToken();
            if(token == TokensGramatica.PC)
                if(listaReglasP())
                    return true;
            return false;
        }
        //System.out.println("Asigne X con "+x);
        lexic.setEdo(x);
        return true;
    }
    
    boolean regla(){
        //System.out.println("-*Regla");
        if(ladoIzquierdo()){
            int tok = lexic.obtenerToken();
            if(tok== TokensGramatica.FLECHA)
                if(listaLadosDerechos())
                    return true;
        }
        return false;
    }
    
    boolean ladoIzquierdo(){
        //System.out.println("-*LadoIzquierdo");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB)
            return true;
        return false;
    }
    
    boolean listaLadosDerechos(){
        //System.out.println("-*ListaLadosDerechos");
        if(ladoDerecho())
            if(listaLadosDerechosP())
                return true;
        return false;
    }
    
    boolean listaLadosDerechosP(){
        //System.out.println("-**listaLadosDerechosP'");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.OR){
            if(ladoDerecho())
                if(listaLadosDerechosP())
                    return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
    
    boolean ladoDerecho(){
        //System.out.println("-*LadoDerecho");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB)
            if(ladoDerechoP())
                return true;
        return false;
    }
    
    boolean ladoDerechoP(){
        //System.out.println("-**LadoDerechoP'");
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            if(ladoDerechoP())
                return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
}
