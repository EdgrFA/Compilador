
package Calculadora;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;

public class AnalizadorSintactico {
    private AnalizadorLexico Lexic;
    private final AFD afd;

    public AnalizadorSintactico(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, DoubleM resultado, 
            StringM prefijo, StringM posfijo){
        Lexic = new AnalizadorLexico(cadena, afd);
        return E(resultado,prefijo,posfijo);
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
                if(tok == Tokens.SUMA){
                    v.suma(v1);
                    pre.suma(pre1,1);
                    pos.suma(pos1,0);
                }else{
                    v.resta(v1);
                    pre.resta(pre1,1);
                    pos.resta(pos1,0);
                }
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
                if(tok == Tokens.PROD){
                    v.producto(v1);
                    pre.producto(pre1,1);
                    pos.producto(pos1,0);
                }else{
                    v.division(v1);
                    pre.division(pre1,1);
                    pos.division(pos1,0);
                }
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
        StringM pre1 = new StringM("");
        StringM pos1 = new StringM("");
        tok = Lexic.obtenerToken();
        if(tok == Tokens.POT || tok == Tokens.RAIZ){
            if(F(v1,pre,pos)){
                if(tok == Tokens.POT){
                    v.potencia(v1);
                    pre.suma(pre1,1);
                    pos.suma(pos1,0);
                }else{
                    v.raiz(v1);
                    pre.suma(pre1,1);
                    pos.suma(pos1,0);
                }
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
            String lexema = Lexic.getLexema();
            v.setValor(Double.parseDouble(lexema));
            pre.setValor(lexema);
            pos.setValor(lexema);
            System.out.println("Lexema: "+lexema);
            return true;
        }else if(tok == Tokens.SIN || tok == Tokens.COS || tok == Tokens.TAN ||
                tok == Tokens.DI || tok == Tokens.POT ||  tok == Tokens.EXP 
                || tok == Tokens.LN || tok == Tokens.LOG){
            //PONER OPERACIONES para "v"
            int AuxTok = tok;
            tok = Lexic.obtenerToken();
            if (tok == Tokens.PAR_I) {
                if(E(v,pre,pos)){
                    tok = Lexic.obtenerToken();
                    if(AuxTok == Tokens.SIN){
                        v.sin();
                        pre.sinPre();
                        pos.sinPos();
                    }else if(AuxTok == Tokens.COS){
                        v.cos();
                        pre.cosPre();
                        pos.cosPos();
                    }else if(AuxTok == Tokens.TAN){
                        v.tan();
                        pre.tanPre();
                        pos.tanPos();
                    }else if(AuxTok == Tokens.POT){
                        v.potencia(v);
                    }else if(AuxTok == Tokens.EXP){
                        v.exponencial();
                        pre.exponencialPre();
                        pos.exponencialPos();
                    }else if(AuxTok == Tokens.LN){
                        v.ln();
                        pre.lnPre();
                        pos.lnPos();
                    }else if(AuxTok == Tokens.LOG){
                        v.log();
                        pre.logPre();
                        pos.logPos();
                    }
                    if(tok == Tokens.PAR_D)
                        return true;
                    return false;
                }
            }
            
        }
        return false;
    }
}