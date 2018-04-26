package GramaticaDeGramaticas;

public class TokensGramatica {
    public static int PC;
    public static int OR;
    public static int FLECHA;
    public static int SIMB;
    public static int EPSILON;
    public static final int FIN = 0;
    public static final int ERROR = -1;

    public static void TokenInfo() {
        System.out.println("Token PC =  " + TokensGramatica.PC);
        System.out.println("Token OR =  " + TokensGramatica.OR);
        System.out.println("Token FLECHA = " + TokensGramatica.FLECHA);
        System.out.println("Token SIMB =  " + TokensGramatica.SIMB);
        System.out.println("Token EPSILON =  " + TokensGramatica.EPSILON);
        System.out.println("");
    }
}