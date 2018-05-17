package GramaticaDeGramaticas;

public class SimboloInicial extends SimboloNoTerminal{
    Simbolo padre = Gramatica.RAIZ; //Necesario?
    
    public SimboloInicial(String expresion) {
        super(expresion);
    }
}