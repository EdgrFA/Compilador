package LL1;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import GramaticaDeGramaticas.TokensGramatica;

public class AnalizadorSintacticoLL1 {
    private AnalizadorLexico lexic;
    private AFD afd;
    
    public AnalizadorSintacticoLL1(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, Gramatica gramatica){
        lexic = new AnalizadorLexico(cadena, afd);
        return G(gramatica);
    }
    
    boolean G(Gramatica gramatica){
        if(listaReglas(gramatica)){
            return true;
        }
        return false;
    }
    
    boolean listaReglas(Gramatica gramatica){
        if(regla( gramatica )){
            int tok = lexic.obtenerToken();
            if(tok == TokensGramatica.PC)
                if(listaReglasP(gramatica))
                    return true;
        }
        return false;
    }
    
    boolean listaReglasP(Gramatica gramatica){
        int x = lexic.getEdo();
        if(regla(gramatica)){
            int token = lexic.obtenerToken();
            if(token == TokensGramatica.PC)
                if(listaReglasP(gramatica))
                    return true;
            return false;
        }
        lexic.setEdo(x);
        return true;
    }

    boolean regla(Gramatica gramatica){
        SimboloNoTerminal ladoIzquierdo = new SimboloNoTerminal("");
        ladoIzquierdo.setTerminal(false);
        if(ladoIzquierdo(gramatica, ladoIzquierdo)){
            //*** Validación si existe el ladoIzquierdo
            int indice = gramatica.comprobarSimbolo(ladoIzquierdo);
            if(indice<0){
                SimboloInicial simboloIni = new SimboloInicial(ladoIzquierdo.getExpresion());
                simboloIni.setTerminal(false);
                ladoIzquierdo = (SimboloNoTerminal) simboloIni ;
                gramatica.agregarSimbolo(ladoIzquierdo);
            }else{
                ladoIzquierdo = gramatica.getSimbolo(indice);
                ladoIzquierdo.setTerminal(false);
            }
            int tok = lexic.obtenerToken();
            if(tok== TokensGramatica.FLECHA)
                if(listaLadosDerechos(gramatica, ladoIzquierdo ))
                    return true;
        }
        //if(!gramatica.getReglas().isEmpty())
            //gramatica.removerRegla(regla);
        return false;
    }

    boolean ladoIzquierdo(Gramatica gramatica, SimboloNoTerminal ladoIzquierdo){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            ladoIzquierdo.setExpresion(lexic.getLexema());
            return true;
        }
        return false;
    }

    boolean listaLadosDerechos(Gramatica gramatica, SimboloNoTerminal ladoIzquierdo){
        Regla regla = new Regla();
        gramatica.agregarRegla(regla);
        regla.setLadoIzquierdo(ladoIzquierdo);
        ladoIzquierdo.agregarReglaLadoDerecho(regla);
        if(ladoDerecho(gramatica, regla)){
            if(listaLadosDerechosP(gramatica, ladoIzquierdo))
                return true;
        }
        return false;
    }
    
    boolean listaLadosDerechosP(Gramatica gramatica, SimboloNoTerminal ladoIzquierdo){
        int tok = lexic.obtenerToken();
        System.out.println(ladoIzquierdo.getReglasLadosDerechos().get(0));
        if(tok == TokensGramatica.OR){
            Regla regla = new Regla();
            gramatica.agregarRegla(regla);
            regla.setLadoIzquierdo(ladoIzquierdo);
            ladoIzquierdo.agregarReglaLadoDerecho(regla);
            if(ladoDerecho(gramatica, regla))
                if(listaLadosDerechosP(gramatica, ladoIzquierdo))
                    return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
    
    boolean ladoDerecho(Gramatica gramatica,Regla regla){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){            
            SimboloNoTerminal simboloAux = new SimboloNoTerminal(lexic.getLexema());
            //Validación si ya existe un Simbolo
            int indice = gramatica.comprobarSimbolo(simboloAux);
            if(indice<0)
                gramatica.agregarSimbolo(simboloAux);
            else
                simboloAux = (SimboloNoTerminal)gramatica.getSimbolo(indice);
            regla.agregarSimbolo( simboloAux );
            if(ladoDerechoP( gramatica, regla ))
                return true;
        }else if(tok == TokensGramatica.EPSILON){
            regla.agregarSimbolo(new SimboloNoTerminal(lexic.getLexema()));
            return true;
        }
        return false;
    }
    
    boolean ladoDerechoP(Gramatica gramatica, Regla regla){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){           
            SimboloNoTerminal simboloAux = new SimboloNoTerminal(lexic.getLexema());
            //Validación si ya existe un Simbolo
            int indice = gramatica.comprobarSimbolo(simboloAux);
            if(indice<0)
                gramatica.agregarSimbolo(simboloAux);
            else
                simboloAux = gramatica.getSimbolo(indice);
            regla.agregarSimbolo( simboloAux );
            if(ladoDerechoP( gramatica, regla ))
                return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
}
