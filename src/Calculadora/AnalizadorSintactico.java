
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;

public class AnalizadorSintactico {
    private AnalizadorLexico Lexic;
    private final AFD afd;
    private DoubleM resultado;
    private StringM prefijo;
    private StringM posfijo;
    private int tipoExpresion;

    public AnalizadorSintactico(AFD afd) {
        this.afd = afd;
        resultado = new DoubleM(1.0);
        tipoExpresion = 0;
    }
    
    public boolean AnalizarCadena(String cadena, StringM v, int tipoExpresion){
        Lexic = new AnalizadorLexico(cadena, afd);
        this.tipoExpresion = tipoExpresion;
        return E(v);
    }
    
    public boolean AnalizarCadena(String cadena, DoubleM v){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(v);
    }
    
    public boolean AnalizarCadena(String cadena){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(resultado);
    }
    
    // *************************************************************************
    public boolean E(DoubleM v, StringM pre, StringM pos){
        System.out.println("E");
        if(T(v,pre,pos))
            if(Ep(v,pre,pos))
                return true;
        return false;
    }
    
    public boolean Ep(DoubleM v, StringM pre, StringM pos){
        System.out.println("Ep");
        int tok;
        DoubleM v1 = new DoubleM(1.0);
        StringM pre1 = new StringM("");
        StringM pos1 = new StringM("");
        tok = Lexic.obtenerToken();
        System.out.println("Mi Token: "+tok);
        if(tok == Tokens.SUMA || tok == Tokens.RESTA){
            if(T(v1,pre1,pos1)){
                if(tok == Tokens.SUMA)
                    v.suma(v1);
                else
                    v.resta(v1);
                if(Ep(v,pre,pos)){
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean T(DoubleM v, StringM pre, StringM pos){
        System.out.println("T");
        if(R(v,pre,pos))
            if(Tp(v,pre,pos))
                return true;
        return false;
    }
    
    public boolean Tp(DoubleM v, StringM pre, StringM pos){
        System.out.println("Tp");
        int tok;
        DoubleM v1 = new DoubleM(1.0);
        StringM pre1 = new StringM("");
        StringM pos1 = new StringM("");
        tok = Lexic.obtenerToken();
        if(tok == Tokens.PROD || tok == Tokens.DIV){
            if(R(v1,pre1,pos1)){
                if(tok == Tokens.PROD)
                    v.producto(v1);
                else
                    v.division(v1);
                if(Tp(v,pre,pos))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean R(DoubleM v, StringM pre, StringM pos){
        System.out.println("R");
        if(F(v,pre,pos))
            if(Rp(v,pre,pos))
                return true;
        return false;
    }
    
    public boolean Rp(DoubleM v, StringM pre, StringM pos){
        System.out.println("Rp");
        int tok;
        DoubleM v1 = new DoubleM(1.0);
        tok = Lexic.obtenerToken();
        if(tok == Tokens.POT || tok == Tokens.RAIZ){
            if(F(v1,pre,pos)){
                if(tok == Tokens.POT)
                    v.potencia(v1);
                else
                    v.raiz(v1);
                if(Rp(v,pre,pos))
                    return true;
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean F(DoubleM v, StringM pre, StringM pos){
        int tok;
        tok = Lexic.obtenerToken();
        System.out.println("Token: "+tok);
        System.out.print("F -> ");
        if (tok == Tokens.PAR_I) {
            if(E(v,pre,pos)){
                tok = Lexic.obtenerToken();
                if(tok == Tokens.PAR_D)
                    return true;
            }
        }else if(tok == Tokens.NUM){
            v.setValor(Double.parseDouble(Lexic.getLexema()));
            System.out.println("Lexema: "+Lexic.getLexema());
            return true;
        }else if(tok == Tokens.SIN || tok == Tokens.COS || tok == Tokens.TAN ||
                tok == Tokens.POT ||  tok == Tokens.EXP || tok == Tokens.LN || tok == Tokens.LOG){
            //PONER OPERACIONES para "v"
            int AuxTok = tok;
            tok = Lexic.obtenerToken();
            if (tok == Tokens.PAR_I) {
                if(E(v,pre,pos)){
                    tok = Lexic.obtenerToken();
                    if(AuxTok == Tokens.SIN){
                        v.sin();
                    }else if(AuxTok == Tokens.COS){
                        v.cos();
                    }else if(AuxTok == Tokens.TAN){
                        v.tan();
                    }else if(AuxTok == Tokens.POT){
                        v.potencia(v);
                    }else if(AuxTok == Tokens.EXP){
                        v.exponencial();
                    }else if(AuxTok == Tokens.LN){
                        v.ln();
                    }else if(AuxTok == Tokens.LOG){
                        v.log();
                    }
                    if(tok == Tokens.PAR_D)
                        return true;
                    return false;
                }
            }
            
        }
        return false;
    }
    
    // ********************** EXPRESIONES **************************************
    
    public boolean E(StringM v){
        System.out.println("E");
        if(T(v))
            if(Ep(v))
                return true;
        return false;
    }
    
    public boolean Ep(StringM v){
        System.out.println("Ep");
        int tok;
        StringM v1 = new StringM("");
        tok = Lexic.obtenerToken();
        System.out.println("Mi Token: "+tok);
        if(tok == Tokens.SUMA || tok == Tokens.RESTA){
            if(T(v1)){
                if(tok == Tokens.SUMA)
                    v.suma(v1,tipoExpresion);
                else
                    v.resta(v1,tipoExpresion);
                if(Ep(v)){
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean T(StringM v){
        System.out.println("T");
        if(R(v))
            if(Tp(v))
                return true;
        return false;
    }
    
    public boolean Tp(StringM v){
        System.out.println("Tp");
        int tok;
        StringM v1 = new StringM("");
        tok = Lexic.obtenerToken();
        if(tok == Tokens.PROD || tok == Tokens.DIV){
            if(R(v1)){
                if(tok == Tokens.PROD)
                    v.producto(v1,tipoExpresion);
                else
                    v.division(v1,tipoExpresion);
                if(Tp(v)){
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean R(StringM v){
        System.out.println("R");
        if(F(v))
            if(Rp(v))
                return true;
        return false;
    }
    
    public boolean Rp(StringM v){
        System.out.println("Rp");
        int tok;
        StringM v1 = new StringM("");
        tok = Lexic.obtenerToken();
        if(tok == Tokens.POT || tok == Tokens.RAIZ){
            if(F(v1)){
                if(tok == Tokens.POT)
                    v.potencia(v1,tipoExpresion);
                else
                    v.raiz(v1,tipoExpresion);
                if(Rp(v)){
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    public boolean F(StringM v){
        int tok;
        tok = Lexic.obtenerToken();
        System.out.println("Token: "+tok);
        System.out.print("F -> ");
        if (tok == Tokens.PAR_I) {
            if(E(v)){
                tok = Lexic.obtenerToken();
                if(tok == Tokens.PAR_D)
                    return true;
            }
        }else if(tok == Tokens.NUM){
            v.setValor(Lexic.getLexema());
            System.out.println("Lexema: "+Lexic.getLexema());
            return true;
        }else if(tok == Tokens.SIN || tok == Tokens.COS || tok == Tokens.TAN ||
                tok == Tokens.POT ||  tok == Tokens.EXP || tok == Tokens.LN || tok == Tokens.LOG){
            //PONER OPERACIONES para "v"
            int AuxTok = tok;
            tok = Lexic.obtenerToken();
            if (tok == Tokens.PAR_I) {
                if(E(v)){
                    tok = Lexic.obtenerToken();
                    if(AuxTok == Tokens.SIN){
                        v.sin();
                    }else if(AuxTok == Tokens.COS){
                        v.cos();
                    }else if(AuxTok == Tokens.TAN){
                        v.tan();
                    }else if(AuxTok == Tokens.POT){
                        v.potencia(v,tipoExpresion);
                    }else if(AuxTok == Tokens.EXP){
                        v.exponencial();
                    }else if(AuxTok == Tokens.LN){
                        v.ln();
                    }else if(AuxTok == Tokens.LOG){
                        v.log();
                    }
                    if(tok == Tokens.PAR_D)
                        return true;
                    return false;
                }
            }
            
        }
        return false;
    }
    
    public double getResultado() {
        return resultado.getValor();
    }
    
    public double getExpresion() {
        return resultado.getValor();
    }
}