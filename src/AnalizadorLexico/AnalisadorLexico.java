package AnalizadorLexico;

import Automatas.AFN;
import Automatas.AFNs;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AnalisadorLexico {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion, id1,id2;
        String s;
        AFNs afns = new AFNs();
        while(!salir){
            System.out.println("***************************");
            System.out.println("1. Crear AFN Basico");
            System.out.println("2. Unir AFN");
            System.out.println("3. Concatenar AFN");
            System.out.println("4. Cerradura +");
            System.out.println("5. Cerradura *");
            System.out.println("6. Operacion ?");
            System.out.println("7. Validar Cadena");
            System.out.println("8. Mostrar Automatas");
            System.out.println("9. Salir");
            System.out.println("Selecciona una de la opciones");
            try {
                opcion = sn.nextInt();
                switch(opcion){
                    case 1:
                        System.out.println("\n\tCrear Automata: \n1.Crear Nuevo Automata. \n2.Reiniciar Automata ");
                        System.out.println("\nIngresa el caracter Minimo:");
                        String carMin = sn.next();
                        System.out.println("Ingresa el Caracter Maximo:");
                        String carMax = sn.next();
                        if(carMin.charAt(0) == carMax.charAt(0))
                            afns.crearAFN(carMax.charAt(0));
                        else
                            afns.crearAFN(carMin.charAt(0),carMax.charAt(0));
                        break;
                    case 2:
                        System.out.println("\n\t Unir Automatas");
                        System.out.println("Por favor ingresa el id del automata del primero");
                        id1 = sn.nextInt();
                        System.out.println("Por favor ingresa el id del automata del segundo");
                        id2 = sn.nextInt();
                        afns.union(id1, id2);
                        break;
                    case 3:
                        System.out.println("\n\t Concatenar Automatas");
                        System.out.println("Por favor ingresa el id del automata del primero");
                        id1 = sn.nextInt();
                        System.out.println("Por favor ingresa el id del automata del segundo");
                        id2 = sn.nextInt();
                        afns.concatenar(id1, id2);
                        break;
                    case 4:
                        System.out.println("\n\tCerradura +:");
                        System.out.println("Por favor ingresa el id del automata");
                        id1 = sn.nextInt();
                        afns.cerraduraSuma(id1);
                        System.out.println("Se ha aplicado Cerradura + a Automata "+id1);
                        break;
                    case 5:
                        System.out.println("\n\tCerradura +:");
                        System.out.println("Por favor ingresa el id del automata");
                        id1 = sn.nextInt();
                        afns.cerraduraAsterisco(id1);
                        System.out.println("Se ha aplicado Cerradura * a Automata "+id1);
                        break;
                    case 6:
                        System.out.println("\n\tOperacion ?:");
                        System.out.println("Por favor ingresa el id del automata");
                        id1 = sn.nextInt();
                        afns.operacionSigno(id1);
                        System.out.println("Se ha aplicado Operacion ? a Automata "+id1);
                        break;
                    case 7:
                        System.out.println("\n\tValidar Cadena ");
                        System.out.println("Por favor ingresa el id del automata");
                        id1 = sn.nextInt();
                        System.out.println("Ingrese la cadena a validar");
                        s = sn.next();
                        if(afns.validar(id1,s))
                            System.out.println("La cadena es aceptada");
                        else
                            System.out.println("La cadena NO es aceptada");
                        
                        break;
                    case 8:
                        //System.out.println("\n\tAutomatas: \nNumero de Automatas "+afns.getNoAutomatas());
                        ArrayList<AFN> automatas = afns.getAutomatas();
                        System.out.println("");
                        for (int i=0; i < automatas.size(); i++)
                            System.out.println("ID: " + i + ", Expresion Regular: " + automatas.get(i).getExpresionR());
                        System.out.println("");
                        break;
                    case 9:
                        System.out.println("Has seleccionado opcion 8");
                        salir = true;
                        break;
                    default:
                        System.out.println("Debes ingresar alguna de las opciones validas\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un numero");
                sn.next();
            }
        }
    }
}