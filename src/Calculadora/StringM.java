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
    
    public void sinPre(){
        valor = "SIN "+valor;
    }
    
    public void cosPre(){
        valor = "COS "+valor;
    }
    
    public void tanPre(){
        valor = "TAN "+valor;
    }
    
    public void exponencialPre(){
        valor = "e "+valor;
    }
    
    public void lnPre(){
        valor = "ln "+valor;
    }
    
    public void logPre(){
        valor = "log " + valor;
    }
    
    public void sinPos(){
        valor = valor +" SIN";
    }
    
    public void cosPos(){
        valor = valor +" COS";
    }
    
    public void tanPos(){
        valor = valor + " TAN";
    }
    
    public void exponencialPos(){
        valor = valor +" e";
    }
    
    public void lnPos(){
        valor = valor+" ln";
    }
    
    public void logPos(){
        valor = valor +" log";
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
