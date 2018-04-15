package Automatas;

public class TransicionCE {
    private char caracter;
    private ConjuntoEstados conjuntoDestino;

    public TransicionCE(char caracter, ConjuntoEstados conjuntoDestino) {
        this.caracter =  caracter;
        this.conjuntoDestino = conjuntoDestino;
    }
    
    public ConjuntoEstados getConjuntoD(){
        return conjuntoDestino;
    }
}
