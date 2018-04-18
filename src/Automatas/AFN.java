package Automatas;

import java.util.HashSet;

public class AFN extends Automata {
    private HashSet<Character> alfabeto;
    private String expresionR;
    private Estado estadoInicial;
    private HashSet<Estado> estadosAceptacion;
    private HashSet<Estado> estados;

    /**
     * Constructor para crear un AFN basico con solo un caracter.
     * @param simbolo : caracter del AFN basico.
     */
    public AFN(char simbolo) {
        estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        alfabeto = new HashSet<>();
        estadosAceptacion = new HashSet<>();
        estados = new HashSet<>();
        
        //Crear transicion y estado de aceptacion
        estadoInicial.crearTrancision(simbolo, estadoFinal);
        estadoFinal.setEsAceptacion(true);
        estadosAceptacion.add(estadoFinal);
        
        //Agregar estados a las listas
        estados.add(estadoInicial);
        estados.add(estadoFinal);
        alfabeto.add(simbolo);
        
        expresionR = simbolo + "";
    }
    
    /**
     * Constructor para crear un AFN basico con un rango de simbolos.
     * @param simboloInicial : Simbolo donde comienza el rango.
     * @param simboloFinal : Simbolo donde termina el rango.
     */
    public AFN(char simboloInicial, char simboloFinal){
        estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        alfabeto = new HashSet<>();
        estados = new HashSet<>();
        estadosAceptacion = new HashSet<>();
        
        //Crear transicion y estado de aceptacion
        estadoInicial.crearTrancision(simboloInicial, simboloFinal, estadoFinal);
        estadoFinal.setEsAceptacion(true);
        estadosAceptacion.add(estadoFinal);
        
        //Agregar estados a las listas
        estados.add(estadoInicial);
        estados.add(estadoFinal);
        //Agregar caracteres al alfabeto
        for (int c = simboloInicial; c <= simboloFinal; c++)
            alfabeto.add((char)c);
        
        //EXPRESION REGULAR
        expresionR = simboloInicial + "" + (char)187 + "" + simboloFinal;
    }
    
    public AFN unirAFN(AFN afn2){
        Estado nuevoEstadoI = new Estado();
        Estado nuevoEstadoFinal = new Estado();
        
        //Crear transiciones con Epsilon
        nuevoEstadoI.crearTrancision(EPSILON, estadoInicial);
        nuevoEstadoI.crearTrancision(EPSILON, afn2.estadoInicial);
        
        //Elimina Edos de aceptacion de los anteriores Automatas
        for (Estado estado : estadosAceptacion) {
            estado.crearTrancision(EPSILON, nuevoEstadoFinal);
            estado.setEsAceptacion(false);
        }
        for (Estado estado : afn2.estadosAceptacion) {
            estado.crearTrancision(EPSILON, nuevoEstadoFinal);
            estado.setEsAceptacion(false);
        }
        
        //Actualizar Listas
        alfabeto.addAll(afn2.alfabeto);
        estados.addAll(afn2.estados);
        estados.add(nuevoEstadoI);
        estados.add(nuevoEstadoFinal);
        estadosAceptacion.clear();
        estadosAceptacion.add(nuevoEstadoFinal);
        nuevoEstadoFinal.setEsAceptacion(true);
        estadoInicial = nuevoEstadoI;
        
        expresionR = "(" + expresionR + "|" + afn2.expresionR + ")";
        return this;
    }
    
    public AFN concatenarAFN(AFN afn2){
        for (Estado estado : estadosAceptacion) {
            for (Transicion tr : afn2.estadoInicial.getTrancisiones())
                estado.crearTrancision(tr.getCarMax(), tr.getCarMin(), tr.getEstadoDestino());
            estado.setEsAceptacion(false);
        }
        
        afn2.estados.remove(afn2.estadoInicial);
        alfabeto.addAll(afn2.alfabeto);
        estados.addAll(afn2.estados);
        estadosAceptacion.clear();
        estadosAceptacion.addAll(afn2.estadosAceptacion);

        //EXPRESION REGULAR
        expresionR = expresionR + (char)186 + afn2.expresionR;
        return this;
    }
        
    public AFN cerraduraSuma(){
        Estado nuevoEstadoI = new Estado();
        Estado nuevoEstadoF = new Estado();
        
        nuevoEstadoI.crearTrancision(EPSILON, estadoInicial);
        for (Estado estado : estadosAceptacion) {
            estado.crearTrancision(EPSILON, estadoInicial);
            estado.crearTrancision(EPSILON, nuevoEstadoF);
            estado.setEsAceptacion(false);
        }
        
        nuevoEstadoF.setEsAceptacion(true);
        estados.add(nuevoEstadoI);
        estados.add(nuevoEstadoF);
        estadoInicial = nuevoEstadoI;
        estadosAceptacion.clear();
        estadosAceptacion.add(nuevoEstadoF);

        //EXPRESION REGULAR
        expresionR += (char)177;
        
        return this;
    }
    
    public AFN cerraduraAsterisco(){
        Estado nuevoEstadoI = new Estado();
        Estado nuevoEstadoF = new Estado();
        
        nuevoEstadoI.crearTrancision(EPSILON, estadoInicial);
        nuevoEstadoI.crearTrancision(EPSILON, nuevoEstadoF);
        for (Estado estado : estadosAceptacion) {
            estado.crearTrancision(EPSILON, estadoInicial);
            estado.crearTrancision(EPSILON, nuevoEstadoF);
            estado.setEsAceptacion(false);
        }
        
        nuevoEstadoF.setEsAceptacion(true);
        estados.add(nuevoEstadoI);
        estados.add(nuevoEstadoF);
        estadoInicial = nuevoEstadoI;
        estadosAceptacion.clear();
        estadosAceptacion.add(nuevoEstadoF);
        
        //EXPRESION REGULAR
        expresionR += (char)164;
        
        return this;
    }
    
    public AFN operacionSigno(){
        Estado nuevoEstadoI = new Estado();
        Estado nuevoEstadoF = new Estado();
        
        nuevoEstadoI.crearTrancision(EPSILON, estadoInicial);
        nuevoEstadoI.crearTrancision(EPSILON, nuevoEstadoF);
        for (Estado estado : estadosAceptacion) {
            estado.crearTrancision(EPSILON, nuevoEstadoF);
            estado.setEsAceptacion(false);
        }
        
        nuevoEstadoF.setEsAceptacion(true);
        estados.add(nuevoEstadoI);
        estados.add(nuevoEstadoF);
        estadoInicial = nuevoEstadoI;
        estadosAceptacion.clear();
        estadosAceptacion.add(nuevoEstadoF);
        
        //EXPRESION REGULAR
        expresionR += "?";
        return this;
    }
    
    public boolean analizarCardena(String s){
        HashSet<Estado> R = new HashSet<>();
        int l;
        R = CerraduraE(this.estadoInicial);
        
        l= s.length();
        for(int i=0; i < l; i++){
            R = IrA(R,s.charAt(i));
            if(R.isEmpty())
                return false;
        }
        //Si el ultimo caracter 
        for(Estado e: estadosAceptacion){
            if(R.contains(e))
                return true;
        }
        return false;
    }

    public HashSet<Character> getAlfabeto() {
        return alfabeto;
    }
    
    public String getExpresionR() {
        return expresionR;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }
    
    public HashSet<Estado> getEdosAceptacion(){
        return estadosAceptacion;
    }
}