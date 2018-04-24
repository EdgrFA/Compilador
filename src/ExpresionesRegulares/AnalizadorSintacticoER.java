package ExpresionesRegulares;

import Analizadores.AnalizadorLexico;
import Automatas.AFD;
import Automatas.AFNs;

public class AnalizadorSintacticoER {
    private AnalizadorLexico Lexic;
    private final AFD afd;
    private int indexAfns;
    
    public AnalizadorSintacticoER(AFD afd) {
        this.afd = afd;
    }
    
    public boolean AnalizarCadena(String cadena, AFNs afns){
        Lexic = new AnalizadorLexico(cadena, afd);
        indexAfns = -1;
        boolean isOk = E(afns);
        int tok = Lexic.obtenerToken();
        return isOk && (tok == TokensER.FIN);
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
            indexAfns++;
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
                        tok = Lexic.obtenerToken();
                        if(tok == TokensER.CORCH_D){
                            afns.crearAFN(c1, c2);
                            indexAfns++;
                            return true;   
                        }
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
