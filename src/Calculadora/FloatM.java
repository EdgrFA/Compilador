package Calculadora;

public class FloatM {
    private float valor;
    
    public FloatM(float valor){
        this.valor = valor; 
    }
    
    public static float prod(FloatM factor1, FloatM factor2){
        return factor1.getValor()*factor2.getValor();
    }
    
    public static float div(FloatM numerador, FloatM divisor){
        return numerador.getValor()/divisor.getValor();
    }
    
    public void suma(FloatM sumando){
        valor = valor + sumando.getValor();
    }
    
    public void resta(FloatM sustraendo){
        valor = valor - sustraendo.getValor();
    }
    
    public void producto(FloatM factor2){
        valor = valor * factor2.getValor();
    }
    
    public void division(FloatM divisor){
        valor = valor / divisor.getValor();
    }
    
    public void sin(FloatM divisor){
        valor = (float) Math.sin((double)valor);
    }
    
    public void cos(FloatM divisor){
        valor = valor / divisor.getValor();
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }    
}
