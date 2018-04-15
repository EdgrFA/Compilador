package ExpresionesRegulares;

import Automatas.AFD;
import Automatas.AFNs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExpresionesRegulares {
    private final AFD afd;
    private final AnalizadorSintacticoER asER;
    private ArrayList<AFNs> afnsL;
    private BufferedReader br;
    
    public ExpresionesRegulares() {
        AFNs afns = new AFNs();
        
        afns.crearAFN('|'); //0
        afns.crearAFN('&'); //1
        afns.crearAFN('+'); //2
        afns.crearAFN('*'); //3
        afns.crearAFN('?'); //4
        afns.crearAFN('('); //5
        afns.crearAFN(')'); //6
        afns.crearAFN('['); //7
        afns.crearAFN(']'); //8
        afnSIMB(afns); //9
        afns.crearAFN('~'); //10
        
        afd = new AFD(afns);
        
        TokensER.UNION = afns.getTokenAFN(0);
        TokensER.CONC = afns.getTokenAFN(1);
        TokensER.CERR_SUM = afns.getTokenAFN(2);
        TokensER.CERR_ASTER = afns.getTokenAFN(3);
        TokensER.CERR_SIGNO = afns.getTokenAFN(4);
        TokensER.PAR_I = afns.getTokenAFN(5);
        TokensER.PAR_D = afns.getTokenAFN(6);
        TokensER.CORCH_I = afns.getTokenAFN(7);
        TokensER.CORCH_D = afns.getTokenAFN(8);
        TokensER.SIMB = afns.getTokenAFN(9);
        TokensER.SEPARADOR = afns.getTokenAFN(10);
        
        TokensER.TokenInfo();
        afd.imprimirTablaTransiciones();
        asER = new AnalizadorSintacticoER(afd);
        
        afnsL = new ArrayList<>();
        iniciarMenu();
    }

    //SIMB -> 9
    private static void afnSIMB(AFNs afns){
        afns.crearAFN((char)32, (char)37); //9
        afns.crearAFN((char)39); //10
        afns.crearAFN((char)44, (char)46); //11
        afns.crearAFN((char)48, (char)62); //12
        afns.crearAFN((char)64, (char)90); //13
        afns.crearAFN((char)92); //14
        afns.crearAFN((char)94, (char)123); //15
        afns.crearAFN((char)125); //16
        afns.union(15, 16); 
        afns.union(14, 15);
        afns.union(13, 14);
        afns.union(12, 13);
        afns.union(11, 12);
        afns.union(10, 11);
        afns.union(9, 10); // -> 9
        //Simbolos especiales
        afns.crearAFN('/'); //10*
        afns.crearAFN('+'); //11
        afns.crearAFN('&'); //12
        afns.crearAFN('?'); //13
        afns.crearAFN('*'); //14
        afns.crearAFN('('); //15
        afns.crearAFN(')'); //16
        afns.crearAFN('['); //17
        afns.crearAFN(']'); //18
        afns.crearAFN('/'); //19
        afns.crearAFN('~'); //20
        //Union de todos los simbolos especiales
        afns.union(19, 20); 
        afns.union(18, 19); 
        afns.union(17, 18);
        afns.union(16, 17);
        afns.union(15, 16);
        afns.union(14, 15);
        afns.union(13, 14);
        afns.union(12, 13);
        afns.union(11, 12); // -> 11 
        //Concatenarlos con '/'
        afns.concatenar(10, 11);
        //Unir todos los simbolos imprimibles
        afns.union(9, 10);    
    }
    
    private void iniciarMenu(){
        br = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while (!exit) {
            System.out.println("\n\nMENU CREACION DE AUTOMATAS");
            System.out.println("Elija una opcion:");
            System.out.println("1.- Crear AFD.");
            System.out.println("2.- Validar AFD.");
            System.out.println("3.- Imprimir Tabla de Transiciones de un AFD.");
            System.out.println("4.- Salir.");
            int opt;
            while (true) {
                try{
                    opt = Integer.parseInt(br.readLine());
                }catch(IOException ioe){
                    System.out.println("\nIntroduce un numero valido.");
                    continue;
                }catch(NumberFormatException ioe){
                    System.out.println("\nIntroduce un numero valido.");
                    continue;
                }
                if(opt >= 1 && opt <= 4)
                    break;
                else
                    System.out.println("\nIntroduce un numero valido");
            }
            switch(opt){
                case 1:
                    crearAFD();
                    break;
                case 2:
                    validarAFD();
                    break;
                case 3:
                    imprimirTabla();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
    
    private void crearAFD(){
        while(true){
            System.out.println("\nInserte la expresion regular del automata.");
            String expR;
            try{
                expR = br.readLine();
            }catch(IOException ioe){
                System.out.println("\nIntroduce una cadena valida.");
                continue;
            }
            AFNs newAfd = new AFNs();
            if(asER.AnalizarCadena(expR, newAfd)){
                afnsL.add(newAfd);
                break;
            }else
                System.out.println("Error al crear el automata.");
        }
    }
    
    private void validarAFD(){
        if(afnsL.isEmpty()){
            System.out.println("\nNo existen ningun AFD");
            return;
        }
        System.out.println("Introduce el indice del AFD");
        int index;
        while (true) {
            try{
                index = Integer.parseInt(br.readLine());
                if(index+1 != afnsL.size()){
                    System.out.println("Indice no existente.");
                    continue;
                }
                System.out.println("\nIntroduce la cadena a validar" + index);
                String cad = br.readLine();
                boolean isCorrect = afnsL.get(index).getAutomata(0).analizarCardena(cad);
                if (isCorrect)
                    System.out.println("Cadena Valida.");
                else
                    System.out.println("Cadena invalida.");
                break;
            }catch(IOException ioe){
                System.out.println("\nIntroduce un numero valido.");
                continue;
            }catch(NumberFormatException nfe){
                System.out.println("\nIntroduce un numero valido.");
                continue;
            }catch(IndexOutOfBoundsException iob){
                System.out.println("\nIndice incorrecto.");
                continue;
            }
        }
    }
    
    private void imprimirTabla(){
        if(afnsL.isEmpty()){
            System.out.println("\nNo existen ningun Automata");
            return;
        }
        System.out.println("Introduce el indice del Automnata");
        int index;
        while (true) {
            try{
                index = Integer.parseInt(br.readLine());
                System.out.println("\nTabla de transiciones del AFD: " + index);
                AFD afdN = new AFD(afnsL.get(index));
                afdN.imprimirTablaTransiciones();
                break;
            }catch(IOException ioe){
                System.out.println("\nIntroduce un numero valido.");
                continue;
            }catch(NumberFormatException nfe){
                System.out.println("\nIntroduce un numero valido.");
                continue;
            }catch(IndexOutOfBoundsException iob){
                System.out.println("\nIndice incorrecto.");
                continue;
            }
        }
    }

    public static void main(String[] args) {
        ExpresionesRegulares er = new ExpresionesRegulares();
    }
}
