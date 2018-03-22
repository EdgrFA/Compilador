package Calculadora;

public class DoubleM {
    private double valor;
    
    public DoubleM(double valor){
        this.valor = valor; 
    }
    
    public void suma(DoubleM sumando){
        valor = valor + sumando.getValor();
    }
    
    public void resta(DoubleM sustraendo){
        valor = valor - sustraendo.getValor();
    }
    
    public void producto(DoubleM factor2){
        valor = valor * factor2.getValor();
    }
    
    public void division(DoubleM divisor){
        valor = valor / divisor.getValor();
    }
    
    public void sin(){
        valor = Math.sin(valor);
    }
    
    public void cos(){
        valor = Math.cos(valor);
    }
    
    public void tan(){
        valor = Math.cos(valor);
    }
    
    public void potencia(DoubleM exponente){
        valor = Math.pow(valor,exponente.valor);
    }
    
    public void exponencial(){
        valor = Math.exp(valor);
    }
    
    public void ln(){
        valor = Math.log(valor);
    }
    
    public void log(){
        valor = Math.log10(valor);
    }
    
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    } 
}
