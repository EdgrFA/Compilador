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
    
    public void suma(FloatM sumando1, FloatM sumando2){
        valor = sumando1.getValor()+sumando2.getValor();
    }
    
    public void resta(FloatM minuendo, FloatM sustraendo){
        valor = minuendo.getValor()+sustraendo.getValor();
    }
    
    public void producto(FloatM factor1, FloatM factor2){
        valor = factor1.getValor()*factor2.getValor();
    }
    
    public void division(FloatM numerador, FloatM divisor){
        valor = numerador.getValor()/divisor.getValor();
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }    
}
