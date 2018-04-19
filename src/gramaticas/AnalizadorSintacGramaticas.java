
package gramaticas;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class AnalizadorSintacGramaticas {
    private AnalizadorLexico lexic;
    private AFD afd;
    private int indexAfns;
    
    public AnalizadorSintacGramaticas(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, AFNs afns){
        lexic = new AnalizadorLexico(cadena, afd);
        indexAfns = -1;
        boolean isOk = G();
        int tok = lexic.obtenerToken();
        return isOk && (tok == TokensGramatica.FIN);
    }
    
    boolean G(){
        if(listaReglas())
            return true;
        return false;
    }
    
    boolean listaReglas(){
        if(regla()){
            int tok = lexic.obtenerToken();
            if(tok == TokensGramatica.PC)
                if(listaReglasP())
                    return true;
        }
        return false;
    }
    
    boolean listaReglasP(){
        int x = lexic.getEdo();
        if(regla()){
            int token = lexic.obtenerToken();
            if(token == TokensGramatica.PC)
                if(listaReglasP())
                    return true;
            return false;
        }
        lexic.setEdo(x);
        return true;
    }
    
    boolean regla(){
        if(ladoIzquierdo()){
            int tok = lexic.obtenerToken();
            if(tok== TokensGramatica.FLECHA)
                if(listaLadosDerechos())
                    return true;
        }
        return false;
    }
    
    boolean ladoIzquierdo(){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB)
            return true;
        return false;
    }
    
    boolean listaLadosDerechos(){
        if(ladoDerecho())
            if(listaLadosDerechosP())
                return true;
        return false;
    }
    
    boolean listaLadosDerechosP(){
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
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB)
            if(ladoDerechoP())
                return true;
        return false;
    }
    
    boolean ladoDerechoP(){
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
