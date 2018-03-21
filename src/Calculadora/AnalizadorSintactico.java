
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;

public class AnalizadorSintactico {
    private AnalizadorLexico Lexic;
    private AFD afd;

    public AnalizadorSintactico(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, FloatM v){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(v);
    }
    
    public boolean E(FloatM v){
        if(T(v))
            if(Ep(v))
                return true;
        return false;
    }
    
    public boolean Ep(FloatM v){
        int tok;
        FloatM v1 = new FloatM(0.0f);
        tok = Lexic.obtenerToken();
        if(tok == Tokens.SUM || tok == Tokens.REST){
            if(T(v1)){
                if(tok == Tokens.SUM)
                    v.suma(v, v1);
                else
                    v.resta(v, v1);
                if(Ep(v))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean T(FloatM v){
        if(F(v))
            if(Tp(v))
                return true;
        return false;
    }
    
    public boolean Tp(FloatM v){
        int tok=0;
        FloatM v1 = new FloatM(0.0f);
        tok = Lexic.obtenerToken();
        if(tok == Tokens.PROD || tok == Tokens.DIV){
            if(F(v1)){
                if(tok == Tokens.PROD)
                    v.producto(v, v1);
                else
                    v.division(v, v1);
                if(Tp(v))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean F(FloatM v){
        int tok;
        tok = Lexic.obtenerToken();
        if (tok == Tokens.PAR_I) {
            if(E(v)){
                tok = Lexic.obtenerToken();
                if(tok == Tokens.PAR_D)
                    return true;
            }
        }else if(tok == Tokens.NUM){
            v = new FloatM(Float.parseFloat(Lexic.getLexema()));
            return true;
        }else if(tok == Tokens.SIN || tok == Tokens.COS || tok == Tokens.TAN || tok == Tokens.EXP || tok == Tokens.LN || tok == Tokens.LOG){
            if(E(v)){
                //PONER OPERACIONES para "v"
                if(tok == Tokens.SIN){
                    
                }else if(tok == Tokens.COS){

                }else if(tok == Tokens.TAN){

                }else if(tok == Tokens.EXP){

                }else if(tok == Tokens.LN){

                }else if(tok == Tokens.LOG){

                }
                return true;
            }
        }
        return false;
    }
    
    public static void cambiar(FloatM v ){

    }
    
    public static void main(String[] args) {
        FloatM v = new FloatM(1.5f);
        FloatM v1 = new FloatM(1/4);
        
        System.out.println(v);
    } 
    
}
