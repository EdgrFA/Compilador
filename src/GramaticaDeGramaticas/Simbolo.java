package GramaticaDeGramaticas;

public class Simbolo {
    private boolean terminal;
    private String expresion;
    
    public Simbolo(String expresion){
        this.expresion = expresion;
        this.terminal = true;
    }
    
    public String getExpresion(){
        return expresion;
    }
    
    public boolean isTerminal(){
        return terminal;
    }
    
    public void setExpresion(String expresion){
        this.expresion = expresion;
    }
    
    public void setTerminal(boolean terminal){
        this.terminal = terminal;
    }
    
    @Override
    public boolean equals(Object obj){
        Simbolo Aux = (Simbolo) obj;
        return expresion.equals(Aux.getExpresion());
    }
    
    @Override
    public String toString(){
        return expresion;
    }
}