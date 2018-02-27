package Automatas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


public class AFN extends Automata {
    int idAFN;
    private Estado estadoInicial;
    private HashSet<Character> alfabeto;
    private HashSet<Estado> estados;
    private HashSet<Estado> estadosAceptacion;
    //Cadena para revisar la expresion regular
    private String expresionR;

    /**
     * Constructor para crear un AFN basico con solo un caracter.
     * @param c : caracter del AFN basico.
     */
    public AFN(char c) {
        estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        alfabeto = new HashSet<>();
        estados = new HashSet<>();
        estadosAceptacion = new HashSet<>();
        
        //Crear transicion y estado de aceptacion
        estadoInicial.crearTrancision(c, estadoFinal);
        estadoFinal.setEsAceptacion(true);
        estadosAceptacion.add(estadoFinal);
        
        //Agregar estados a las listas
        estados.add(estadoInicial);
        estados.add(estadoFinal);
        alfabeto.add(c);    //Agregar caracter al alfabeto    
        
        //EXPRESION REGULAR
        expresionR = c + "";
    }
    
    /**
     * Constructor para crear un AFN basico con un rango de caracteres.
     * @param carMax : caracter superior.
     * @param carMin : caracter inferior.
     */
    public AFN(char carMin, char carMax){
        estadoInicial = new Estado();
        Estado estadoFinal = new Estado();
        alfabeto = new HashSet<>();
        estados = new HashSet<>();
        estadosAceptacion = new HashSet<>();
        
        //Crear transicion y estado de aceptacion
        estadoInicial.crearTrancision(carMin, carMax, estadoFinal);
        estadoFinal.setEsAceptacion(true);
        estadosAceptacion.add(estadoFinal);
        
        //Agregar estados a las listas
        estados.add(estadoInicial);
        estados.add(estadoFinal);
        //Agregar caracteres al alfabeto
        for (int c = carMin; c <= carMax; c++)
            alfabeto.add((char)c);
        
        //EXPRESION REGULAR
        expresionR = carMin + "" + (char)187 + "" + carMax;
    }
    
    public AFN unirAFN(AFN afn2){
        Estado nuevoEstadoI = new Estado();
        Estado nuevoEstadoF = new Estado();
        
        //Crear transiciones con Epsilon
        nuevoEstadoI.crearTrancision(EPSILON, estadoInicial);
        nuevoEstadoI.crearTrancision(EPSILON, afn2.estadoInicial);
        
        for (Estado estado : estadosAceptacion) {
            estado.crearTrancision(EPSILON, nuevoEstadoF);
            estado.setEsAceptacion(false);
        }
        for (Estado estado : afn2.estadosAceptacion) {
            estado.crearTrancision(EPSILON, nuevoEstadoF);
            estado.setEsAceptacion(false);
        }
        
        //Actualizar Listas
        alfabeto.addAll(afn2.alfabeto);
        estados.addAll(afn2.estados);
        estados.add(nuevoEstadoI);
        estados.add(nuevoEstadoF);
        estadosAceptacion.clear();
        estadosAceptacion.add(nuevoEstadoF);
        nuevoEstadoF.setEsAceptacion(true);
        estadoInicial = nuevoEstadoI;
        
        //EXPRESION REGULAR
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
        expresionR = "(" + expresionR + ")" + (char)186 + "(" + afn2.expresionR + ")";
        
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
        expresionR = "(" + expresionR + ")" + (char)177;
        
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
        expresionR = "(" + expresionR + ")" + (char)164;
        
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
        expresionR = "(" + expresionR + ")" + "?";
        
        return this;
    }

    public HashSet<Character> getAlfabeto() {
        return alfabeto;
    }
    
    public boolean AnalizarCardena(String s){
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

    public String getExpresionR() {
        return expresionR;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }
    
    public static void main(String[] args) {
        AFN afn = new AFN('d','r');
        //afn.cerraduraAsterisco();
        System.out.println(afn.alfabeto);
        System.out.println("Soy el Edo Inicial= "+afn.estadoInicial.getTrancisiones().get(0).getCarMin());
        System.out.println("numero de Esdos= "+afn.estados.size());
        int i = 0;
        for(Estado e : afn.estados){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
            i++;
        }
        System.out.println("------------------------");
        HashSet<Estado> edosX = afn.CerraduraE(afn.estadoInicial);
        for(Estado e : edosX){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
            i++;
        }
        System.out.println("------------------------");
        AFN afn2 = new AFN('x','z');
        afn.unirAFN(afn2);
        System.out.println(afn.alfabeto);
        System.out.println("Soy el Edo Inicial= "+afn.estadoInicial.getTrancisiones().get(0).getCarMin());
        System.out.println("numero de Esdos= "+afn.estados.size());
        for(Estado e : afn.estados){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
        }
        System.out.println("----------------------");
        afn.cerraduraAsterisco();
        System.out.println(afn.alfabeto);
        System.out.println("Soy el Edo Inicial= "+afn.estadoInicial.getTrancisiones().get(0).getCarMin());
        System.out.println("numero de Esdos= "+afn.estados.size());
        for(Estado e : afn.estados){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
        }
        
        System.out.println("------------------------");
        edosX.clear();
        edosX = afn.CerraduraE(afn.estadoInicial);
        for(Estado e : edosX){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
            i++;
        }
        
        System.out.println("------------------------");
        edosX.clear();
        edosX = afn.Mover(afn.estadoInicial,EPSILON);
        for(Estado e : edosX){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
            i++;
        }
        
        System.out.println("-----------------------------");
        afn2 = new AFN('d','g');
        afn.unirAFN(afn2);
        System.out.println(afn.alfabeto);
        System.out.println("Soy el Edo Inicial= "+afn.estadoInicial.getTrancisiones().get(0).getCarMin());
        System.out.println("numero de Esdos= "+afn.estados.size());
        for(Estado e : afn.estados){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
        }
        System.out.println("----------Cerradura Canijota XD -------------------");
        edosX = afn.IrA(afn.CerraduraE(afn.estadoInicial), 'g');
        for(Estado e : edosX){
            System.out.println("Mi id= "+e.idEdo);
            for(Transicion t : e.getTrancisiones()){
                System.out.println(t.getCarMax());
            }
            i++;
        }
    }
}