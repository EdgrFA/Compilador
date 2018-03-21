
package Automatas.descensoRecursivo;

public class Calculadora {
    boolean E(FloatM v){
        if(T(v))
            if(Ep(v))
                return true;
        return false;
    }
    
    boolean Ep(FloatM v){
        int tok=0;
        FloatM v1 = new FloatM(0.0f);
        //
        if(tok == Tokens.MAS || tok == Tokens.MENOS){
            if(T(v1)){
                if(tok == Tokens.MAS)
                    v.suma(v, v1);
                else
                    v.resta(v, v1);
                if(Ep(v))
                    return true;
            }
            return false;
        }
        return true;
    }
    
    boolean T(FloatM v){
        if(F(v))
            if(Tp(v))
                return true;
        return false;
    }
    
    boolean Tp(FloatM v){
        int tok=0;
        FloatM v1 = new FloatM(0.0f);
        //
        if(tok == Tokens.MAS || tok == Tokens.MENOS){
            if(T(v1)){
                if(tok == Tokens.MAS)
                    v.suma(v, v1);
                else
                    v.resta(v, v1);
                if(Ep(v))
                    return true;
            }
            return false;
        }
        return true;
    }
    
    public static void cambiar(FloatM v ){

    }
    
    public static void main(String[] args) {
        FloatM v = new FloatM(1.5f);
        FloatM v1 = new FloatM(1/4);
        
        System.out.println(v);
    }
}
