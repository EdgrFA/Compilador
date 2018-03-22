package Calculadora;

public class StringM {
    private String valor;
    
    public StringM(String valor){
        this.valor = valor; 
    }
    
    public void suma(StringM sumando, int preFijo){
        if(preFijo==1)
            valor = "+"+valor+" "+sumando;
        else
            valor = valor+" "+sumando+"+";
    }
    
    public void resta(StringM sustraendo, int preFijo){
        if(preFijo==1)
            valor = "-"+valor+" "+sustraendo;
        else
            valor = valor+" "+sustraendo+"-";
    }
    
    public void producto(StringM factor2,int preFijo){
        if(preFijo==1)
            valor = "*"+valor+" "+factor2;
        else
            valor = valor+" "+factor2+"*";
    }
    
    public void division(StringM divisor, int preFijo){
        if(preFijo==1)
            valor = "/"+valor+" "+divisor;
        else
            valor = valor+" "+divisor+"/";
    }
    
    public void potencia(StringM exponente, int preFijo){
        if(preFijo==1)
            valor = "^"+valor+" "+exponente;
        else
            valor = valor+" "+exponente+"^";
    }
    
    public void raiz(StringM indice, int preFijo){
        if(preFijo==1)
            valor = "sqrt "+valor+" "+indice;
        else
            valor = valor+" "+indice+" sqrt ";
    }
    
    public void sin(){
        valor = "SIN "+valor;
    }
    
    public void cos(){
        valor = "COS "+valor;
    }
    
    public void tan(){
        valor = "TAN "+valor;
    }
    
    public void exponencial(){
        valor = "e "+valor;
    }
    
    public void ln(){
        valor = "ln "+valor;
    }
    
    public void log(){
        valor = "log " + valor;
    }
    
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return valor;
    }
}
