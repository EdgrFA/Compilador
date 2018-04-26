package GramaticaDeGramaticas;

public class Simbolo {
    private String simbolo;
    private boolean esTerminal;

    public Simbolo(String simbolo) {
        this.simbolo = simbolo;
        esTerminal = false;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String Simbolo) {
        this.simbolo = Simbolo;
    }

    public boolean esTerminal() {
        return esTerminal;
    }

    public void setEsTerminal(boolean esTerminal) {
        this.esTerminal = esTerminal;
    }
}
