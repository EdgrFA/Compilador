
package Automatas.descensoRecursivo;

public class Calculadora {
    boolean E(Float v){
        if(T(v))
            if(Ep(v))
                return true;
        return false;
    }
    
    boolean Ep(Float v){
        int tok=0;
        Float v1 = new Float(0.0);
        //
        if(tok == Tokens.MAS || tok == Tokens.MENOS){
            if(T(v1)){
                
            }
        }
        return false;
    }
    
    boolean T(Float v){
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
