package Automatas;

public class Transicion {
    private char carMin;
    private char carMax;
    private Estado estadoDestino;

    public Transicion(char carMin, char carMax, Estado estadoDestino) {
        this.carMin = carMin;
        this.carMax = carMax;
        this.estadoDestino = estadoDestino;
    }

    public Transicion(char caracter, Estado estadoDestino) {
        this.carMin =  this.carMax = caracter;
        this.estadoDestino = estadoDestino;
    }
    
    public boolean SeEncuentra(char c){
        for(char carAux = carMin; carAux!=(carMax+1); carAux+=1){
            if(c==carAux)
                return true;
        }
        return false;
    }

    public char getCarMin() {
        return carMin;
    }

    public char getCarMax() {
        return carMax;
    }

    public Estado getEstadoDestino() {
        return estadoDestino;
    }
}