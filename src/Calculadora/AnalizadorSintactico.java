
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;

public class AnalizadorSintactico {
    private AnalizadorLexico Lexic;
    private final AFD afd;
    private DoubleM resultado;

    public AnalizadorSintactico(AFD afd) {
        this.afd = afd;
        resultado = new DoubleM(1.0);
    }
    
    public boolean AnalizarCadena(String cadena, DoubleM v){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(v);
    }
    
    public boolean AnalizarCadena(String cadena){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(resultado);
    }
    
    public boolean E(DoubleM v){
        if(T(v))
            if(Ep(v))
                return true;
        return false;
    }
    
    public boolean Ep(DoubleM v){
        int tok;
        DoubleM v1 = new DoubleM(1.0);
        tok = Lexic.obtenerToken();
        if(tok == Tokens.SUMA || tok == Tokens.RESTA){
            if(T(v1)){
                if(tok == Tokens.SUMA)
                    v.suma(v1);
                else
                    v.resta(v1);
                if(Ep(v))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean T(DoubleM v){
        if(F(v))
            if(Tp(v))
                return true;
        return false;
    }
    
    public boolean Tp(DoubleM v){
        int tok=0;
        DoubleM v1 = new DoubleM(1.0);
        tok = Lexic.obtenerToken();
        if(tok == Tokens.PROD || tok == Tokens.DIV){
            if(F(v1)){
                if(tok == Tokens.PROD)
                    v.producto(v1);
                else
                    v.division(v1);
                if(Tp(v))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean F(DoubleM v){
        int tok;
        tok = Lexic.obtenerToken();
        System.out.println("Token: "+tok);
        if (tok == Tokens.PAR_I) {
            if(E(v)){
                tok = Lexic.obtenerToken();
                if(tok == Tokens.PAR_D)
                    return true;
            }
        }else if(tok == Tokens.NUM){
            v.setValor(Double.parseDouble(Lexic.getLexema()));
            System.out.println("Lexema: "+Lexic.getLexema());
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
    
    public static void cambiar(DoubleM v ){

    }

    public double getResultado() {
        return resultado.getValor();
    }
    
    public static void main(String[] args) {
        DoubleM v = new DoubleM(1.5);
        DoubleM v1 = new DoubleM(1);
        v.ln();
        
        
        System.out.println(v.getValor());
    } 
    
}
