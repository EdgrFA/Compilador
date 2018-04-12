package ExpresionesRegulares;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;
import Calculadora.DoubleM;
import Calculadora.Tokens;

public class AnalizadorSintacticoER {
    private AnalizadorLexico Lexic;
    private final AFD afd;
    private DoubleM resultado;
    private String expresion;
    private int indexAfns;
    
    public AnalizadorSintacticoER(AFD afd) {
        this.afd = afd;
    }
    
    private boolean AnalizarCadena(String cadena, AFD newAfd){
        Lexic = new AnalizadorLexico(cadena, afd);
        AFNs afns = new AFNs();
        indexAfns = 0;
        boolean isOk = E(afns);
        newAfd = null;
        if(isOk)
            newAfd = new AFD(afns);
        return isOk;
    }
    
    private boolean E(AFNs afns){
        if(T(afns))
            if(Ep(afns))
                return true;
        return false;
    }
    
    private boolean Ep(AFNs afns){
        int tok;
        tok = Lexic.obtenerToken();
        if(tok == TokensER.UNION){
            if(T(afns)){
                if(Ep(afns)){
                    afns.union(indexAfns-1, indexAfns);
                    indexAfns--;
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    private boolean T(AFNs afns){
        if(C(afns))
            if(Tp(afns))
                return true;
        return false;
    }
    
    private boolean Tp(AFNs afns){
        int tok;
        tok = Lexic.obtenerToken();
        if(tok == TokensER.CONC){
            if(C(afns)){
                if(Tp(afns)){
                    afns.concatenar(indexAfns-1, indexAfns);
                    indexAfns--;
                    return true;
                }
            }
            return false;
        }
        Lexic.regresarToken();
        return true;
    }
    
    private boolean C(AFNs afns){
        if(F(afns))
            if(Cp(afns))
                return true;
        return false;
    }
    
    private boolean Cp(AFNs afns){
        int tok;
        tok = Lexic.obtenerToken();
        if(tok == TokensER.CERR_SUM){
            if(Cp(afns)){
                afns.cerraduraSuma(indexAfns);
                return true;
            }
        }else if(tok == TokensER.CERR_ASTER){
            if(Cp(afns)){
                afns.cerraduraAsterisco(indexAfns);
                return true;
            }
        }else if(tok == TokensER.CERR_SIGNO){
            if(Cp(afns)){
                afns.operacionSigno(indexAfns);
                return true;
            }
        }else{
            Lexic.regresarToken();
            return true;
        }
        return false;
    }
    
    private boolean F(AFNs afns){
        int tok;
        tok = Lexic.obtenerToken();
        if(tok == TokensER.PAR_I){
            if(E(afns)){
                tok = Lexic.obtenerToken();
                if(tok == TokensER.PAR_D)
                    return true;
            }
        }else if(tok == TokensER.SIMB){
            char c = obtenerCaracter(Lexic.getLexema());
            afns.crearAFN(c);
            return true;
            
        }else if(tok == TokensER.CORCH_I){
            char c1, c2;
            tok = Lexic.obtenerToken();
            if(tok == TokensER.SIMB){
                c1 = obtenerCaracter(Lexic.getLexema());
                tok = Lexic.obtenerToken();
                if(tok == TokensER.SEPARADOR){
                    tok = Lexic.obtenerToken();
                    if(tok == TokensER.SIMB){
                        c2 = obtenerCaracter(Lexic.getLexema());
                        afns.crearAFN(c1, c2);
                        return true;
                    }
                }
            }
        }else{
            Lexic.regresarToken();
            return true;
        }
        return false;
    }
    
    private char obtenerCaracter(String str){
        if(str.charAt(0) == '/')
            return str.charAt(1);
        return str.charAt(0);
    }
}
