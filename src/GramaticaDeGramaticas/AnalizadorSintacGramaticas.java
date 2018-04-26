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
<<<<<<< HEAD
=======
       // System.out.println("-**listaReglasP'");
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
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
<<<<<<< HEAD
=======
       // System.out.println("-*Regla");
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
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
<<<<<<< HEAD
=======
      //  System.out.println("-*LadoIzquierdo");
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            regla.setSimbolo(lexic.getLexema());
            return true;
        }
        return false;
    }

    boolean listaLadosDerechos(Nodo regla){
<<<<<<< HEAD
        ArrayList<String> listaSimbs = regla.agregarListaSimbs();
=======
       // System.out.println("-*ListaLadosDerechos");
        ArrayList<Simbolo> listaSimbs = regla.agregarListaSimbs();
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
        if(ladoDerecho(listaSimbs))
            if(listaLadosDerechosP(regla))
                return true;
        return false;
    }
    
    //Aqui se agrego el caracter especial epsilon
    boolean listaLadosDerechosP(Nodo regla){
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.OR){
            ArrayList<Simbolo> listaSimbs = regla.agregarListaSimbs();
            if(ladoDerecho(listaSimbs))
                if(listaLadosDerechosP(regla))
                    return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
    
<<<<<<< HEAD
    boolean ladoDerecho(ArrayList<String> listaSimbs){
=======
        boolean ladoDerecho(ArrayList<Simbolo> listaSimbs){
        //System.out.println("-*LadoDerecho");
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            listaSimbs.add(new Simbolo(lexic.getLexema()));
            if(ladoDerechoP(listaSimbs))
                return true;
        }else if(tok == TokensGramatica.EPSILON){
            listaSimbs.add(new Simbolo(lexic.getLexema()));
            return true;
        }
        return false;
    }
    
<<<<<<< HEAD
    boolean ladoDerechoP(ArrayList<String> listaSimbs){
=======
    boolean ladoDerechoP(ArrayList<Simbolo> listaSimbs){
        //System.out.println("-**LadoDerechoP'");
>>>>>>> 3a1c408c6d9c80c359724914cb0c8b70ccb4c216
        int tok = lexic.obtenerToken();
        if(tok == TokensGramatica.SIMB){
            listaSimbs.add(new Simbolo(lexic.getLexema()));
            if(ladoDerechoP(listaSimbs))
                return true;
            return false;
        }
        lexic.regresarToken();
        return true;
    }
}